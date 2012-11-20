# --- Sample dataset

# --- !Ups

insert into AreaCalculavel (id,areaSimulacao,areaReal,arquivoInicial,arquivoFinal) values (1, 165.4, 150, 'Caminho arquivo', 'Caminho arquivo');
insert into AreaCalculavel (id,areaSimulacao,areaReal,arquivoInicial,arquivoFinal) values (2, 23.4, 24, 'Caminho arquivo', 'Caminho arquivo');
insert into AreaCalculavel (id,areaSimulacao,areaReal,arquivoInicial,arquivoFinal) values (3, 260, 300, 'Caminho arquivo', 'Caminho arquivo');
insert into AreaCalculavel (id,areaSimulacao,areaReal,arquivoInicial,arquivoFinal) values (4, 100, 103, 'Caminho arquivo', 'Caminho arquivo');
insert into AreaCalculavel (id,areaSimulacao,areaReal,arquivoInicial,arquivoFinal) values (5, 200, 205, 'Caminho arquivo', 'Caminho arquivo');

insert into Cliente (id, cpf, nome, endereco, cidade, estado, email) values (1, '123456789', 'Joao', 'R. Vinte e Tres', 'Lavras', 'MG', 'joao@gmail.com');
insert into Cliente (id, cpf, nome, endereco, cidade, estado, email) values (2, '234567891', 'Maria', 'Av. Alameda', 'Belo Horizonte', 'MG', 'maria@gmail.com');
insert into Cliente (id, cpf, nome, endereco, cidade, estado, email) values (3, '345678912', 'Carlos', 'Rua Jose Modesto', 'Ijaci', 'MG', 'carlos@gmail.com');
insert into Cliente (id, cpf, nome, endereco, cidade, estado, email) values (4, '456789123', 'Miguel', 'Rua Petrolina', 'Sao Paulo', 'SP', 'miguel@gmail.com');	

insert into Estacionamento (id, idAreaCalculavel) values (1, 1);
insert into Estacionamento (id, idAreaCalculavel) values (2, 2);
insert into Estacionamento (id, idAreaCalculavel) values (3, 3);
insert into Estacionamento (id, idAreaCalculavel) values (4, 4);
insert into Estacionamento (id, idAreaCalculavel) values (5, 5);

insert into FormaPagamento (id,nome) values (1, 'Cartao de Credito');
insert into FormaPagamento (id,nome) values (2, 'Boleto Bancario');
insert into FormaPagamento (id,nome) values (3, 'Dinheiro');

insert into Pagamento (id,data,idFormaPagamento) values (1, '10/10/2012', 1);
insert into Pagamento (id,data,idFormaPagamento) values (2, '23/10/2012', 2);
insert into Pagamento (id,data,idFormaPagamento) values (3, '01/11/2012', 1);
insert into Pagamento (id,data,idFormaPagamento) values (4, '30/10/2012', 3);

insert into Parametro (id,nome) values (1, 'Quantidade vagas para carros');
insert into Parametro (id,nome) values (2, 'Quantidade vagas para motos');
insert into Parametro (id,nome) values (3, 'Tamanho vaga para carro');
insert into Parametro (id,nome) values (4, 'Tamanho vaga para moto');

insert into EstacionamentoParametro (id, idEstacionamento, idParametro, valor) values (1, 1, 1, 10);
insert into EstacionamentoParametro (id, idEstacionamento, idParametro, valor) values (2, 1, 2, 10);
insert into EstacionamentoParametro (id, idEstacionamento, idParametro, valor) values (3, 1, 3, 2.0);
insert into EstacionamentoParametro (id, idEstacionamento, idParametro, valor) values (4, 2, 2, 30);
insert into EstacionamentoParametro (id, idEstacionamento, idParametro, valor) values (5, 2, 4, 1.0);
insert into EstacionamentoParametro (id, idEstacionamento, idParametro, valor) values (6, 3, 1, 35);
insert into EstacionamentoParametro (id, idEstacionamento, idParametro, valor) values (7, 3, 3, 1.5);

insert into Status (id,nome) values (1, 'Calculo da área em processamento');
insert into Status (id,nome) values (2, 'Area calculada/Aguardando pagamento');
insert into Status (id,nome) values (3, 'Calculo do estacionamento em processamento');
insert into Status (id,nome) values (4, 'Finalizado');

insert into Pedido (idCliente, data, idStatus, valor, idAreaCalculavel, idPagamento) values ( 1, '10/03/2012', 1, 50, 1, 1);
insert into Pedido (idCliente, data, idStatus, valor, idAreaCalculavel, idPagamento) values ( 1, '28/10/2012', 1, 20, 2, 1);
insert into Pedido (idCliente, data, idStatus, valor, idAreaCalculavel, idPagamento) values ( 2, '05/08/2012', 4, 70, 3, 1);
insert into Pedido (idCliente, data, idStatus, valor, idAreaCalculavel, idPagamento) values ( 3, '20/03/2012', 2, 130, 4, 2);
insert into Pedido (idCliente, data, idStatus, valor, idAreaCalculavel, idPagamento) values ( 4, '17/03/2012', 3, 47, 5, 3);


# --- !Downs

DELETE FROM Pedido;

DELETE FROM Cliente;

DELETE FROM EstacionamentoParametro;

DELETE FROM Estacionamento;

DELETE FROM Parametro;

DELETE FROM AreaCalculavel;

DELETE FROM Pagamento;

DELETE FROM FormaPagamento;

DELETE FROM Status;