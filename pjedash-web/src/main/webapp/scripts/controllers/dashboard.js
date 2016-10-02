'use strict';

/**
 * @ngdoc function
 * @name pjedash.app.controller:DashboardCtrl
 * @description
 * # DashboardCtrl
 * Controller of the pjedash.app
 */

function DashboardCtrl (IndicatorService, VariableService, AlertService, $rootScope) {
	var controller = this;
	
	var currentYear = new Date().getFullYear();
	var currentMonth = new Date().getMonth() + 1;
	
	var currentOJ = 2;
	
	controller.months = ['Todos', 'Janeiro', 'Fevereiro', 'Março', 'Abril', 'Maio', 'Junho', 'Julho', 'Agosto', 'Setembro', 'Outubro', 'Novembro', 'Dezembro'];
	
	controller.chart1 = {
		type: 'line',
		data: [],
		labels: [],
		series: [],
		legend: true
	};
	
	controller.variables = [];
	controller.colors = ['aqua', 'green', 'red', 'yellow'];
	
	controller.chart2 = {
		type: 'pie',
		data: [[]],
		labels: [],
		legend: true
	};
	
	controller.table1 = {
		data: [],
		labels: []
	};
	
	controller.table2 = {
		data: [],
		labels: []
	};
	
	function initData() {
		// Chart 1
		IndicatorService.getIAD({ orgaoJulgador: 2, ano: currentYear - 1}, function (response) {
			controller.chart1.labels = [];
			controller.chart1.data = [[]];
			controller.chart1.series = ['IAD (%)'];
			
			controller.table1.labels = [];
			controller.table1.data = [[]];
			
			controller.chart1Title = 'Índice de Atendimento à Demanda';
			controller.chart1SubTitle = 'Ano de ' + (currentYear - 1);
			
			angular.forEach(response, function (e) {
				controller.chart1.labels.push(controller.months[e.mes]);
				controller.chart1.data[0].push((e.indice * 100).toFixed(2));
				
				controller.table1.labels.push(controller.months[e.mes]);
				controller.table1.data[0].push((e.indice * 100).toFixed(2) + '%');
			});
		});
		
		// Chart 2
		IndicatorService.getNB({ orgaoJulgador: 2, ano: currentYear - 1}, function (response) {
			controller.chart2.labels = [];
			controller.chart2.data = [];
			
			controller.table2.labels = [];
			controller.table2.data = [[],[]];
			
			controller.chart2Title = 'Casos Novos x Casos Baixados';
			controller.chart2SubTitle = controller.months[currentMonth] + ' de ' + (currentYear - 1);
			controller.table2SubTitle = 'Ano de ' + (currentYear - 1);
			
			angular.forEach(response, function (e) {
				if (e.mes === currentMonth) {
					controller.chart2.labels.push('Casos Novos');
					controller.chart2.labels.push('Casos Baixados');
					
					controller.chart2.data.push(e.casosNovos);
					controller.chart2.data.push(e.casosBaixados);
				}
				
				controller.table2.labels.push(controller.months[e.mes]);
				controller.table2.data[0].push(e.casosNovos);
				controller.table2.data[1].push(e.casosBaixados);
			});
		});
		
		// Variables
		getVariable('CASOS NOVOS');
		getVariable('SENTENCA');
		getVariable('BAIXADOS');
		getVariable('TEMPO BAIXADOS');
	}
	
	function getVariable(indicator) {
		VariableService.getVariable({ tipoIndicador: indicator, orgaoJulgador: currentOJ, ano: currentYear - 1, mes: currentMonth }, function (response) {
			angular.forEach(response, function (e, key) {
				// Preenche com 4 variaveis
				if (controller.variables.length < 4) {
					controller.variables.push({
						cor: controller.colors[controller.variables.length],
						descricao: e.descricao,
						indice: e.indice,
						sigla: e.sigla,
						id: e.id
					});
				}
			});
		});
		
	}
	
	initData();
}

angular.module('pjedash.app').controller('DashboardCtrl', ['IndicatorService', 'VariableService', 'AlertService', '$rootScope', DashboardCtrl]);