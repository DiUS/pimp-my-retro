var controller = app.controller('DashboardCtrl', ['$scope', 'CategoryService', 'TopicService', function($scope, CategoryService, TopicService) {

    $scope.title = 'CRApp';

    $scope.categories = CategoryService.list();
    $scope.topics = TopicService.list();

//    CategoryService.add({title:'Happy', icon:'happy.png'});
//    CategoryService.add({title:'Sad', icon:'sad.png'});
//    CategoryService.add({title:'Confused', icon:'confused.png'});
}]);
