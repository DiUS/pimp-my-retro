app.service('ActionService', ['$rootScope', '$firebase', function($rootScope, $firebase) {

  var actionsRef = $firebase(fb.child('actions'));
  var selectedTopic = {};

  this.list = function() {
    return actionsRef;
  };

  this.add = function(action) {
    action['user'] = $rootScope.user.given_name;
    actionsRef.$add(action);
  };

  this.remove = function(action) {
    actionsRef.$child(action.$id).$remove();
  };

  this.setSelectedTopic = function(topic) {
    selectedTopic = topic;
  }

  this.getSelectedTopic = function() {
    return selectedTopic;
  }

}]);