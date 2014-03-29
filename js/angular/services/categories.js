app.service('CategoryService', ['$rootScope', '$firebase', function($rootScope, $firebase) {

  var categoriesRef = $firebase(fb.child('categories'));

  this.list = function() {
    return categoriesRef;
  };

  this.add = function(topic) {
    categoriesRef.$add(topic);
  };

  this.remove = function(topic) {
    categoriesRef.$child(topic.$id).$remove();
  };
}]);