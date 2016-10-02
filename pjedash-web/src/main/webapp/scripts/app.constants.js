'use strict';

/**
 * @ngdoc overview
 * @name pjedash.filters
 * @description
 * # pjedash.filters
 *
 * Main module of the application.
 */
angular.module('pjedash.constants', [])
	.constant('PjeDashConstants', {
		'SERVICE': {
			'SERVICE_ROOT': '/service/rs',
			'SERVICE_PJE2_ROOT': '/pje/restPje2',
			'AUTH': '/autenticacao/login',
			'LOGGED_USER': '/autenticacao/usuarioLogado'
		},
		'STATE': {
			'LOGIN': 'login'
		}
	});