package bo

import play.api.mvc.Action
import dao._
import models._

object PagamentoBO {
   //mesmos metodos com validacao
   def inserir(pagamento: Pagamento) = {
     PagamentoDAO.inserir(pagamento)
   }
   
   def listar() : List[Pagamento] = {
	 PagamentoDAO.listar()
   }
   
   def remover(id: Long) = {
     PedidoDAO.remover(id)
   }
   
}