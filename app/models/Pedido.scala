package models

import play.api.libs.json._

case class Pedido (
    id: Option[Long],
    data: String,
    valor: Double,
    pagamento: Pagamento,
    status: Status,
    areaCalculavel: AreaCalculavel,
    cliente: Cliente    
){
  
  def this(data: String,
		   valor: Double,
		   status: Status,
		   areaCalculavel: AreaCalculavel,
		   cliente: Cliente)
  	= this(null, data, valor, null, status, areaCalculavel, cliente)
  	
  def this(valor: Double, areaSimulacao: Double, arquivoInicial: String)
  	= this(null, null, valor, null, null, new AreaCalculavel(areaSimulacao, arquivoInicial), null)
}
