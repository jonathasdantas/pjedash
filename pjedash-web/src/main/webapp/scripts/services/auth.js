'use strict';

/**
 * @ngdoc service
 * @name pjedash.services.processes
 * @description
 * # processes
 * Service in the pjedash.services.
 */
angular.module('pjedash.services')
	.factory('AuthService', function ($resource, PjeDashConstants) {		
        return $resource('', {},
        {
        	getUsuarioLogado: {
        		url: PjeDashConstants.SERVICE.SERVICE_ROOT + PjeDashConstants.SERVICE.LOGGED_USER,
        		method: 'GET'
        	}
        });
	});
