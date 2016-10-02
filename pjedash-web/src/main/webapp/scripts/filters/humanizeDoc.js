'use strict';

/**
 * Melhora a legibilidade colocando lowercase em string
 * 
 * @param numero
 * @returns
 */

function humanizeDoc(doc) {
	if (!doc) {
		return;
	}
	
	if (doc.type === 'directive') {
		return doc.name.replace(/([A-Z])/g, function ($1) {
			return '-' + $1.toLowerCase();
		});
	}

	return doc.label || doc.name;
}

function HumanizeDoc () {
	return humanizeDoc;
}

angular.module('pjedash.filters').filter('humanizeDoc', HumanizeDoc);