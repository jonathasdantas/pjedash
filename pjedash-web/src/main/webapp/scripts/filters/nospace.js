'use strict';

/**
 * Retira os espacos de uma string
 * 
 * @param numero
 * @returns
 */

function applyNoSpace(str) {
	return (!str) ? '' : str.replace(/ /g, '');
}

function NoSpace () {
	return applyNoSpace;
}

angular.module('pjedash.filters').filter('nospace', NoSpace);