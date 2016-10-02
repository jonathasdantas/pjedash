'use strict';

/**
 * @ngdoc directive
 * @name pjedash.app.directive:annotation-card
 * @description
 * # annotation-card
 */
function settingItem($interval, $rootScope, SettingService) {
	return {
		replace : 'true',
		scope : {
			setting: '=',
			index: '@'
		},
		templateUrl : './scripts/directives/setting-item/setting-item.html',
		restrict : 'E',
		controller: 'SettingsCtrl',
		link: function (scope, element, attrs, controller) {
			var currentUser = $rootScope.currentUser;
			var currentOJ = 2;

			scope.selectedValue = undefined;
			scope.selectedFile = {};
			
			function getValue() {
				var value = undefined;
				
				if (scope.setting.tipoValor === 'INTEGER') {
					value = scope.selectedValue;
				} else if (scope.setting.tipoValor === 'IMAGE') {
					value = angular.toJson(scope.selectedFile);
				}
				
				return value;
			}
			
			// Caso seja do tipo Inteiro verificando se já tem configuração para o OJ salva
			if (scope.setting.tipoValor === 'INTEGER') {
				SettingService.getSettingByOJ({
					'idOrgaoJulgador': currentOJ,
					'idConfiguracao': scope.setting.id
				}, function (response) {
					// Configuração já existe
					if (response.valor && response.id) {
						scope.selectedValue = Number(response.valor);
					// Configuração pelo valor default
					} else {
						scope.selectedValue = Number(scope.setting.valorDefault);
					}
				});
			}
			
			// Verifica a mudança do parâmetro
			scope.$watch(getValue, function (newValue, oldValue) {
				if (newValue === oldValue) {
					return;
				}
				
				controller.saveSetting({
					'valor': getValue(),
					'configuracao': scope.setting,
					'idOrgaoJulgador': currentOJ,
					'usuarioUltimaAlteracao': currentUser.login
					
				});
			});
		}
	};
}

angular.module('pjedash.app').directive('settingItem', ['$interval', '$rootScope', 'SettingService', settingItem]);