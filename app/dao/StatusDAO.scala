package dao

import anorm._
import anorm.SqlParser._
import play.api.db._
import play.api.Play.current
import models._

object StatusDAO {
  
  //Parser da tabela do BD para o objeto
  val status = {
    get[Long]("id") ~
    get[String]("nome") map {
      case id~nome => Status(id, nome)
    }
  }
  
  //Metodo que busca todos status do BD
  def listar(): List[Status] = DB.withConnection {implicit c =>
    SQL("SELECT * FROM Status").as(status *)    
  }
  
  def buscarPorId(id: Long): Status = DB.withConnection { implicit c =>
    SQL("SELECT * FROM Status WHERE id = {id}").on('id -> id).as(status.single) 
  }
  
}