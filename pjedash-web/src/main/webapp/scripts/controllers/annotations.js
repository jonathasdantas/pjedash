'use strict';

function AnnotationsCtrl ($scope, $rootScope, $interval, $location, $anchorScroll, AnnotationService, ProcessService, npuFilter) {
	// Variável para ViewModel
	var vm = this;
	var SEARCH_MIN_SIZE = 5;
	
	var currentOJ = 108;
	
	$scope.mostRecently = [];
	$scope.annotationResult = [];
	
	$scope.filterQuery = "";
	
											//---------------------------------//
											//----------- FUNÇÕES -------------//
											//---------------------------------//
	
	function getMostRecently() {
		AnnotationService.getMostRecently({ orgaoJulgador: currentOJ }, function (response) {
			angular.forEach(response, function (e, key) {
				$scope.mostRecently.push(fillClasseJudicial(e));
			});
		});
	}
	
	function getAnnotationFromText(text) {
		$scope.annotationResult = [];
		
		if (text && text.length >= SEARCH_MIN_SIZE) {
			AnnotationService.getFromText({ orgaoJulgador: currentOJ, texto: text }, function (response) {
				angular.forEach(response, function (e, key) {
					$scope.annotationResult.push(fillClasseJudicial(e));
				});
				
				$interval(function() {
					scrollToResult();				
				}, 200, 1);
			});
		}
	}
	
	function fillClasseJudicial(anotacao) {
		if (!anotacao.classeProcesso) {
			ProcessService.processData.getDadosProcesso({ npu: npuFilter(anotacao.npuProcesso) }, function (response) {
				if (response.length > 0) {
					anotacao.classeProcesso = response[0].classeJudicial;
				}
			});
		}
		
		return anotacao;
	}
	
	function scrollToResult() {
		$location.hash("annotation-search-result");
		$anchorScroll();
	}
	
	vm.getAnnotationFromText = getAnnotationFromText;
	getMostRecently();
}

angular.module('pjedash.app').controller('AnnotationsCtrl', ['$scope', '$rootScope', '$interval', '$location', '$anchorScroll', 'AnnotationService', 'ProcessService', 'npuFilter', AnnotationsCtrl]);