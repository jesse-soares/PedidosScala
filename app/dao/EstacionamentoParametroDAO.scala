package dao

import anorm._
import anorm.SqlParser._
import play.api.db._
import play.api.Play.current
import models._

object EstacionamentoParametroDAO {
  
  //Parser da tabela do BD para o objeto
  val estacionamentoParametro = {
    get[Long]("id") ~
    get[Long]("idEstacionamento") ~
    get[Long]("idParametro") ~
    get[Double]("valor") map {
      case id~estacionamento~parametro~valor => EstacionamentoParametro(id,EstacionamentoDAO.buscarPorId(estacionamento),ParametroDAO.buscarPorId(parametro),valor)
    }
  }
  
  //Metodo que busca todos estacionamentoParametro do BD
  def listar(): List[EstacionamentoParametro] = DB.withConnection {implicit c =>
    SQL("SELECT * FROM EstacionamentoParametro").as(estacionamentoParametro *)    
  }
  
  def buscarPorId(id: Long): EstacionamentoParametro = DB.withConnection { implicit c =>
    SQL("SELECT * FROM EstacionamentoParametro WHERE id = {id}").on('id -> id).as(estacionamentoParametro.single) 
  }

}
