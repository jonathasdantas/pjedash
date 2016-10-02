'use strict';

/**
 * Retorna um range de inteiros
 * 
 * @param input
 * @returns
 */

function makeRange(input) {
	var lowBound, highBound;
    
    switch (input.length) {
    case 1:
        lowBound = 0;
        highBound = parseInt(input[0]) - 1;
        break;
    case 2:
        lowBound = parseInt(input[0]);
        highBound = parseInt(input[1]);
        break;
    default:
        return input;
    }
    
    var result = [];
    
    for (var i = lowBound; i <= highBound; i++) {
    	result.push(i);
    }
    
    return result;
}

function MakeRange () {
	return makeRange;
}

angular.module('pjedash.filters').filter('makeRange', MakeRange);