package dao

import anorm._
import anorm.SqlParser._
import play.api.db._
import play.api.Play.current
import models._

object PedidoDAO {

    //Parser da tabela do BD para o objeto
    val pedido = {
        get[Option[Long]]("id") ~                      
        get[String]("data") ~                      
        get[Double]("valor") ~                      
        get[Option[Long]]("idPagamento") ~ 
        get[Long]("idStatus") ~ 
        get[Long]("idAreaCalculavel") ~ 
        get[Long]("idCliente") map {
            case id~data~valor~pagamento~status~areaCalculavel~cliente => Pedido(id, data, valor, PagamentoDAO.buscarPorId(pagamento), StatusDAO.buscarPorId(status), AreaCalculavelDAO.buscarPorId(areaCalculavel), ClienteDAO.buscarPorId(cliente))                  
        }
    }
    
    //Metodo que busca todos pedidos do BD
    def listar(): List[Pedido] = DB.withConnection {implicit c =>
        SQL("SELECT * FROM Pedido").as(pedido *)    
    }
    
    def listarPorStatus(idStatus:Long): List[Pedido] = DB.withConnection {implicit c =>
        SQL("SELECT * FROM Pedido WHERE idStatus = {idStatus}").on('idStatus -> idStatus).as(pedido *)    
    }
    
    def buscarPorId(id: Long): Pedido = DB.withConnection { implicit connection =>
      	try {
      		SQL("SELECT * FROM Pedido WHERE id = {id}").on('id -> id).as(pedido.single)
      	} catch {
      		case e: Exception => null
      	}
    }
    
    def buscarUltimo(): Pedido = DB.withConnection {implicit c =>
    	SQL("SELECT * FROM Pedido ORDER BY id DESC LIMIT 1").as(pedido.single)   
    }

    def remover(id: Long) = DB.withConnection { implicit connection =>
        SQL("DELETE FROM Pedido WHERE id = {id}").on('id -> id).executeUpdate()
    }
        
    def inserir(pedido: Pedido) = DB.withConnection { implicit c =>
        SQL("INSERT INTO Pedido(data, valor, idStatus, idAreaCalculavel, idCliente) VALUES ({data}, {valor}, {idStatus}, {idAreaCalculavel}, {idCliente})").on('data -> pedido.data, 'valor -> pedido.valor, 'idStatus -> pedido.status.id, 'idAreaCalculavel -> pedido.areaCalculavel.id, 'idCliente -> pedido.cliente.id).executeUpdate()        
    } 
    
    def atualizarIdPagamento(idPagamento: Option[Long], id: Long) = DB.withConnection { implicit c =>
        SQL("UPDATE Pedido SET idPagamento = {idPagamento}, idStatus = {idStatus} WHERE id = {id}").on('idPagamento -> idPagamento, 'idStatus -> Status.CALCULO_DO_ESTACIONAMENTO_EM_PROCESSAMENTO, 'id -> id).executeUpdate()        
    }
    
    def atualizarAreaReal(idPedido: Long, areaReal: Double) = DB.withConnection { implicit c =>
        SQL("UPDATE AreaCalculavel SET areaReal = {areaReal} WHERE id = (SELECT idAreaCalculavel FROM Pedido WHERE id = {idPedido})").on('areaReal -> areaReal, 'idPedido -> idPedido).executeUpdate()        
        SQL("UPDATE Pedido SET idStatus = {idStatus} WHERE id = {idPedido}").on('idStatus -> Status.AREA_CALCULADA_AGUARDANDO_PROCESSAMENTO, 'idPedido -> idPedido).executeUpdate()        
    }
    
    def atualizarArquivoFinal(idPedido: Long, arquivoFinal: String) = DB.withConnection { implicit c =>
        SQL("UPDATE AreaCalculavel SET arquivoFinal = {arquivoFinal} WHERE id = (SELECT idAreaCalculavel FROM Pedido WHERE id = {idPedido})").on('arquivoFinal -> arquivoFinal, 'idPedido -> idPedido).executeUpdate()        
        SQL("UPDATE Pedido SET idStatus = {idStatus} WHERE id = {idPedido}").on('idStatus -> Status.FINALIZADO, 'idPedido -> idPedido).executeUpdate()        
    }
    
}