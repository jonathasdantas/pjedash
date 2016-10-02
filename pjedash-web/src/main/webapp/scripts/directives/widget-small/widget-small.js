'use strict';

/**
 * @ngdoc directive
 * @name pjedash.app.directive:widget-small
 * @description
 * # widget-small
 */
function widgetSmall($mdDialog) {
	var colors = ['aqua', 'green', 'red', 'yellow'];
	
	return {
		replace : 'true',
		scope : {
			icon : '@',
			value : '@',
			label : '@',
			color: '@',
			initials: '@',
			id: '@'
		},
		templateUrl : './scripts/directives/widget-small/widget-small.html',
		restrict : 'E',
		link: function (scope, element) {
			if (scope.color === undefined || scope.color === null) {
				element.addClass(colors[Math.floor(Math.random() * (colors.length))]);
			} else {
				element.addClass(scope.color);
			}
			
			// Mostra um modal para criação dos alertas
			scope.showAlertDialog = function (ev) {
				$mdDialog.show({
					controller: AlertsCtrl,
					controllerAs: 'alert',
					templateUrl: 'scripts/directives/widget-small/alert-dialog-template.html',
					parent: angular.element(document.body),
					targetEvent: ev,
					clickOutsideToClose: true,
					// Passa as informações ao controller
					locals: {
						alert: {
							description: scope.label,
							initials: scope.initials,
							id: scope.id,
							value: scope.value
						}
					},
					bindToController: true
				});
			};
		}
	};
}

angular.module('pjedash.app').directive('widgetSmall', ['$mdDialog', widgetSmall]);