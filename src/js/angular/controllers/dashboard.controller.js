var controller = app.controller('DashboardCtrl', ['$scope', 'CategoryService', 'TopicService', function($scope, CategoryService, TopicService) {
    $scope.title = 'CRApp';
    $scope.categories = CategoryService.list();
    $scope.topics = TopicService.list();

    $scope.list1 = {title: 'AngularJS - Drag Me'};
    $scope.list2 = {};

    $scope.remove = function(topic) {
        TopicService.remove(topic);
    }

    $scope.countVotes = function(topic) {
      if(topic.votes == undefined) {
        return 0;
      }
      return Object.keys(topic.votes).length;
    }
}]);
