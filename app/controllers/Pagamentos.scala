package controllers

import play.api.mvc.Controller
import play.api.mvc.Action
import com.codahale.jerkson.Json
import bo._

object Pagamentos extends Controller {
  
  def listar() = Action {
    val pagamentos = PagamentoBO.listar() 
    val json = Json.generate(pagamentos) 
    Ok(json).as("application/json")
  }
  
  def inserir() = Action {
	PagamentoBO.inserir(null)  
    //val pedido = new Pedido(null, "05/11/2012", 30, PagamentoDAO.buscarPorId(1), StatusDAO.buscarPorId(1), AreaCalculavelDAO.buscarPorId(1), ClienteDAO.buscarPorId(1))
	//val pedidoBO = PedidoDAO.inserir(pedido)   
	Ok("{}").as("application/json")
  }
}