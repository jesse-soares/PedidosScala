package models

case class EstacionamentoParametro(
    id: Long,
    estacionamento: Estacionamento,
    parametro: Parametro,
    valor: Double
)
