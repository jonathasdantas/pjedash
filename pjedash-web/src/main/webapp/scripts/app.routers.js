'use strict';

/**
 * @ngdoc overview
 * @name pjedash.routers
 * @description
 * # pjedash.routers
 *
 * Main module of the application.
 */
angular.module('pjedash.routers', ['ui.router', 'satellizer'])
	// Configuração das rotas
	.config(function ($stateProvider, $urlRouterProvider) {
		// Rota padrão
		$urlRouterProvider.otherwise('/login');
		
		// Definição dos estados
		$stateProvider
			.state('base', {
				abstract: true,
				templateUrl: 'views/base.html'
			})
			.state('login', {
				url: '/login',
				templateUrl: 'views/login.html',
				controller: 'LoginCtrl'
			})
			.state('dashboard', {
				url: '/dashboard',
				parent: 'base',
				abstract: true,
				templateUrl: 'views/dashboard.html',
				controller: 'DashboardCtrl'
			})
			.state('overview', {
				url: '/overview',
				parent: 'dashboard',
				templateUrl: 'views/dashboard/overview.html',
				controller: 'DashboardCtrl'
			})
			.state('indicators', {
				url: '/indicators',
				abstract: true,
				parent: 'base',
				templateUrl: 'views/indicators.html',
				controller: 'IndicatorsCtrl'
			})
			.state('indicators.iad', {
				url: '/iad',
				params: { type: 'iad' },
				parent: 'indicators',
				templateUrl: 'views/indicators/index.html',
				controller: 'IndicatorsCtrl'
			})
			.state('indicators.nb', {
				url: '/nb',
				params: { type: 'nb' },
				parent: 'indicators',
				templateUrl: 'views/indicators/index.html',
				controller: 'IndicatorsCtrl'
			})
			.state('indicators.sentences', {
				url: '/sentences',
				params: { type: 'sentences' },
				parent: 'indicators',
				templateUrl: 'views/indicators/index.html',
				controller: 'IndicatorsCtrl'
			})
			.state('indicators.congestion', {
				url: '/congestion',
				params: { type: 'congestion' },
				parent: 'indicators',
				templateUrl: 'views/indicators/index.html',
				controller: 'IndicatorsCtrl'
			})
			.state('aggregators', {
				url: '/aggregators',
				abstract: true,
				parent: 'base',
				templateUrl: 'views/aggregators.html',
				controller: 'AggregatorsCtrl'
			})
			.state('aggregators.concluded', {
				url: '/concluded',
				params: { type: 'concluded' },
				parent: 'aggregators',
				templateUrl: 'views/aggregators/index.html',
				controller: 'AggregatorsCtrl'
			})
			.state('aggregators.pending', {
				url: '/pending',
				params: { type: 'pending' },
				parent: 'aggregators',
				templateUrl: 'views/aggregators/index.html',
				controller: 'AggregatorsCtrl'
			})
			.state('variables', {
				url: '/variables',
				abstract: true,
				parent: 'base',
				templateUrl: 'views/variables.html',
				controller: 'VariablesCtrl'
			})
			.state('variables.newset', {
				url: '/newset',
				params: { type: 'newset' },
				parent: 'variables',
				templateUrl: 'views/variables/index.html',
				controller: 'VariablesCtrl'
			})
			.state('variables.sentences', {
				url: '/sentences',
				params: { type: 'sentences' },
				parent: 'variables',
				templateUrl: 'views/variables/index.html',
				controller: 'VariablesCtrl'
			})
			.state('variables.archived', {
				url: '/archived',
				params: { type: 'archived' },
				parent: 'variables',
				templateUrl: 'views/variables/index.html',
				controller: 'VariablesCtrl'
			})
			.state('variables.timeToArchive', {
				url: '/timeToArchive',
				params: { type: 'timeToArchive' },
				parent: 'variables',
				templateUrl: 'views/variables/index.html',
				controller: 'VariablesCtrl'
			})
			.state('annotations', {
				url: '/annotations',
				parent: 'base',
				templateUrl: 'views/annotations/index.html',
				controller: 'AnnotationsCtrl'
			})
			.state('tags', {
				url: '/tags/:tag',
				parent: 'base',
				templateUrl: 'views/tags/index.html',
				controller: 'TagsCtrl'
			})
			.state('logout', {
				url: '/logout',
				parent: 'base',
				resolve: {
					logout: function ($auth, $state, $rootScope, $interval) {
						return $auth.logout().then(function() {
							localStorage.removeItem('user');

							$rootScope.authenticated = false;
							$rootScope.currentUser = null;

							$interval.cancel($rootScope.alertsJob);
							
							$state.go('login');
						});
					}
				}
			})
			.state('alerts', {
				url: '/alerts',
				parent: 'base',
				templateUrl: 'views/alerts/index.html',
				controller: 'AlertsCtrl'
			})
			.state('settings', {
				url: '/settings',
				parent: 'base',
				templateUrl: 'views/settings/index.html',
				controller: 'SettingsCtrl'
			});
	});