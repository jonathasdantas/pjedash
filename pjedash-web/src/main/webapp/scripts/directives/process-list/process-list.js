'use strict';

/**
 * @ngdoc directive
 * @name pjedash.app.directive:process-list
 * @description
 * # process-list
 */
function ProcessList() {
	return {
		replace : 'true',
		scope : {
			process: '=',
			callback: '&'
		},
		templateUrl : './scripts/directives/process-list/process-list.html',
		restrict : 'E',
		link: function (scope, element) {
			scope.selectProcess = function (process) {
				scope.callback({
					processo: process
				});
			}
		}
	};
}

angular.module('pjedash.app').directive('processList', [ProcessList]);