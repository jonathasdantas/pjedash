'use strict';

/**
 * @ngdoc service
 * @name pjedash.services.judgingBody
 * @description
 * # processes
 * Service in the pjedash.judgingBody.
 */
angular.module('pjedash.services')
	.factory('JudgingBodyService', function ($resource, PjeDashConstants) {
        return $resource(PjeDashConstants.SERVICE.SERVICE_ROOT + '/pje/restPje2/orgaoJulgador/', {},
        {
        	getOrgaosFromUser: {
        		url: PjeDashConstants.SERVICE.SERVICE_ROOT + '/orgaoJulgador/login/:idUsuario',
        		method: 'GET',
        		params: {
        			orgaoJulgador: "@idUsuario"
        		},
        		isArray: true
        	}
        });
	});
