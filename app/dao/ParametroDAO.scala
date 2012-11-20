package dao

import anorm._
import anorm.SqlParser._
import play.api.db._
import play.api.Play.current
import models._

object ParametroDAO {
  
  //Parser da tabela do BD para o objeto
  val parametro = {
    get[Long]("id") ~
    get[String]("nome") map {
      case id~nome => Parametro(id, nome)
    }
  } 
  
  //Metodo que busca todos parametros do BD
  def listar(): List[Parametro] = DB.withConnection {implicit c =>
    SQL("SELECT * FROM Parametro").as(parametro *)    
  }
  
  def buscarPorId(id: Long): Parametro = DB.withConnection { implicit c =>
    SQL("SELECT * FROM Parametro WHERE id = {id}").on('id -> id).as(parametro.single) 
  }
    
}