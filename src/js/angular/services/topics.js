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

  this.vote = function(topic, user) {
    topicsRef.$child(topic.$id).$child('votes').$add(user);
  };

  this.unvote = function(topic, user) {
    var votesRef = fb.child('topics').child(topic.$id).child('votes');
    votesRef.on('value', function(snapshot) {
      snapshot.forEach(function(childSnapshot) {
        var voter = childSnapshot.val();
        if(voter == $rootScope.user.given_name) {
          childSnapshot.ref().remove();
          return true;
        }
      });
    });
    votesRef.off('value');
  };

}]);