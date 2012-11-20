package models

case class Pagamento(
    id: Option[Long], 
    data: String,
    formaPagamento: FormaPagamento
){
  
  def this(data: String, 
		  formaPagamento: FormaPagamento) 
  	= this(null, data, formaPagamento)
  
}