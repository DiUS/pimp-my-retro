app.controller('NotesCtrl', ['$scope', '$rootScope', 'TopicService', 'CategoryService', function($scope, $rootScope, TopicService, CategoryService) {
	$scope.topic = {};
  $scope.topics = TopicService.list();
  $scope.categories = CategoryService.list();
  $scope.selectedCategory = $scope.categories[0];

  $scope.selectCategory = function(category) {
    $scope.selectedCategory = category;
    $scope.categorySelected = true;
  };

  $scope.addTopic = function() {
    $scope.topic['category'] = $scope.selectedCategory;
    if ($scope.topic.text) {
    	TopicService.add($scope.topic);
    	$scope.error = false;
    	$scope.selectedCategory = $scope.categories[0];
    	$scope.categorySelected = false;
    } else {
    	$scope.error = "Add a topic, stupid!";
    }
    $scope.topic = {};
  };
  
  $scope.vote = function(topic) {
    TopicService.vote(topic, $rootScope.user.given_name);
  };

  $scope.hasVote = function(topic) {
    var voted = false;
    angular.forEach(topic.votes, function(vote) {
      if(vote == $rootScope.user.given_name) {
        voted = true;
      }
    });

    return voted;
  };
}]);