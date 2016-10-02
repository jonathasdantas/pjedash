'use strict';

/**
 * @ngdoc service
 * @name pjedash.services.processes
 * @description
 * # processes
 * Service in the pjedash.services.
 */
angular.module('pjedash.services')
	.factory('AggregatorService', function ($resource, PjeDashConstants) {
        return $resource(PjeDashConstants.SERVICE.SERVICE_ROOT + '/processos/', {},
        {
        	getProcessosConclusos: {
        		url: PjeDashConstants.SERVICE.SERVICE_ROOT + '/processos/conclusos/:orgaoJulgador/:qtdDias',
        		method: 'GET',
        		params: {
        			orgaoJulgador: "@orgaoJulgador",
        			qtdDias: "@qtdDias"
        		},
        		isArray: true
        	},
        	getProcessosPendentes: {
        		url: PjeDashConstants.SERVICE.SERVICE_ROOT + '/processos/pendentes/:orgaoJulgador/:qtdDias',
        		method: 'GET',
        		params: {
        			orgaoJulgador: "@orgaoJulgador",
        			qtdDias: "@qtdDias"
        		},
        		isArray: true
        	}
        });
	});
