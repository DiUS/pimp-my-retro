var app = angular.module('app', ['firebase', 'ngRoute']);
var fb = new Firebase('https://collaborative-retro.firebaseio.com/');

// configure our routes
app.config(['$routeProvider', function($routeProvider) {
	$routeProvider
		.when('/', {
			templateUrl : 'notes.html',
			controller  : 'NotesCtrl'
		})
		.when('/notes', {
			templateUrl : 'notes.html',
			controller  : 'NotesCtrl'
		})
		.when('/dashboard', {
			templateUrl : 'dashboard.html',
			controller  : 'DashboardCtrl'
		});
}]);