app.controller('NotesCtrl', ['$scope', '$rootScope', 'TopicService', 'CategoryService', function($scope, $rootScope, TopicService, CategoryService) {
	$scope.topic = {};
  $scope.topics = TopicService.list();
  $scope.categories = CategoryService.list();
  $scope.selectedCategory = false;

  $scope.selectCategory = function(category) {
    $scope.selectedCategory = category;
  };

  $scope.addTopic = function() {
    $scope.topic['category'] = $scope.selectedCategory;
    if ($scope.topic.text) {
    	TopicService.add($scope.topic);
    	$scope.error = false;
    	$scope.selectedCategory = false;
    } else {
    	$scope.error = "Add a topic, stupid!";
    }
    $scope.topic = {};
  };
  
  $scope.vote = function(topic) {
    TopicService.vote(topic, $rootScope.user.given_name);
  }
  
  $scope.showInput = function() {
	  return selectedCategory;
  };
}]);