app.service('AuthenticationService', ['$rootScope', function($rootScope) {
  authService = this;
  this.auth = function() {
    return new FirebaseSimpleLogin(fb, function(error, user) {
      if (error) {
        console.log(error);
      } else if (user) {
        authService.updateUserInfo(user);
        $rootScope.authenticated = true;
        $rootScope.user = user;
      } else {
        $rootScope.authenticated = false;
        $rootScope.user = null;
      }
      $rootScope.$apply();
    });
  };

  this.updateUserInfo = function(user) {
    var userRef = fb.child("users").child(user.id);
    userRef.once("value", function(userSnap) {
      var val = userSnap.val();
      if (!val) {
        userRef.set(user);
      } else {
        userRef.update(user);
      }
    });
  };
}]);