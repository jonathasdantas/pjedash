'use strict';

function VariablesCtrl ($scope, $rootScope, $state, VariableService) {
	// Variável para ViewModel
	var vm = this;
	var type = $state.params.type;
	
	var currentYear = new Date().getFullYear();
	var currentMonth = new Date().getMonth() + 1;
	
	var currentOJ = $rootScope.currentUser.orgaos[0].id;
	
	$scope.filter = {
		selectedMonth: currentMonth,
		selectedYear: currentYear - 1
	}
	
	$scope.months = ['Todos', 'Janeiro', 'Fevereiro', 'Março', 'Abril', 'Maio', 'Junho', 'Julho', 'Agosto', 'Setembro', 'Outubro', 'Novembro', 'Dezembro'];
	$scope.years = [currentYear - 15, currentYear];
	
	$scope.variables = [];
	
											//---------------------------------//
											//----------- FUNÇÕES -------------//
											//---------------------------------//
	
	$rootScope.$watch(function() { return $scope.filter.selectedMonth; }, function () {
		updateVariables();
	});
	
	$rootScope.$watch(function() { return $scope.filter.selectedYear; }, function () {
		updateVariables();
	});
	
	function updateVariables() {
		if (type === 'newset') {
			$scope.variableTitle = 'Casos Novos';
			getVariable('CASOS NOVOS');
		} else if (type === 'sentences') {
			$scope.variableTitle = 'Sentenças';
			getVariable('SENTENCA');
		} else if (type === 'archived') {
			$scope.variableTitle = 'Arquivados';
			getVariable('BAIXADOS');			
		} else if (type === 'timeToArchive') {
			$scope.variableTitle = 'Tempo para Baixa';
			getVariable('TEMPO BAIXADOS');
		}
	}
	
	function getVariable(indicator) {
		VariableService.getVariable({ tipoIndicador: indicator, orgaoJulgador: currentOJ, ano: $scope.filter.selectedYear, mes: $scope.filter.selectedMonth }, function (response) {
			$scope.variables = [];
			if ($scope.variables.length < 1) {
				angular.forEach(response, function (e, key) {
					$scope.variables.push({
						descricao: e.descricao,
						indice: e.indice,
						sigla: e.sigla,
						id: e.id
					});
				});
			}
		});
	}
}

angular.module('pjedash.app').controller('VariablesCtrl', ['$scope', '$rootScope', '$state', 'VariableService', VariablesCtrl]);