'use strict';

/**
 * @ngdoc service
 * @name pjedash.services.tag
 * @description
 * # tag
 * Service in the pjedash.services.
 */
angular.module('pjedash.services')
	.factory('TagService', function ($resource) {
        return $resource('/service/rs/tags/:id/processo/:npuProcesso/usuario/:idUsuario', { id: "@id", npuProcesso: "@npuProcesso", idUsuario: "@idUsuario" },
        {
        	getFromUser: {
        		url: '/service/rs/tags/usuario/:idUsuario',
        		method: 'GET',
        		params: { idUsuario: "@idUsuario" },
        		isArray: true
        	},
        	update: {
        		url: '/service/rs/tags/:id',
        		method: 'PUT'
        	},
        	updateProcessoTag: {
        		url: '/service/rs/tags/:id/processo/:npuProcesso/usuario/:idUsuario',
        		params: { id: "@id", npuProcesso: "@npuProcesso", idUsuario: "@idUsuario" },
        		method: 'PUT'
        	},
        	getFromOJ: {
        		url: '/service/rs/tags/associacoes/orgao/:idOrgaoJulgador',
        		method: 'GET',
        		params: { idOrgaoJulgador: "@idOrgaoJulgador" },
        		isArray: true
        	},
        	getProcessosFromOJ: {
        		url: '/service/rs/tags/:id/processos/orgao/:idOrgaoJulgador',
        		method: 'GET',
        		params: { id: "@id", idOrgaoJulgador: "@idOrgaoJulgador" }
        	}
        });
	});
