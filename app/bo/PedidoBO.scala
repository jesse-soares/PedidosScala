package bo

import play.api.mvc.Action
import dao._
import models._
import play.api.libs.ws.WS
import play.api.libs.ws.Response
import akka.dispatch.Promise

object PedidoBO {
  
   def inserir(pedido: Pedido): Long = {
     AreaCalculavelDAO.inserir(pedido.areaCalculavel)  
     val idAreaCalculavel = AreaCalculavelDAO.buscarUltimo() 
     val novoPedido = new Pedido("09/11/2012", pedido.valor, new Status(1), idAreaCalculavel, new Cliente(1))
     PedidoDAO.inserir(novoPedido)
     return PedidoDAO.buscarUltimo().id.get
   }
   
   def listar() : List[Pedido] = {
	 PedidoDAO.listar()
   }
   
   def listarPorStatus(idStatus:Long) : List[Pedido] = {
	 PedidoDAO.listarPorStatus(idStatus)
   }
   
   def buscarPorId(id: Long): Pedido = {
     PedidoDAO.buscarPorId(id)
   }
   
   def remover(id: Long) = {
     PedidoDAO.remover(id)
   }
   
   def simularValor(area: Long): Long = {
     area * 10
   }
   
   def realizarPagamento(id: Long) = {     
     val pagamento = new Pagamento("10/11/2012", new FormaPagamento(1))
     PagamentoDAO.inserir(pagamento)
     val idPagamento = PagamentoDAO.buscarUltimo().id
     PedidoDAO.atualizarIdPagamento(idPagamento, id)
   }
   
   def enviarCalcularArea() = {
     
     val pedidos = listarPorStatus(Status.CALCULO_AREA_EM_PROCESSAMENTO)
     
     for (pedido <- pedidos) {
    	 
    	 println("Chamando webservice para calcular area do pedido " + pedido.id)
    	 
    	 val response = WS.url("http://localhost:9000/EnterpriseServiceBus/resources/ESBReceiver").post(pedido.areaCalculavel.arquivoInicial)
		 val areaReal = response.value.get.body.toDouble
		 
		 println("Retorno do servico: " + areaReal)
    	 
    	 PedidoDAO.atualizarAreaReal(pedido.id.get, areaReal)
    	 
    	 // TODO enviar email com o resultado da Area
     }
     
  }
   
   def enviarCalcularEstacionamento() = {
     
     val pedidos = listarPorStatus(Status.CALCULO_DO_ESTACIONAMENTO_EM_PROCESSAMENTO)
     
     for (pedido <- pedidos) {
    	 
    	 println("Chamando webservice para calcular area do estacionamento " + pedido.id)
    	 
    	 val response = WS.url("http://localhost:9000/EnterpriseServiceBus/resources/EstacionamentoReceiver").post(pedido.areaCalculavel.arquivoInicial)
		 val arquivoFinal = response.value.get.body
		 
		 println("Retorno do servico: " + arquivoFinal)
    	 
    	 PedidoDAO.atualizarArquivoFinal(pedido.id.get, arquivoFinal)
    	 
    	 // TODO enviar email com o resultado da Area
     }
     
  }
   
}