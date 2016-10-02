'use strict';

/**
 * Aplica o padrão NNNNNNN-DD.AAAA.J.TR.OOOO
 * 
 * @param numero
 * @returns
 */
function removeNpuMask(npu) {
	return npu.replace(/[\.-]/g, "");	
}

function maskNpu(numero) {
	numero = removeNpuMask(numero);
	
	if (numero !== undefined) {
		numero = numero.slice(0, 7) + "-" +
		numero.slice(7, 9) + "." +
		numero.slice(9, 13) + "." +
		numero.slice(13, 14) + "." +
		numero.slice(14, 16) + "." +
		numero.slice(16); 
	}
	
	return numero;
}

function NpuFilter () {
	return maskNpu;
}

angular.module('pjedash.filters').filter('npu', NpuFilter);