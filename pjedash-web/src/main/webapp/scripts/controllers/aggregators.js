'use strict';

function AggregatorsCtrl ($scope, $rootScope, $state, TagService, AnnotationService, ProcessService, AggregatorService) {
	// Variável para ViewModel
	var vm = this;
	var type = $state.params.type;
	
	var defaultColor = '#0000FF';
	
	var currentUser = $rootScope.currentUser.id;
	var currentOJ = 108;
	
	$scope.processos = [];
	
	// Variáveis de contexto
	$scope.currentProcesso = null;
	$scope.currentSelectedTag = null;
	
											//---------------------------------//
											//----------- FUNÇÕES -------------//
											//---------------------------------//
	
	$scope.$watch(type, function () {
		if (type === 'concluded') {
			getConcludedProcesses();
			
			$scope.currentAggregator = 'Conclusos';
			$scope.daysAmount = 5;
		} else if (type === 'pending') {
			getPendingProcesses();
			
			$scope.currentAggregator = 'Pendentes';
			$scope.daysAmount = 12;
		}
	});
	
	function getPendingProcesses() {
		AggregatorService.getProcessosPendentes({ orgaoJulgador: currentOJ, qtdDias: 12 }, function (response) {
			updateProcessos(response);
		});
	}
	
	function getConcludedProcesses() {
		AggregatorService.getProcessosConclusos({ orgaoJulgador: currentOJ, qtdDias: 5 }, function (response) {
			updateProcessos(response); 
		});
	}
	
	function updateProcessos(response) {
		var processos = [];
		$scope.currentProcesso = null;
		
		angular.forEach(response, function (e, key) {
			processos.push({
				id: e.id,
				numero: e.npu,
				classe: e.classeJudicial,
				partes: [],
				movimentos: [],
				status: { open : false }
			});
		});
				
		$scope.processos = processos;
	}
	
	// Recupera o texto da anotação do processo selecionado
	function getCurrentAnnotation() {
		var annotation = "";
		
		if ($scope.currentProcesso !== null) {
			if ($scope.currentProcesso.anotacao !== undefined) {
				annotation = $scope.currentProcesso.anotacao.texto;
			}
		}
		
		return annotation;
	}
	
	// Atualiza a anotação após alguns segundos
	function updateAnnotation (newValue, oldValue) {
		if (newValue === oldValue) {
			return;
		}
		
		if ($scope.currentProcesso !== null) {
			var anotacao = $scope.currentProcesso.anotacao;
			
			if (anotacao !== undefined) {
				// Caso a anotação já exista apenas atualiza
				if (anotacao.id !== null && anotacao.id !== undefined) {
					AnnotationService.update($scope.currentProcesso.anotacao, function (response) {
						$scope.currentProcesso.anotacao = response;
					});
				} else {
					// Caso a anotaçaõ ainda não exista, salva uma nova
					if (newValue) {
						var newAnnotationObj = new AnnotationService
						({
							texto: newValue,
							npuProcesso: removeNpuMask($scope.currentProcesso.numero),
							usuarioId: currentUser,
							orgaoJulgadorId: currentOJ
						});
						
						newAnnotationObj.$save(function (response) {
							$scope.currentProcesso.anotacao = response;
						});
					}
				}
			}
		}
	}
	
	function loadUserTags(userId) {
		return TagService.getFromUser( { idUsuario: userId } );
	}
	
	function searchUserTags(query) {
		return query ? $scope.userTags.filter(createFilterFor(query)) : [];
	}
	
	// Rotina para verificar as tags e salvá-las no banco, caso não existam
	function newTag (newTagParam) {
		var existe = false;
		var currentTag = null;
		
		// Verifica se já tem tag com mesmo id
		if (angular.isObject(newTagParam)) {
			angular.forEach($scope.currentProcesso.tags, function(value) {
				if (newTagParam.id !== undefined && newTagParam.id !== "") {
					if (newTagParam.id === value.id) {
						existe = true;
					}
				}
			});
			
			if (!existe) {
				TagService.updateProcessoTag({ id: newTagParam.id, descricao: newTagParam.descricao, cor: newTagParam.cor, idUsuario: currentUser, npuProcesso: removeNpuMask($scope.currentProcesso.numero) });
			}
			
			return existe ? null : { id: newTagParam.id, descricao: newTagParam.descricao, cor: newTagParam.cor };
		}
		
		// Verifica se já tem tag com mesma descricao
		angular.forEach($scope.userTags, function(value) {
			if (newTagParam === value.descricao) {
				existe = true;
				currentTag = value;
			}
		});
		
		// Caso não exista, salva a tag no Banco e atualiza as coleções
		if (!existe) {
			var newTagObj = new TagService({ descricao: newTagParam, cor: defaultColor, idUsuario: currentUser, npuProcesso: removeNpuMask($scope.currentProcesso.numero), idOrgaoJulgador: currentOJ });
			newTagObj.$save(function (response) {
				currentTag = { id: response.tag.id, cor: response.tag.cor, descricao: response.tag.descricao, orgaoJulgador: response.tag.idOrgaoJulgador };
				$scope.currentProcesso.tags.push(currentTag);
				$scope.userTags.push(currentTag);
			});
		}
	
		return null;
	}
	
	function removeTag(tag) {
		TagService.remove({id: tag.id, npuProcesso: removeNpuMask($scope.currentProcesso.numero), idUsuario: currentUser}, function (response) {
			if (response.id) {
				$scope.userTags = loadUserTags(currentUser);
			}
		});
	}
	
	function setCurrentSelectedTag (tag) {
		$scope.currentSelectedTag = tag;
	}
	
	function updateTagColor(tag) {
		if (tag !== null) {
			TagService.update(tag);
		}
	}
	
	// Atualiza contexto de acordo com o processo selecionado
	function selectProcesso (processo) {
		if (!processo.status.open) {
			$scope.currentProcesso = processo;
			
			// Carrega as tags do processo
			processo.tags = TagService.query({npuProcesso: removeNpuMask(processo.numero), idUsuario: currentUser});
			
			// Carrega a anotação do processo
			AnnotationService.getFromProcess({ npuProcesso: removeNpuMask(processo.numero) }, function (response) {
				if (response.length > 0) {
					processo.anotacao = response[0];
				}
			});
			
			// Carrega os dados do processo, parte e movimentos
			ProcessService.getProcess(processo);
		} else {
			$scope.currentProcesso = null;
		}
	}
	
	// Filtro para autocomplete
	function createFilterFor(query) {
		var lowercaseQuery = angular.lowercase(query);
		
		return function filterFn(tag) {
			var lowername = angular.lowercase(tag.descricao);
			return (lowername.indexOf(lowercaseQuery) === 0);
		};
	}
	
	function removeNpuMask(npu) {
		return npu.replace(/[\.-]/g, "");	
	}
	
	// Carregamento Inicial das Tags do Usuario
	$scope.userTags = loadUserTags(currentUser);

	// Autocomplete
	$scope.tagQuery = "";
	$scope.searchUserTags = searchUserTags;
	
	// Event para mudança de cor da tag
	$scope.$on('colorpicker-closed', function () {
		updateTagColor($scope.currentSelectedTag);
	});
	
	// Event para atualização da anotação do processo
	$scope.$watch(getCurrentAnnotation, updateAnnotation);
	
	// Remover tag action
	$scope.removeTag = removeTag;
	
	// Mapeamento para o Objeto Tag
	$scope.newTag = newTag;
	
	// Atribui qual tag foi selecionada para a mudança de cor
	$scope.setCurrentSelectedTag = setCurrentSelectedTag;
	
	// Método chamado quando seleciona-se um processo
	$scope.selectProcesso = selectProcesso;
}

angular.module('pjedash.app').controller('AggregatorsCtrl', ['$scope', '$rootScope', '$state', 'TagService', 'AnnotationService', 'ProcessService', 'AggregatorService', AggregatorsCtrl]);