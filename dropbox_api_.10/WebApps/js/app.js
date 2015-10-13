var app = angular.module("DropboxAPI", [ 'ngRoute', 'ui.bootstrap','ui.tree' ]);

app.config(function($routeProvider) {
	$routeProvider.when("/", {
		redirectTo : "/dropboxoperation"
	}).when('/dropboxoperation', {
		templateUrl : 'views/en-US/dropboxoperation.html',
		controller  : 'DropBoxAPIController',
	    
	}).when('/treeview', {
		templateUrl : 'views/en-US/treeview.html',
		controller  : 'treeController'
	}).otherwise({
		redirectTo : '/dropboxoperation'
	});
});
