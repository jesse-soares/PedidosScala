'use strict';

var estacionamentoApp = angular.module('estacionamentoApp', [])
	.config(['$routeProvider', function($routeProvider){
		'use strict';

		$routeProvider
			.when('/', {
				templateUrl: 'views/Home.html'
			})
			.when('/novaSimulacao', {
				templateUrl: 'views/CriarPedido.html',
				controller: 'CriarPedidoCtrl'
			})
			.when('/pedidos', {
				templateUrl: 'views/Pedidos.html',
				controller: 'ListarPedidosCtrl'
			})
			.when('/pedido/?:id', {
				templateUrl: 'views/Pedido.html',
				controller: 'VerPedidoCtrl'
			})
			.otherwise({
				redirectTo: '/'
			})
	}]);