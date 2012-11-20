package dao

import anorm._
import anorm.SqlParser._
import play.api.db._
import play.api.Play.current
import models._

object PagamentoDAO {
  
  //Parser da tabela do BD para o objeto
  val pagamento = {
    get[Option[Long]]("id") ~
    get[String]("data")~
    get[Long]("idFormaPagamento") map {
      case id~data~formaPagamento => Pagamento(id, data, FormaPagamentoDAO.buscarPorId(formaPagamento))
    }
  }
  
  //Metodo que busca todos pagamento do BD
  def listar(): List[Pagamento] = DB.withConnection {implicit c =>
    SQL("SELECT * FROM Pagamento").as(pagamento *)    
  }
  
  def buscarPorId(id: Option[Long]): Pagamento = DB.withConnection { implicit c =>
    try {
    	SQL("SELECT * FROM Pagamento WHERE id = {id}").on('id -> id).as(pagamento.single)
    } catch {
      case e: Exception => null
    }
  }  
  
  def buscarUltimo(): Pagamento = DB.withConnection {implicit c =>
    SQL("SELECT * FROM Pagamento ORDER BY id DESC LIMIT 1").as(pagamento.single)   
  }
  
  def inserir(pagamento: Pagamento) = DB.withConnection { implicit c =>
    SQL("INSERT INTO Pagamento(data, idFormaPagamento) VALUES ({data}, {idFormaPagamento})").on('data -> pagamento.data, 'idFormaPagamento -> pagamento.formaPagamento.id).executeUpdate()        
  }
  
}

