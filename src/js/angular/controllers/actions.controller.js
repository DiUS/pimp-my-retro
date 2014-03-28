app.controller('ActionsCtrl', ['$scope', 'ActionService', function($scope, ActionService) {
  $scope.action = {};
  $scope.actions = ActionService.list();
  
  $scope.selectedTopic = ActionService.getSelectedTopic();
  
  $scope.addAction = function(action) {
    $scope.action['topic'] = $scope.selectedTopic;
    ActionService.add($scope.action);

    $scope.selectedTopic = {};
  }

}]);