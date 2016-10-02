'use strict';

/**
 * @ngdoc service
 * @name pjedash.services.annotation
 * @description
 * # annotation
 * Service in the pjedash.services.
 */
angular.module('pjedash.services')
	.factory('AnnotationService', function ($resource) {
        return $resource('/service/rs/anotacoes/:id', { id: "@id" },
        {
        	getFromProcess: {
        		url: '/service/rs/anotacoes/processo/:npuProcesso',
        		method: 'GET',
        		params: { npuProcesso: "@npuProcesso" },
        		isArray: true
        	},
        	getFromText: {
        		url: '/service/rs/anotacoes/orgaoJulgador/:orgaoJulgador/:texto',
        		method: 'GET',
        		params: { orgaoJulgador: "@orgaoJulgador", texto: "@texto" },
        		isArray: true
        	},
        	getMostRecently: {
        		url: '/service/rs/anotacoes/orgaoJulgador/:orgaoJulgador/ultimas',
        		method: 'GET',
        		params: { orgaoJulgador: "@orgaoJulgador" },
        		isArray: true
        	},
        	update :{
        		url: '/service/rs/anotacoes/:id',
        		method: 'PUT'
        	},
        	
        });
	});
