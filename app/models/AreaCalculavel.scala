package models

case class AreaCalculavel(
    id: Option[Long], 
    areaSimulacao: Double,
    areaReal: Option[Double],
    arquivoInicial: String,
    arquivoFinal: Option[String]
){
  
  def this(areaSimulacao: Double, arquivoInicial: String) = this(null, areaSimulacao, null, arquivoInicial, null)
  
}

