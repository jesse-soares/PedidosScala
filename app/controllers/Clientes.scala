package controllers

import play.api.mvc.Controller
import play.api.mvc.Action
import com.codahale.jerkson.Json
import dao.ClienteDAO

object Clientes extends Controller {
  
  def listar() = Action {
    val cliente = ClienteDAO.listar() 
    val json = Json.generate(cliente) 
    Ok(json).as("application/json")
  }
  
  def buscarPorId(id: Long) = Action {
    val cliente = ClienteDAO.buscarPorId(id)
    val json = Json.generate(cliente) 
    Ok(json).as("application/json")
  }
  
}