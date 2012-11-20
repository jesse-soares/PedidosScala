package dao

import anorm._
import anorm.SqlParser._
import play.api.db._
import play.api.Play.current
import models._

object AreaCalculavelDAO {
  
  //Parser da tabela do BD para o objeto
  val areaCalculavel = {
    get[Option[Long]]("id") ~
    get[Double]("areaSimulacao")~
    get[Option[Double]]("areaReal")~
    get[String]("arquivoInicial")~
    get[Option[String]]("arquivoFinal")map {
      case id~areaSimulacao~areaReal~arquivoInicial~arquivoFinal => AreaCalculavel(id,areaSimulacao,areaReal,arquivoInicial,arquivoFinal)
    } 
  } 
  
  //Metodo que busca todas áreas calculaveis do BD
  def listar(): List[AreaCalculavel] = DB.withConnection {implicit c =>
    SQL("SELECT * FROM AreaCalculavel").as(areaCalculavel *)    
  }
  
  def buscarUltimo(): AreaCalculavel = DB.withConnection {implicit c =>
    SQL("SELECT * FROM AreaCalculavel ORDER BY id DESC LIMIT 1").as(areaCalculavel.single)   
  }
  
  def buscarPorId(id: Long): AreaCalculavel = DB.withConnection { implicit connection =>
    SQL("SELECT * FROM AreaCalculavel WHERE id = {id}").on('id -> id).as(areaCalculavel.single)
  }
    
  def inserir(areaCalculavel: AreaCalculavel) = DB.withConnection { implicit c =>
    SQL("INSERT INTO AreaCalculavel(areaSimulacao, arquivoInicial) VALUES ({areaSimulacao}, {arquivoInicial})").on('areaSimulacao -> areaCalculavel.areaSimulacao, 'arquivoInicial -> areaCalculavel.arquivoInicial).executeUpdate()        
  }
  
  def remover(id: Long) = DB.withConnection { implicit connection =>
    SQL("DELETE FROM AreaCalculavel WHERE id = {id}").on('id -> id).executeUpdate()
  }
  
}
