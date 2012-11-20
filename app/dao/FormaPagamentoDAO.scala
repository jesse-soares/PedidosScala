package dao

import anorm._
import anorm.SqlParser._
import play.api.db._
import play.api.Play.current
import models._

object FormaPagamentoDAO {
  
  //Parser da tabela do BD para o objeto
  val formaPagamento = {
    get[Long]("id") ~
    get[String]("nome") map {
      case id~nome => FormaPagamento(id, nome)
    }
  }
  
  //Metodo que busca todas as formas de pagamento do BD
  def listar(): List[FormaPagamento] = DB.withConnection {implicit c =>
    SQL("SELECT * FROM FormaPagamento").as(formaPagamento *)    
  }
  
  def buscarPorId(id: Long): FormaPagamento = DB.withConnection { implicit c =>
    SQL("SELECT * FROM FormaPagamento WHERE id = {id}").on('id -> id).as(formaPagamento.single) 
  }
   
}