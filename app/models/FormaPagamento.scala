package models

case class FormaPagamento(
    id: Long, 
    nome: String
){
  
  def this(id: Long) = this(id, null)

}
