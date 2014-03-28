app.controller('AuthenticationCtrl', ['$scope', '$rootScope', 'AuthenticationService', function($scope, $rootScope, as) {
  $scope.auth = as.auth();

  $scope.login = function() {
    $scope.auth.login('google');
  }
}]);