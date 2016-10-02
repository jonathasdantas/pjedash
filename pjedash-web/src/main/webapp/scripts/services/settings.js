'use strict';

/**
 * @ngdoc service
 * @name pjedash.services.setting
 * @description
 * # setting
 * Service in the pjedash.services.
 */
angular.module('pjedash.services')
	.factory('SettingService', function ($resource) {
        return $resource('/service/rs/configuracoesOrgao/:id', { id: "@id" },
        {
        	getAllSections: {
        		url: '/service/rs/secoes',
        		method: 'GET',
        		isArray: true
        	},
        	getBySection: {
        		url: '/service/rs/configuracoes/secao/:idSecao',
        		method: 'GET',
        		params: { idSecao: "@idSecao" },
        		isArray: true
        	},
        	getSettingByOJ: {
        		url: '/service/rs/configuracoesOrgao/orgao/:idOrgaoJulgador/configuracao/:idConfiguracao',
        		method: 'GET',
        		params: {
        			idOrgaoJulgador: "@idOrgaoJulgador",
        			idConfiguracao: "@idConfiguracao"
				},
        	},
        	update: {
        		url: '/service/rs/configuracoesOrgao/:id',
        		method: 'PUT'
        	}
        });
	});
