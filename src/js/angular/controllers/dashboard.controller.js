var controller = app.controller('DashboardCtrl', ['$scope', 'CategoryService', 'TopicService', function($scope, CategoryService, TopicService) {
    $scope.title = 'CRApp';
    $scope.categories = CategoryService.list();
    $scope.topics = TopicService.list();
}]);
