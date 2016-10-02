'use strict';

/**
 * @ngdoc service
 * @name pjedash.services.indicators
 * @description
 * # indicators
 * Service in the pjedash.services.
 */
angular.module('pjedash.services')
	.factory('IndicatorService', function ($resource, PjeDashConstants) {
        return $resource(PjeDashConstants.SERVICE.SERVICE_ROOT + '/indicadores/', {},
        {
        	getIAD: {
        		url: PjeDashConstants.SERVICE.SERVICE_ROOT + '/indicadores/indiceAtendimentoDemanda/:orgaoJulgador/:ano/:mes',
        		method: 'GET',
        		params: {
        			orgaoJulgador: "@orgaoJulgador",
        			ano: "@ano",
        			mes: "@mes"
        		},
        		isArray: true
        	},
        	getNB: {
        		url: PjeDashConstants.SERVICE.SERVICE_ROOT + '/indicadores/novosBaixadosSentencas/:orgaoJulgador/:ano/:mes',
        		method: 'GET',
        		params: {
        			orgaoJulgador: "@orgaoJulgador",
        			ano: "@ano",
        			mes: "@mes"
        		},
        		isArray: true
        	},
        	getTaxaCongestionamento: {
        		url: PjeDashConstants.SERVICE.SERVICE_ROOT + '/indicadores/taxaCongestionamento/:orgaoJulgador/:ano/:mes',
        		method: 'GET',
        		params: {
        			orgaoJulgador: "@orgaoJulgador",
        			ano: "@ano",
        			mes: "@mes"
        		},
        		isArray: true
        	}
        });
	});
