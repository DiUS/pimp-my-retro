app.controller('AuthenticationCtrl', ['$scope', '$rootScope', 'AuthenticationService', function($scope, $rootScope, AuthenticationService) {
  $scope.auth = AuthenticationService.auth();

  $scope.login = function() {
    $scope.auth.login('google');
  }

  $scope.logout = function() {
    $scope.auth.logout();
  }
}]);