var app = angular.module('app', ['firebase', 'ngRoute']);
var fb = new Firebase('https://collaborative-retro.firebaseio.com/');

// configure our routes
app.config(['$routeProvider', function($routeProvider) {
	$routeProvider
		.when('/', {
			templateUrl : '../../html/notes.html',
			controller  : 'NotesCtrl'
		})
		.when('/notes', {
			templateUrl : '../../html/notes.html',
			controller  : 'NotesCtrl'
		})
		.when('/dashboard', {
			templateUrl : '../../html/dashboard.html',
			controller  : 'DashboardCtrl'
		});
}]);