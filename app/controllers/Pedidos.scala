package controllers

import play.api.mvc.Controller
import play.api.mvc.Action
import com.codahale.jerkson.Json
import bo._
import models._
import play.api.libs.json.JsValue
import utils.JsonObjectParser

object Pedidos extends Controller {
  
  val pedidoParser = new JsonObjectParser[Pedido]()
  
  def listar() = Action {
    val pedidos = PedidoBO.listar() 
    val json = Json.generate(pedidos) 
    Ok(json).as("application/json")
  }
  
  def buscarPorId(id: Long) = Action {
    val pedidos = PedidoBO.buscarPorId(id)
    val json = Json.generate(pedidos) 
    Ok(json).as("application/json")
  }
  
  def remover(id: Long) = Action { 
    val pedidos = PedidoBO.remover(id)
    val json = Json.generate(pedidos) 
    Ok(json).as("application/json")
  }
  
  def inserir() = Action { implicit request =>
    
    val valor = request.queryString.get("valor").flatMap(_.headOption).get.toDouble
    val areaSimulacao = request.queryString.get("areaSimulacao").flatMap(_.headOption).get.toDouble
    val arquivoInicial = request.queryString.get("arquivoInicial").flatMap(_.headOption).get
    
    val pedido = new Pedido(valor, areaSimulacao, arquivoInicial)
    val idPedido = PedidoBO.inserir(pedido)
    val id = Json.generate(idPedido) 
    Ok(id).as("application/json") 
  }
  
  def simularValor(area: Long) = Action {
    val pedidos = PedidoBO.simularValor(area)
    val json = Json.generate(pedidos) 
    Ok(json).as("application/json")
  }
  
  def realizarPagamento(id: Long) = Action {
    PedidoBO.realizarPagamento(id)
    Ok("OK").as("application/json")
  }
  
}