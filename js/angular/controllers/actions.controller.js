app.controller('ActionsCtrl', ['$scope', 'ActionService', function($scope, ActionService) {
  $scope.action = {};
  $scope.actions = ActionService.list();
  $scope.message = '';
  
  $scope.selectedTopic = ActionService.getSelectedTopic();
  
  $scope.addAction = function() {
    $scope.action['topic'] = $scope.selectedTopic;
    ActionService.add($scope.action);
    $scope.message = 'Action added';

    $scope.selectedTopic = {};
  }

}]);