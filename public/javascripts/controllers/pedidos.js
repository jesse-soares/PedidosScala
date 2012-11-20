estacionamentoApp.controller('ListarPedidosCtrl', ['$scope', '$http', function(ng, $http) {
	'use strict';

	ng.pedidos = [];

	ng.listar = function() {

		ng.pedidos.splice(0, ng.pedidos.length);

		$http.get('/pedidos').then(function(result) {

			angular.forEach(result.data, function(pedido) {
				ng.pedidos.push(pedido);
			})
		});
	};

	ng.listar();

}]);

estacionamentoApp.controller('VerPedidoCtrl', ['$scope', '$http', '$routeParams', function(ng, $http, $routeParams) {
	'use strict';

	var ID_STATUS_AGUARDANDO_PAGAMENTO = 2;

	ng.pedido = {};

	ng.pedidoAguardandoPagamento = function() {
		return ng.pedido != null && ng.pedido.status != null && ng.pedido.status.id == ID_STATUS_AGUARDANDO_PAGAMENTO;
	}

	ng.ver = function() {

		$http.get('/pedidos/' + $routeParams.id).then(function(result) {
			ng.pedido = result.data;
		});
	};

	ng.realizarPagamento = function() {

		$http.get('/pedidos/realizaPagamento/' + this.pedido.id).then(function(result) {
			
			ng.ver()
		});

	}

	ng.ver();

}]);

estacionamentoApp.controller('CriarPedidoCtrl', ['$scope', '$http', '$location', function(ng, $http, $location) {
	'use strict';

	ng.error = false;

	ng.init = function() {
		ng.view = 'views/SimulacaoPreco.html';
	}

	ng.simularPreco = function() {

		ng.error = false;
		ng.area = this.area;

		if (!this.area) {
			mensagemErro("Área inválida!");
			return;
		}

		$http.get('/pedidos/simulaValor/' + this.area).then(function(result) {
			
			if (!result.data) {
				mensagemErro("Erro ao executar a operação!");
				return;
			}

			ng.preco = result.data;
			ng.view = 'views/ConfirmacaoSimulacao.html';
		});
		
	}

	ng.continuarPedido = function() {
		ng.view = 'views/EnvioArquivo.html';
	}

	ng.salvar = function() {

		ng.error = false;
		
		if (this.arquivo == "" || $.trim("this.arquivo") == "") {
			mensagemErro("Conteúdo vazio!");
			return;
		}

		$http.post('/pedidos?valor=' + ng.preco + '&areaSimulacao=' + ng.area + '&arquivoInicial=' + this.arquivo)
			.success(function(result) {

				if (!result) {
					mensagemErro("Erro ao cadastrar o pedido!");
					return;
				}

				$location.url('/pedido/' + result);
			})
			.error(function() {
				mensagemErro("Erro ao cadastrar o pedido!!");
			});
	}

	var mensagemErro = function(mensagem) {
		ng.error = true;
		ng.errorMessage = mensagem;
	}

}]);