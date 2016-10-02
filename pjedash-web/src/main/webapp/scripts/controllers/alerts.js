'use strict';

function AlertsCtrl ($scope, $rootScope, $state, $mdDialog, $mdToast, AlertService, VariableService) {
	// Variável para ViewModel
	var vm = this;
		
	var currentYear = new Date().getFullYear();
	var currentMonth = new Date().getMonth() + 1;
	
	var currentOJ = 2;
	
	vm.alerts = [];
	vm.alertsHistory = {
		today: [],
		past: []
	};
											//---------------------------------//
											//----------- FUNÇÕES -------------//
											//---------------------------------//
	
	function initData() {
		getAlerts();
		getAlertsHistory();
		getAlertTypes();
	}
	
	function getAlerts() {
		AlertService.service.getAlertasPorUsuario({ idUsuario: $rootScope.currentUser.id }, function (response) {
			vm.alerts = response;
		});
	}
	
	function getAlertsHistory() {
		AlertService.service.getHistoricoAlertasPorUsuario({ idUsuario: $rootScope.currentUser.id }, function (response) {
			angular.forEach(response, function (e, key) {
				var date = new Date(e.dataOcorrencia);
				var dateToday = new Date();
				dateToday.setHours(0,0,0,0);
				
				if (date < dateToday) {
					vm.alertsHistory.past.push(e);
				} else {
					vm.alertsHistory.today.push(e);
				}
			});
		});
	}
	
	function getAlertTypes() {
		AlertService.service.getTiposAlerta(function (response) {
			vm.alertTypes = response;
		});
	}
	
	function closeDialog() {
		$mdDialog.hide();
	}
	
	function saveAlert () {
		vm.newAlert.usuarioId = $rootScope.currentUser.id;
		vm.newAlert.orgaoJulgadorId = $rootScope.currentUser.orgaos[0].id;
		vm.newAlert.variavelId = vm.alert.id;
		vm.newAlert.variavelSigla = vm.alert.initials;
		vm.newAlert.variavelDescricao = vm.alert.description;
		
		var newAlertObj = new AlertService.service(vm.newAlert);
		newAlertObj.$save(function (response) {
			closeDialog();
			vm.newAlert = undefined;
			
			$mdToast.show(
				$mdToast.simple()
					.textContent('Alerta salvo com sucesso!')
					.hideDelay(2000)
			);
		});
	}
	
	function removeAlert(id) {
		AlertService.service.remove({ id: id }, function (response) {
			vm.alerts = [];
			getAlerts();
			
			$mdToast.show(
				$mdToast.simple()
					.textContent('Alerta removido com sucesso!')
					.hideDelay(2000)
			);
		});
	}
	
	vm.removeAlert = removeAlert;
	
	vm.closeDialog = closeDialog;
	
	vm.saveAlert = saveAlert;
	
	initData();
	
}

angular.module('pjedash.app').controller('AlertsCtrl', ['$scope', '$rootScope', '$state', '$mdDialog', '$mdToast', 'AlertService', 'VariableService', AlertsCtrl]);