package models

case class Status(
    id: Long, 
    nome: String
){
  
  def this(id: Long) = this(id, null)

}

object Status {
  
	val CALCULO_AREA_EM_PROCESSAMENTO:Long = 1
	val AREA_CALCULADA_AGUARDANDO_PROCESSAMENTO:Long = 2
	val CALCULO_DO_ESTACIONAMENTO_EM_PROCESSAMENTO:Long = 3
	val FINALIZADO:Long = 4
}