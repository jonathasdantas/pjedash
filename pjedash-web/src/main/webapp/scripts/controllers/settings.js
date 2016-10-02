'use strict';

function SettingsCtrl ($scope, $rootScope, $interval, SettingService) {
	// Variável para ViewModel
	var vm = this;
	
	var currentOJ = 108;
	
	vm.sections = [];
	
											//---------------------------------//
											//----------- FUNÇÕES -------------//
											//---------------------------------//
	
	
	
	function saveSetting(setting) {
		SettingService.getSettingByOJ({
			'idOrgaoJulgador': setting.idOrgaoJulgador,
			'idConfiguracao': setting.configuracao.id
		}, function (response) {
			if (response.valor && response.id) {
				// Configuração já existe
				setting.id = response.id;
				
				SettingService.update(setting, function (response) {
					console.log("UPDATE");
				});
			} else {
				// Nova Configuração
				var newSettingObj = new SettingService(setting);
				
				newSettingObj.$save(function (response) {
					console.log("SAVE");
				});
				
			}
		});
	}
	
	function getSettings() {
		SettingService.getAllSections(function (response) {
			angular.forEach(response, function (e, key) {
				SettingService.getBySection({idSecao: e.id}, function (response) {
					e.settings = response;
				});
				
				vm.sections.push(e);
			});
		});
	}
	
	getSettings();
	vm.saveSetting = saveSetting;
}

angular.module('pjedash.app').controller('SettingsCtrl', ['$scope', '$rootScope', '$interval', 'SettingService', SettingsCtrl]);