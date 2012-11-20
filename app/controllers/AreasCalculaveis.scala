package controllers

import play.api.mvc.Controller
import play.api.mvc.Action
import com.codahale.jerkson.Json
import bo._

object AreasCalculaveis extends Controller {
  
  def listar() = Action {
    val areasCalculaveis = AreaCalculavelBO.listar() 
    val json = Json.generate(areasCalculaveis) 
    Ok(json).as("application/json")
  }
  
}