'use strict';

function TagsCtrl ($scope, $stateParams, $rootScope, $interval, $location, $anchorScroll, TagService, ProcessService) {
	// Variável para ViewModel
	var vm = this;
	
	var currentOJ = 108;

	vm.tags = [];
	vm.totalHits = 0;
	vm.colors = ["#800026", "#bd0026", "#e31a1c", "#fc4e2a", "#fd8d3c", "#feb24c", "#fed976", "#ffeda0", "#ffffcc"];
	
	$scope.annotationResult = [];
	$scope.processesResult = [];
	
											//---------------------------------//
											//----------- FUNÇÕES -------------//
											//---------------------------------//
	
	function getTagsStats() {
		TagService.getFromOJ({ idOrgaoJulgador: currentOJ }, function (response) {
			angular.forEach(response, function (e, key) {
				vm.tags.push({
					text: e.tag.descricao,
					weight: e.hits,
					link: "./#/tags/" + e.tag.id
				});
				vm.totalHits = vm.totalHits + e.hits;
			});
			
			// Correção de exibição oculta
			$interval(function(){
                window.dispatchEvent(new Event('resize'));
            }, 50, 1);
		});
	}
	
	function scrollToResult() {
		$location.hash("annotation-search-result");
		$anchorScroll();
	}
	
	function getProcessFromTag(tag) {
		TagService.getProcessosFromOJ({ id: tag, idOrgaoJulgador: currentOJ }, function (response) {
			$scope.processesResult = [];
			
			angular.forEach(response.npus, function (e, key) {
				$scope.processesResult.push(ProcessService.getProcessData({
					id: null,
					numero: e,
					classe: '',
					partes: [],
					movimentos: [],
					status: { open : false }
				}));
			});
		});
	}
	
	function selectProcess(processo) {
		if (!processo.status.open) {
			// Carrega os dados do processo, partes e movimentos
			ProcessService.getProcess(processo);
		}
	}
	
	$rootScope.selectProcess = selectProcess;
	
	$rootScope.$on('$viewContentLoaded', function(event, toState, toParams, fromState, fromParams) {
		if ($stateParams.tag) {
			getProcessFromTag($stateParams.tag);			
		}
	});
	
	getTagsStats();
}

angular.module('pjedash.app').controller('TagsCtrl', ['$scope', '$stateParams', '$rootScope', '$interval', '$location', '$anchorScroll', 'TagService', 'ProcessService', TagsCtrl]);