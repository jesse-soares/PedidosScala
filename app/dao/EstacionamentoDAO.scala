package dao

import anorm._
import anorm.SqlParser._
import play.api.db._
import play.api.Play.current
import models._

object EstacionamentoDAO {
  
  //Parser da tabela do BD para o objeto
  val estacionamento = {
    get[Long]("id")~
    get[Long]("idAreaCalculavel")map {
      case id~areaCalculavel => Estacionamento(id, AreaCalculavelDAO.buscarPorId(areaCalculavel))
    }
  }
  
  //Metodo que busca todos estacionamentos do BD
  def listar(): List[Estacionamento] = DB.withConnection {implicit c =>
    SQL("SELECT * FROM Estacionamento").as(estacionamento *)    
  }
  
  def buscarPorId(id: Long): Estacionamento = DB.withConnection { implicit c =>
    SQL("SELECT * FROM Estacionamento WHERE id = {id}").on('id -> id).as(estacionamento.single) 
  }
   
}
