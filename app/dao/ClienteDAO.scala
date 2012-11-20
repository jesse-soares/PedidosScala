package dao

import anorm._
import anorm.SqlParser._
import play.api.db._
import play.api.Play.current
import models._
 
object ClienteDAO {
  
  //Parser da tabela do BD para o objeto
  val cliente = {
    get[Long]("id") ~
    get[String]("cpf")~
    get[String]("nome")~
    get[String]("endereco")~
    get[String]("cidade")~
    get[String]("estado")~
    get[String]("email") map {
      case id~cpf~nome~endereco~cidade~estado~email => Cliente(id,cpf,nome,endereco,cidade,estado,email)
    }
  }
  
  //Metodo que busca todos clientes do BD
  def listar(): List[Cliente] = DB.withConnection {implicit c =>
    SQL("SELECT * FROM Cliente").as(cliente *)    
  }
  
  def buscarPorId(id: Long): Cliente = DB.withConnection { implicit c =>
    SQL("SELECT * FROM Cliente WHERE id = {id}").on('id -> id).as(cliente.single) 
  }

}