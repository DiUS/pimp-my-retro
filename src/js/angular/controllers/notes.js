app.controller('NotesCtrl', ['$scope', '$rootScope', 'TopicService', function($scope, $rootScope, TopicService) {
	$scope.topic = {};
  $scope.topics = TopicService.list();

  $scope.addTopic = function() {
    TopicService.add($scope.topic);
  }
}]);