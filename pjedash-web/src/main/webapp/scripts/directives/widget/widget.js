'use strict';

/**
 * @ngdoc directive
 * @name pjedash.app.directive:widget
 * @description
 * # widget
 */

function widget($compile, $interval, FileSaver, Blob) {
	var colors = ['#00a65a', '#f39c12', '#00c0ef', '#dd4b39'];
	var colorsPie = ['#dd4b39', '#f39c12', '#00c0ef', '#00a65a'];
	  
    return {
    	replace: 'true',
    	scope: {
    		type: '@',
    		chart: '=',
    		table: '=',
    		title: '@',
    		subtitle: '@'
    	},
    	templateUrl: './scripts/directives/widget/widget.html',
    	restrict: 'E',
    	link: function (scope, element) {
    		if (scope.type === 'chart' && scope.chart.type === 'pie') {
    			scope.colors = colorsPie;
    		} else {
    			scope.colors = colors;
    		}
    		
    		// Caso seja do tipo gráfico adiciona o tipo de gráfico e recompila o DOM
    		if (scope.type === 'chart') {
    			var chart = element.find('canvas'); 
    			
    			chart.addClass('chart-' + scope.chart.type);
    			$compile(chart)(scope);
    			
    			// Correção para resize depois do compile
    			scope.$on('create', function (event, chart) {
    				chart.resize()
    				chart.render();
    				
    				$interval(function(){
    					// Remove a legenda
    					if (!scope.chart.legend) {
                        	element.find('chart-legend').remove();
                        }
    					
                        window.dispatchEvent(new Event('resize'));
                    }, 50, 20);
                });
    		}
    		
    		scope.exportarImagem = function() {
    			var chart = element.find('canvas'); 
    			chart[0].toBlob(function(blob) {
    			    FileSaver.saveAs(blob, scope.title + '.png');
    			});
    		}
    		
    		scope.exportarExcel = function() {
    			var table =  element.find('table'); 
    			var blob = new Blob(['<table>' + table.html() + '</table>'], {
                    type: "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=windows-1252;"
                });
    			FileSaver.saveAs(blob, scope.title + '.xls');
    		}
    	}
    };
}



angular.module('pjedash.app').directive('widget', ['$compile', '$interval', 'FileSaver', 'Blob', widget]);
