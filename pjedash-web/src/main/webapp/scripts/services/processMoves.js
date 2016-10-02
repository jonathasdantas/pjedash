'use strict';

/**
 * @ngdoc service
 * @name pjedash.services.processMoves
 * @description
 * # processMoves
 * Service in the pjedash.services.
 */
angular.module('pjedash.services')
	.factory('ProcessMovesService', function ($resource, PjeDashConstants) {
        return $resource(PjeDashConstants.SERVICE.SERVICE_ROOT + '/movimentosProcesso/', {},
        {
        	getMovimentosProcesso: {
        		url: PjeDashConstants.SERVICE.SERVICE_ROOT + '/movimentosProcesso/:npu/:qtdMov',
        		method: 'GET',
        		params: {
        			npu: "@npu",
        			qtdMov: "@qtdMov"
        		},
        		isArray: true
        	}
        });
	});
