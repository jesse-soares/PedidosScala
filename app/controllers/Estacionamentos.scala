package controllers

import play.api.mvc.Controller
import play.api.mvc.Action
import com.codahale.jerkson.Json
import dao._

object Estacionamentos extends Controller {
  
  def listar() = Action {
    val estacionamentos = EstacionamentoDAO.listar() 
    val json = Json.generate(estacionamentos) 
    Ok(json).as("application/json")
  }
  
  def buscarPorId(id: Long) = Action {
    val estacionamentos = EstacionamentoDAO.buscarPorId(id)
    val json = Json.generate(estacionamentos) 
    Ok(json).as("application/json")
  }
  
}