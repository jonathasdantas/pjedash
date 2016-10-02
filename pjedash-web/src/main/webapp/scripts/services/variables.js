'use strict';

/**
 * @ngdoc service
 * @name pjedash.services.variables
 * @description
 * # variables
 * Service in the pjedash.services.
 */
angular.module('pjedash.services')
	.factory('VariableService', function ($resource, PjeDashConstants) {
        return $resource(PjeDashConstants.SERVICE.SERVICE_ROOT + '/indicadores/', {},
        {
        	getVariable: {
        		url: PjeDashConstants.SERVICE.SERVICE_ROOT + '/indicadores/variavel/:tipoIndicador/:orgaoJulgador/:ano/:mes',
        		method: 'GET',
        		params: {
        			tipoIndicador: "@tipoIndicador",
        			orgaoJulgador: "@orgaoJulgador",
        			ano: "@ano",
        			mes: "@mes"
        		},
        		isArray: true
        	},
        	getVariavelPorSigla: {
        		url: PjeDashConstants.SERVICE.SERVICE_ROOT + '/indicadores/variavel/sigla/:sigla/:orgaoJulgador/:ano/:mes',
        		method: 'GET',
        		params: {
        			sigla: "@sigla",
        			orgaoJulgador: "@orgaoJulgador",
        			ano: "@ano",
        			mes: "@mes"
        		}
        	}
        });
	});
