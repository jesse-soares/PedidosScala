package controllers

import play.api.mvc.Controller
import play.api.mvc.Action
import com.codahale.jerkson.Json
import dao.StatusDAO

object Status extends Controller {
  
  def listar() = Action {
    val status = StatusDAO.listar() 
    val json = Json.generate(status) 
    Ok(json).as("application/json")
  }
  
  def buscarPorId(id: Long) = Action {
    val status = StatusDAO.buscarPorId(id)
    val json = Json.generate(status) 
    Ok(json).as("application/json")
  }
  
}