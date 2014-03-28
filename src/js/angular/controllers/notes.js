app.controller('NotesCtrl', ['$scope', '$rootScope', 'TopicService', 'CategoryService', function($scope, $rootScope, TopicService, CategoryService) {
	$scope.topic = {};
  $scope.topics = TopicService.list();
  $scope.categories = CategoryService.list();
  $scope.selectedCategory = {};

  $scope.selectCategory = function(category) {
    $scope.selectedCategory = category;
  }

  $scope.addTopic = function() {
    $scope.topic['category'] = $scope.selectedCategory;
    TopicService.add($scope.topic);
  }
}]);