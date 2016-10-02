'use strict';

/**
 * @ngdoc service
 * @name pjedash.app.menu
 * @description
 * # menu
 * Service in the pjedash.app.
 */
angular.module('pjedash.services')
	.factory('ProcessService', function ($resource, ProcessPartsService, ProcessMovesService, PjeDashConstants, npuFilter) {
		var processData = $resource(PjeDashConstants.SERVICE.SERVICE_ROOT + '/processos', {},
        {
        	getDadosProcesso: {
        		url: PjeDashConstants.SERVICE.SERVICE_ROOT + '/processos/dados/:npu',
        		method: 'GET',
        		params: {
        			npu: "@npu"
        		},
        		isArray: true
        	}
        });
		
		function getProcessData(process) {
			processData.getDadosProcesso({ npu: npuFilter(process.numero) }, function (response) {
				if (response.length > 0) {
					process.classe = response[0].classeJudicial,
					process.valorCausa = response[0].valorCausa;
					process.dataAutuacao = response[0].dataAutuacao;
					process.dataDistribuicao = response[0].dataDistribuicao;
				}
			});
			
			return process;
		}
		
		function getProcessParts(process) {
			process.partes = [];
			
			ProcessPartsService.getPartesProcesso({ npu: npuFilter(process.numero) }, function (response) {
				angular.forEach(response, function (e, key) {
					process.partes.push({
						id: e.id,
						login: e.login,
						nome: e.nome,
						polo: e.polo === 'A' ? 'Ativo' : (e.polo === 'P' ? 'Passivo' : 'Advogado') 
					});
				});
			});
		}
		
		function getProcessMoves(process) {
			process.movimentos = [];
			
			ProcessMovesService.getMovimentosProcesso({ npu: npuFilter(process.numero), qtdMov: 10 }, function (response) {
				angular.forEach(response, function (e, key) {
					process.movimentos.push({
						id: e.movimento,
						tipo: e.evento,
						texto: e.textoFinal, 
						data: e.data
					});
				});
			});
		}
		
		function removeNpuMask(npu) {
			return npu.replace(/[\.-]/g, "");	
		}
		
		function getProcess(process) {
			if (!process.classe || !process.valorCausa || !process.dataAutuacao || !process.dataDistribuicao) {
				// Carrega os dados do processo
				getProcessData(process);
			}
			
			if (!process.partes || process.partes.length == 0) {
				// Carrega as partes do processo
				getProcessParts(process);
			}
			
			if (!process.movimentos || process.movimentos.length == 0) {
				// Carrega os movimentos do processo
				getProcessMoves(process);
			}
		}
		
        var self = {
			getProcess: getProcess,
			getProcessData: getProcessData,
			processData: processData
		};

		return self;

      });