package controllers

import play.api.mvc.Controller
import play.api.mvc.Action
import dao.FormaPagamentoDAO
import com.codahale.jerkson.Json

object FormasPagamento extends Controller {
  
  def listar() = Action {
    val formasPagamento = FormaPagamentoDAO.listar() 
    val json = Json.generate(formasPagamento) 
    Ok(json).as("application/json")
  }
  
  def buscarPorId(id: Long) = Action {
    val formasPagamento = FormaPagamentoDAO.buscarPorId(id)
    val json = Json.generate(formasPagamento) 
    Ok(json).as("application/json")
  }
  
}