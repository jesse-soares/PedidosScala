# --- First database schema
 
# --- !Ups

CREATE TABLE AreaCalculavel(
  id                       	SERIAL PRIMARY KEY,
  areaSimulacao            	DOUBLE NOT NULL,
  areaReal					DOUBLE,
  arquivoInicial			VARCHAR(4000),
  arquivoFinal				VARCHAR(4000)
);

CREATE TABLE Cliente(
  id                        SERIAL PRIMARY KEY,
  cpf                       VARCHAR(15) NOT NULL,
  nome                      VARCHAR(255) NOT NULL,
  endereco					VARCHAR(255),
  cidade					VARCHAR(255),
  estado					VARCHAR(255),
  email						VARCHAR(255) NOT NULL
);

CREATE TABLE Estacionamento(
  id	                    SERIAL PRIMARY KEY,
  idAreaCalculavel			INTEGER NOT NULL
);


CREATE TABLE EstacionamentoParametro(
  id						SERIAL PRIMARY KEY,
  idEstacionamento         	INTEGER NOT NULL,
  idParametro		        INTEGER NOT NULL,
  valor						DOUBLE	NOT NULL
);

CREATE TABLE FormaPagamento(
  id                        SERIAL PRIMARY KEY,
  nome                      VARCHAR(255) NOT NULL
);

CREATE TABLE Pagamento(
  id                        SERIAL PRIMARY KEY,
  data                      VARCHAR(20) NOT NULL,
  idFormaPagamento			INTEGER NOT NULL
);

CREATE TABLE Parametro(
  id                        SERIAL PRIMARY KEY,
  nome                      VARCHAR(255) NOT NULL
);

CREATE TABLE Pedido(
  id                        SERIAL PRIMARY KEY,
  idCliente                	INTEGER NOT NULL,
  data						VARCHAR(20) NOT NULL,
  idStatus					INTEGER NOT NULL,
  valor						DOUBLE NOT NULL,
  idAreacalculavel			INTEGER NOT NULL,
  idPagamento				INTEGER 
);

CREATE TABLE Status(
  id                        SERIAL PRIMARY KEY,
  nome                      VARCHAR(255) NOT NULL
);

ALTER TABLE Estacionamento ADD CONSTRAINT fk_e_id_area_calculavel FOREIGN KEY (idAreaCalculavel) REFERENCES AreaCalculavel (id) ON DELETE RESTRICT ON UPDATE RESTRICT;
ALTER TABLE EstacionamentoParametro ADD CONSTRAINT fk_ep_id_estacionamento FOREIGN KEY (idEstacionamento) REFERENCES Estacionamento (id) ON DELETE RESTRICT ON UPDATE RESTRICT;
ALTER TABLE EstacionamentoParametro ADD CONSTRAINT fk_ep_id_parametro FOREIGN KEY (idParametro) REFERENCES Parametro (id) ON DELETE RESTRICT ON UPDATE RESTRICT;
ALTER TABLE Pagamento ADD CONSTRAINT fk_p_id_formapagamento FOREIGN KEY (idFormaPagamento) REFERENCES FormaPagamento (id) ON DELETE RESTRICT ON UPDATE RESTRICT;
ALTER TABLE Pedido ADD CONSTRAINT fk_p_id_cliente FOREIGN KEY (idCliente) REFERENCES Cliente (id) ON DELETE RESTRICT ON UPDATE RESTRICT;
ALTER TABLE Pedido ADD CONSTRAINT fk_p_id_status FOREIGN KEY (idStatus) REFERENCES Status (id) ON DELETE RESTRICT ON UPDATE RESTRICT;
ALTER TABLE Pedido ADD CONSTRAINT fk_p_id_areacalculavel FOREIGN KEY (idAreaCalculavel) REFERENCES AreaCalculavel (id) ON DELETE RESTRICT ON UPDATE RESTRICT;

# --- !Downs
 
DROP TABLE IF EXISTS Pedido;

DROP TABLE IF EXISTS AreaCalculavel;

DROP TABLE IF EXISTS Cliente;

DROP TABLE IF EXISTS Estacionamento;

DROP TABLE IF EXISTS EstacionamentoParametro;

DROP TABLE IF EXISTS FormaPagamento;

DROP TABLE IF EXISTS Pagamento;

DROP TABLE IF EXISTS Parametro;

DROP TABLE IF EXISTS Pedido;

DROP TABLE IF EXISTS Status;