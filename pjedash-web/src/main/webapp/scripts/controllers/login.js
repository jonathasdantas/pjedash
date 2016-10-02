'use strict';

function LoginCtrl ($scope, $rootScope, $state, $auth, AuthService) {
	// Variável para ViewModel
	var vm = this;
	
	vm.login = "";
	vm.password = "";
	vm.errorMessage = "";
	
	function logar() {

		var credentials = {
            login: vm.login,
            senha: vm.password
        };
		
		$auth.login(credentials).then(function(response) {

			$rootScope.authenticated = true;

			// recupera os dados do usuário autenticado a partir do payload do token
			$rootScope.currentUser = $auth.getPayload();

			$state.go('overview');

        // Handle errors
        }, function(error) {
        	 
        	$scope.loginForm['password'].$setValidity('server', false)
        	
        	vm.errorMessage = error.data.message;
        
        });
	}
	
	vm.logar = logar;
}

angular.module('pjedash.app').controller('LoginCtrl', ['$scope', '$rootScope', '$state', '$auth', 'AuthService', LoginCtrl]);