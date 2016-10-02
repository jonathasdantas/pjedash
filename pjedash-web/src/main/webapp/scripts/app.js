'use strict';

/**
 * @ngdoc overview
 * @name pjedash.app
 * @description
 * # pjedash.app
 *
 * Main module of the application.
 */
angular.module('pjedash.app', [
        'pjedash.constants',
        'pjedash.routers',
        'pjedash.configs',
	    'pjedash.services',
	    'pjedash.filters',
		'ngAnimate',
		'ngAria',
		'ngCookies',
		'ngSanitize',
		'ngMessages',
		'chart.js',
		'ui.bootstrap',
		'textAngular',
		'colorpicker.module',
		'ngFileSaver',
		'angular-jqcloud',
		'naif.base64'
	])
	// Verifica se o usuário já está logado e envia para página inicial
	.run(function($rootScope, $state, $auth, PjeDashConstants) {
		$rootScope.$on('$stateChangeStart', function(event, toState) {

			$rootScope.authenticated = $auth.isAuthenticated();
			
			if ($rootScope.authenticated) {
				
				$rootScope.currentUser = $auth.getPayload();
				
				if(toState.name === PjeDashConstants.STATE.LOGIN) {
					event.preventDefault();
					$state.go('overview');
				}		
			} else {
				
				if(toState.name !== PjeDashConstants.STATE.LOGIN) {
					event.preventDefault();
					$state.go(PjeDashConstants.STATE.LOGIN);
				}
			}
		});
	});
