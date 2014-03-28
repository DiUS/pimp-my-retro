app.service('TopicService', ['$rootScope', '$firebase', function($rootScope, $firebase) {
  var topicsRef = $firebase(fb.child('topics'));

  this.list = function() {
    return topicsRef;
  };

  this.add = function(topic) {
    topic['user'] = $rootScope.user.given_name;
    topicsRef.$add(topic);
  };

  this.remove = function(topic) {
    topicsRef.$child(topic.$id).$remove();
  };

}]);