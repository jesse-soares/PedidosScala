package bo

import play.api.mvc.Action
import dao._
import models._

object AreaCalculavelBO {
   //mesmos metodos com validacao
   def inserir(areaCalculavel: AreaCalculavel) = {
     AreaCalculavelDAO.inserir(areaCalculavel)
   }
   
   def listar() : List[AreaCalculavel] = {
	 AreaCalculavelDAO.listar()
   }
   
   def remover(id: Long) = {
     AreaCalculavelDAO.remover(id)
   }
   
}