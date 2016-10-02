'use strict';

/**
 * @ngdoc function
 * @name pjedash.app.controller:MenuCtrl
 * @description
 * # MenuCtrl
 * Controller of the pjedash.app
 */
function MenuCtrl ($mdSidenav, Menu, AlertService, $rootScope, $interval) {
	var controller = this;

	// Menu principal
	controller.isMenuLateralOpen = function(){
		return $mdSidenav('menuLateral').isOpen();
	};

	controller.toggleMenuLateral = function () {
 		$mdSidenav('menuLateral').toggle();
	};
	
	controller.isMenuNotificacaoOpen = function(){
		return $mdSidenav('menuNotificacao').isOpen();
	};

	controller.toggleMenuNotificacao = function () {
		if (!$mdSidenav('menuNotificacao').isOpen()) {
			updateAlertHistory();
			
			// Marca todas as notificações como visualizadas
			AlertService.service.setHistoricoVisualizados({ idUsuario: $rootScope.currentUser.id }, function (response) {
				updateNotificationsCount();
			});
		}
		
 		$mdSidenav('menuNotificacao').toggle();
	};

	controller.alertsHistory = [];
	controller.notificationCount = 0;
	
	// Itens do Menu
	controller.menu = Menu;

	controller.autoFocusContent = false;
    
    controller.status = {
      isFirstOpen: true,
      isFirstDisabled: false
    };

	controller.isOpen = function (section) {
		return Menu.isSectionSelected(section);
	};

	controller.toggleOpen = function(section) {
      return Menu.toggleSelectSection(section);
    };
    
    function updateAlertHistory() {
    	controller.alertsHistory = [];
    	
    	AlertService.service.getHistoricoAlertasPorUsuario({ idUsuario: $rootScope.currentUser.id }, function (response) {
    		angular.forEach(response, function (e, key) {
				var date = new Date(e.dataOcorrencia);
				var dateToday = new Date();
				dateToday.setHours(0,0,0,0);
				
				if (date >= dateToday) {
					controller.alertsHistory.push(e);
				}
    		});
    	});
	}
    
    function updateNotificationsCount() {
    	AlertService.service.getNumeroNaoVisualizados({ idUsuario: $rootScope.currentUser.id }, function (response) {
    		controller.notificationCount = response.alertasNaoVisualizados;
    	});
    }
    
    // Job que verifica se alertas foram gerados
    function startAlertCheck() {
    	updateNotificationsCount();
    	
    	if ($rootScope.alertsJob == null || $rootScope.alertsJob == undefined) {
    		$rootScope.alertsJob = $interval(function(){
    			AlertService.checkAlerts();
    			updateNotificationsCount();
            }, 15000);
    	}
    }
    
    startAlertCheck();
}

angular.module('pjedash.app').controller('MenuCtrl', ['$mdSidenav', 'Menu', 'AlertService', '$rootScope', '$interval', MenuCtrl]);