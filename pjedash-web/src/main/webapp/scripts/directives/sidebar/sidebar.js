'use strict';

/**
 * @ngdoc directive
 * @name pjedash.app.directive:sidebar
 * @description
 * # sidebar
 */
angular.module('pjedash.app')
  .directive('sidebar', function () {
    return {
      templateUrl: './scripts/directives/sidebar/sidebar.html',
      restrict: 'E'
    };
  });
