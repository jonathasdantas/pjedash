'use strict';

/**
 * @ngdoc service
 * @name pjedash.services.alert
 * @description
 * # alert
 * Service in the pjedash.services.
 */
angular.module('pjedash.services').factory('AlertService', ['$resource', 'VariableService', '$rootScope', AlertService]);

function AlertService ($resource, VariableService, $rootScope) {
	var currentYear = new Date().getFullYear();
	var currentMonth = new Date().getMonth() + 1;
	
	var currentUser = $rootScope.currentUser.id;
	var currentOJ = 2;
	
	// Servico de Alerta e Historico de Alertas
	var resource = $resource('/service/rs/alertas/:id', {},
			{
		    	getAlertasPorUsuario: {
		    		url: '/service/rs/alertas/usuario/:idUsuario',
		    		method: 'GET',
		    		params: {
		    			idUsuario: "@idUsuario"
		    		},
		    		isArray: true
		    	},
		    	getHistoricoAlertasPorUsuario: {
		    		url: '/service/rs/historicosAlerta/usuario/:idUsuario',
		    		method: 'GET',
		    		params: {
		    			idUsuario: "@idUsuario"
		    		},
		    		isArray: true
		    	},
		    	getNumeroNaoVisualizados: {
		    		url: '/service/rs/historicosAlerta/usuario/:idUsuario/naoVisualizados',
		    		method: 'GET',
		    		params: {
		    			idUsuario: "@idUsuario"
		    		}
		    	},
		    	setHistoricoVisualizados: {
		    		url: '/service/rs/historicosAlerta/usuario/:idUsuario/marcarNaoVisualizados',
		    		method: 'GET',
		    		params: {
		    			idUsuario: "@idUsuario"
		    		}
		    	},
		    	getTiposAlerta: {
		    		url: '/service/rs/tiposAlerta/',
		    		method: 'GET',
		    		isArray: true
		    	},
		    	saveHistorico: {
		    		url: '/service/rs/historicosAlerta/',
		    		method: 'POST'
		    	}
		    }
		);
	
    return {
    	service: resource,
        checkAlerts:
        	// Verifica se os alertas foram atingidos
        	function () {
        		resource.getAlertasPorUsuario({ idUsuario: currentUser }, function (response) {
        			var alerts = response;
        			
        			angular.forEach(alerts, function (e, key) {
    	        		VariableService.getVariavelPorSigla({ sigla: e.variavelSigla, orgaoJulgador: currentOJ, ano: currentYear - 1, mes: currentMonth }, function (resp) {
    	        			var newHistorico =
    	        			{
								variavelId: e.variavelId,
								variavelSigla: e.variavelSigla,
								variavelDescricao: e.variavelDescricao,
								usuarioId: currentUser,
								orgaoJulgadorId: e.orgaoJulgadorId,
								tipoAlerta: {
									id: e.tipoAlerta.id
								},
								valorReferencia: e.valorReferencia,
								valorApurado: resp.indice,
								dataOcorrencia: new Date(),
								visualizado: false
							};
    	        			
    	        			if (e.tipoAlerta.tipo === 'MAIOR_QUE') {
    	        				if (resp.indice > e.valorReferencia) {
    	        					resource.saveHistorico(newHistorico);
    	        				}
    	        			} else if (e.tipoAlerta.tipo === 'MENOR_QUE') {
    	        				if (resp.indice < e.valorReferencia) {
    	        					resource.saveHistorico(newHistorico);
    	        				}
    	        			} else if (e.tipoAlerta.tipo === 'IGUAL') {
    	        				if (resp.indice === e.valorReferencia) {
    	        					resource.saveHistorico(newHistorico);
    	        				}
    	        			}
    	        		});
    	        	});
        		});
	        }
	};
}
		

