var controller = app.controller('DashboardCtrl', ['$scope', '$location', 'CategoryService', 'TopicService', 'ActionService', function($scope, $location, CategoryService, TopicService, ActionService) {
    $scope.title = 'CRApp';
    $scope.categories = CategoryService.list();
    $scope.topics = TopicService.list();
    $scope.actions = ActionService.list();

    $scope.list1 = {title: 'AngularJS - Drag Me'};
    $scope.list2 = {};

    $scope.dropped = function($event, $index, topic) {
        alert("remove " + topic.text);
    }

    $scope.drop = function($event, $data, targetTopic) {
        alert("dropping '" + $data.text + "' on to '" + targetTopic.text + "'");
    }

    $scope.remove = function(topic) {
        TopicService.remove(topic);
    }

    $scope.countVotes = function(topic) {
      if(topic.votes == undefined) {
        return 0;
      }
      return Object.keys(topic.votes).length;
    }

    $('#fire').fire({
        mode:'anim',
        speed:30,
        minPow:1,
        maxPow:10,
        gravity:9,
        flameWidth:4,
        flameHeight:3,
        maxPowZone: 'sides',
        fadingFlameSpeed: 18
    });

    $scope.selectTopic = function(topic) {
      ActionService.setSelectedTopic(topic);
      $location.path('action');
    }

}]);
