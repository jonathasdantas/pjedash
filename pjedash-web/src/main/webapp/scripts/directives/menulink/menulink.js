'use strict';

angular.module('pjedash.app')
  .directive('menuLink', function () {
    return {
      scope: {
        section: '='
      },
      templateUrl: './scripts/directives/menulink/menulink.html',
      link: function ($scope, $element) {
        var controller = $element.parent().controller();
        
        $scope.focusSection = function () {
          controller.autoFocusContent = true;
          controller.toggleMenuLateral();
        };
      }
    };
  });