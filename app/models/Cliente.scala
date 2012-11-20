package models

case class Cliente(
    id: Long, 
    cpf: String, 
    nome: String,
    endereco: String,
    cidade: String,
    estado: String,
    email: String
)
{
  
  def this(id: Long) = this(id, null, null, null, null, null, null)

}
