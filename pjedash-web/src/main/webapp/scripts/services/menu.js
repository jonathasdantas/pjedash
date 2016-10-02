'use strict';

/**
 * @ngdoc service
 * @name pjedash.app.menu
 * @description
 * # menu
 * Service in the pjedash.app.
 */
angular.module('pjedash.app')
	.factory('Menu', function ($location) {

        var sections = [ {
			name : 'Visão Geral',
			state : 'overview',
			type : 'link',
			icon : 'fa fa-tachometer'
		} ];

        sections.push({
        	name: 'Indicadores',
        	type: 'toggle',
        	icon: 'fa fa-area-chart',
        	pages: [
        	{
        		name: 'Índice de Atendimento à Demanda',
        		state: 'indicators.iad',
    			type: 'link'
			},
			{
				name: 'Taxa de Congestionamento',
				state: 'indicators.congestion',
				type: 'link'
			},
			{
				name: 'Casos Novos x Casos Baixados',
				state: 'indicators.nb',
				type: 'link'
			},
			{
				name: 'Sentenças Proferidas',
				state: 'indicators.sentences',
				type: 'link'
			}]
        });

        
        sections.push({
        	name: 'Variáveis',
        	type: 'toggle',
        	icon: 'fa fa-asterisk',
        	pages: [
        	{
        		name: 'Casos Novos',
        		state: 'variables.newset',
    			type: 'link'
			},
        	{
        		name: 'Sentenças',
        		state: 'variables.sentences',
    			type: 'link'
			},
			{
				name: 'Processos Arquivados',
				state: 'variables.archived',
				type: 'link'
			},
			{
				name: 'Tempo para Baixa',
				state: 'variables.timeToArchive',
				type: 'link'
			}]
        });
                
        sections.push({
			name : 'Agrupadores',
			type : 'toggle',
			icon : 'fa fa-list-alt',
			pages : [ {
				name : 'Processos Conclusos',
				state : 'aggregators.concluded',
				type : 'link'
			}, {
				name : 'Processos Pendentes',
				state : 'aggregators.pending',
				type : 'link'
			} ]
		});

        sections.push({
			name : 'Alertas e Notificações',
			state : 'alerts',
			type : 'link',
			icon : 'fa fa-exclamation-triangle'
		});

        sections.push({
			name : 'Anotações',
			state : 'annotations',
			type : 'link',
			icon : 'fa fa-pencil-square-o'
		});
        
        sections.push({
			name : 'Tags',
			state : 'tags',
			type : 'link',
			icon : 'fa fa-tags'
		});
        
		sections.push({
			name : 'Configurações',
			state : 'settings',
			type : 'link',
			icon : 'glyphicon glyphicon-wrench'
		});

		sections.push({
			name : 'Sair',
			state : 'logout',
			type : 'link',
			icon : 'fa fa-sign-out'
		});
		
        var self = {
			sections : sections,

			toggleSelectSection : function(section) {
				self.openedSection = (self.openedSection === section ? null	: section);
			},
			
			isSectionSelected : function(section) {
				return self.openedSection === section;
			},

			selectPage : function(section, page) {
				page = page && page.url && $location.path(page.url);
				self.currentSection = section;
				self.currentPage = page;
			}
		};

		return self;

      });