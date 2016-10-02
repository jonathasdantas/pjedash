'use strict';

/**
 * @ngdoc service
 * @name pjedash.services.processParts
 * @description
 * # processParts
 * Service in the pjedash.services.
 */
angular.module('pjedash.services')
	.factory('ProcessPartsService', function ($resource, PjeDashConstants) {
        return $resource(PjeDashConstants.SERVICE.SERVICE_ROOT + '/processos/', {},
        {
        	getPartesProcesso: {
        		url: PjeDashConstants.SERVICE.SERVICE_ROOT + '/processos/partes/:npu',
        		method: 'GET',
        		params: {
        			npu: "@npu"
        		},
        		isArray: true
        	}
        });
	});
