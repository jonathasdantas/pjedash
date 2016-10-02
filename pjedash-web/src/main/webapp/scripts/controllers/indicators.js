'use strict';

function IndicatorsCtrl ($scope, $rootScope, $state, IndicatorService) {
	// Variável para ViewModel
	var vm = this;
	var type = $state.params.type;
	var currentYear = new Date().getFullYear();
	
	var currentOJ = $rootScope.currentUser.orgaos[0].id;
	
	$scope.months = ['Todos', 'Janeiro', 'Fevereiro', 'Março', 'Abril', 'Maio', 'Junho', 'Julho', 'Agosto', 'Setembro', 'Outubro', 'Novembro', 'Dezembro'];
	$scope.years = [currentYear - 15, currentYear];
	
	$scope.selectedMonth = null;
	$scope.selectedYear = currentYear - 2;
	
	$scope.chart = {
		type: 'line',
		data: [],
		labels: [],
		series: [],
		legend: true
	};
	
	$scope.table = {
		data: [],
		labels: []
	};
											//---------------------------------//
											//----------- FUNÇÕES -------------//
											//---------------------------------//
	
	$rootScope.$watch(function() { return $scope.selectedYear; }, function () {	
		if (type === 'nb') {
			$scope.indicatorTitle = 'Casos Novos x Casos Baixados';
			updateNBSChart($scope.selectedMonth, $scope.selectedYear);
		} else if (type === 'iad') {
			$scope.indicatorTitle = 'Índice de Atendimento à Demanda';
			updateIADChart($scope.selectedMonth, $scope.selectedYear);
		} else if (type === 'sentences') {
			$scope.indicatorTitle = 'Sentenças Proferidas';
			updateNBSChart($scope.selectedMonth, $scope.selectedYear);
		} else if (type == 'congestion') {
			$scope.indicatorTitle = 'Taxa de Congestionamento';
			updateCongestionChart($scope.selectedMonth, $scope.selectedYear);
		}
	});
		
	function updateNBSChart(month, year) {
		IndicatorService.getNB({ orgaoJulgador: currentOJ, ano: year, mes: month}, function (response) {
			createNBSData(response);
		});
	}
	
	function createNBSData(data) {
		$scope.chart.labels = [];
		$scope.table.labels = [];
		
		if (type === 'sentences') {
			$scope.chart.data = [[]];
			$scope.chart.series = ['Senteças'];
			
			$scope.table.data = [[]];
		} else {
			$scope.chart.data = [[],[]];
			$scope.chart.series = ['Casos Novos', 'Casos Baixados'];
			
			$scope.table.data = [[],[]];
		}
		
		angular.forEach(data, function (e) {
			$scope.chart.labels.push($scope.months[e.mes]);
			$scope.table.labels.push($scope.months[e.mes]);
			
			if (type === 'sentences') {
				$scope.chart.data[0].push(e.sentencas);
				
				$scope.table.data[0].push(e.sentencas);
			} else {
				$scope.chart.data[0].push(e.casosNovos);
				$scope.chart.data[1].push(e.casosBaixados);
				
				$scope.table.data[0].push(e.casosNovos);
				$scope.table.data[1].push(e.casosBaixados);
			}
		});
	}
	
	function updateIADChart(month, year) {
		IndicatorService.getIAD({ orgaoJulgador: currentOJ, ano: year, mes: month}, function (response) {
			createIADData(response);
		});
	}
	
	function createIADData(data) {
		$scope.chart.labels = [];
		$scope.chart.data = [[],[]];
		$scope.chart.series = ['IAD (%)'];
		
		$scope.table.labels = [];
		$scope.table.data = [[]];
		
		angular.forEach(data, function (e) {
			$scope.chart.labels.push($scope.months[e.mes]);
			$scope.chart.data[0].push((e.indice * 100).toFixed(2));
			
			$scope.table.labels.push($scope.months[e.mes]);
			$scope.table.data[0].push((e.indice * 100).toFixed(2) + '%');
		});
	}
	
	function updateCongestionChart(month, year) {
		IndicatorService.getTaxaCongestionamento({ orgaoJulgador: currentOJ, ano: year, mes: month}, function (response) {
			createCongestionData(response);
		});
	}
	
	function createCongestionData(data) {
		$scope.chart.labels = [];
		$scope.chart.data = [[],[]];
		$scope.chart.series = ['Taxa (%)'];
		
		$scope.table.labels = [];
		$scope.table.data = [[]];
		
		angular.forEach(data, function (e) {
			$scope.chart.labels.push($scope.months[e.mes]);
			$scope.chart.data[0].push((e.taxa * 100).toFixed(2));
			
			$scope.table.labels.push($scope.months[e.mes]);
			$scope.table.data[0].push((e.taxa * 100).toFixed(2) + '%');
		});
	}
}

angular.module('pjedash.app').controller('IndicatorsCtrl', ['$scope', '$rootScope', '$state', 'IndicatorService', IndicatorsCtrl]);