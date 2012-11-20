import play.api._
import play.api.mvc._
import play.api.data._
import akka.actor.Actor
import akka.actor.Props
import akka.util.duration.intToDurationInt
import play.api.libs.concurrent.Akka
import bo.PedidoBO

object Global extends GlobalSettings {
  
  override def onStart(app: Application) {
    
    println("Application started..")
    
    // Tarefa agenda que executa a cada 20 segundos
  	Akka.system(app).scheduler.schedule(2 seconds, 20 seconds) {
	  
  	  println("Verificando pedidos que estao 'Calculo da Area em processamento'")
	  
	  PedidoBO.enviarCalcularArea()
	  
	  println("Verificando pedidos que estao 'Calculo do Estacionamento em processamento'")
	  
	  PedidoBO.enviarCalcularEstacionamento()
	}
  	
  }
  
}