'use strict';

/**
 * @ngdoc directive
 * @name pjedash.app.directive:annotation-card
 * @description
 * # annotation-card
 */
function annotationCard() {
	return {
		replace : 'true',
		scope : {
			data: '@',
			classeProcesso: '@',
			npuProcesso: '@',
			dataalteracao: '@',
			texto: '@',
			theme: '@',
			searchTerm: '@'
		},
		templateUrl : './scripts/directives/annotation-card/annotation-card.html',
		restrict : 'E',
		link: function (scope, element) {
			var themes = {
				primary: '#00A65A',
				secondary: '#939E99'
			}
			
			if (scope.theme !== null && scope.theme !== '') {
				element.find('md-card-title').css('background-color', themes[scope.theme]);
			}
		}
	};
}

angular.module('pjedash.app').directive('annotationCard', [annotationCard]);