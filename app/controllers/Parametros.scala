package controllers

import play.api.mvc.Controller
import play.api.mvc.Action
import com.codahale.jerkson.Json
import dao._

object Parametros extends Controller {
  
  def listar() = Action {
    val parametro = ParametroDAO.listar() 
    val json = Json.generate(parametro) 
    Ok(json).as("application/json")
  }
  
  def buscarPorId(id: Long) = Action {
    val parametro = ParametroDAO.buscarPorId(id)
    val json = Json.generate(parametro) 
    Ok(json).as("application/json")
  }
  
}