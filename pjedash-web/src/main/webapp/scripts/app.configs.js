'use strict';

/**
 * @ngdoc overview
 * @name pjedash.configs
 * @description
 * # pjedash.configs
 *
 * Main module of the application.
 */
angular.module('pjedash.configs', ['ngMaterial'])
	.config(function($authProvider, PjeDashConstants) {
		$authProvider.cordova = false;
		$authProvider.loginUrl = PjeDashConstants.SERVICE.SERVICE_ROOT + PjeDashConstants.SERVICE.AUTH;
	})
	
	.config(function($mdThemingProvider) {
		$mdThemingProvider.theme('default')
			.primaryPalette('indigo')
			.accentPalette('orange');
	
		$mdThemingProvider.theme('secondary')
			.primaryPalette('grey')
			.accentPalette('orange');
		
		$mdThemingProvider.theme('title')
			.primaryPalette('teal')
			.accentPalette('grey');
		
		$mdThemingProvider.theme('titleSecondary')
			.primaryPalette('deep-orange')
			.accentPalette('grey');
	})
	
	.config(function($stateProvider, $urlRouterProvider, $authProvider, $httpProvider, $provide, PjeDashConstants) {	
		function redirectWhenLoggedOut($q, $injector) {

			return {
				responseError: function(rejection) {

					var $state = $injector.get('$state');

					// caso o código de status esteja entre 400 e 500 (não autorizado, acesso negado, etc)
					// redireciona para login
					if(rejection.status >= 400 && rejection.status < 500  ) {
						localStorage.removeItem('user');
						$state.go(PjeDashConstants.STATE.LOGIN);
					}

					return $q.reject(rejection);
				}
			}
		}

		// Configura um $httpInterceptor
		$provide.factory('redirectWhenLoggedOut', redirectWhenLoggedOut);

		// Adiciona no $http interceptor array
		$httpProvider.interceptors.push('redirectWhenLoggedOut');
	});