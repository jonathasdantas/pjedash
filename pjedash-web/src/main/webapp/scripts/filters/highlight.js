'use strict';

/**
 * Adiciona span com classe highlight aos termos encontrados
 * 
 * @param text, term
 * @returns
 */
function highlight(text, term) {
	if (term) {
		text = text.replace(new RegExp('('+term+')', 'gi'), '<span class="highlight">$1</span>');
	}
	
	return text;
}

function HighlightFilter () {
	return highlight;
}

angular.module('pjedash.filters').filter('highlight', HighlightFilter);