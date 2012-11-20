package controllers

import play.api.mvc.Controller
import play.api.mvc.Action
import com.codahale.jerkson.Json
import bo._
import models._
import play.api.libs.json.JsValue

object Simulacao extends Controller {
 
 def ESBReceiver() = Action { implicit request =>
   val body = request.body
   println(body)
   Ok( Json.generate(100) ).as("application/json")
 }
 
 def EstacionamentoReceiver() = Action { implicit request =>
   val body = request.body
   println(body)
   Ok( Json.generate("{ arquivo final JSON }") ).as("application/json")
 }
   
}