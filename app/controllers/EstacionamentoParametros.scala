package controllers

import play.api.mvc.Controller
import play.api.mvc.Action
import com.codahale.jerkson.Json
import dao._

object EstacionamentoParametros extends Controller {
  
  def listar() = Action {
    val estacionamentoParametros = EstacionamentoParametroDAO.listar() 
    val json = Json.generate(estacionamentoParametros) 
    Ok(json).as("application/json")
  }
  
  def buscarPorId(id: Long) = Action {
    val estacionamentoParametros = EstacionamentoParametroDAO.buscarPorId(id)
    val json = Json.generate(estacionamentoParametros) 
    Ok(json).as("application/json")
  }
  
}