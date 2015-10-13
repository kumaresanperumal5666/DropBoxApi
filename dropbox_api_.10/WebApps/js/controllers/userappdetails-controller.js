	/** This controller is used to get application key,secret key and generation token,
	 *  then we do CRUD operations here*/

app.controller('UserappController', function($scope,$http) {
	console.log("userappcontrollers");
	
	/** 
	 * Storing the user app details on DB
	 * */
	$scope.userdetails={};
	$scope.createUserappDetails = function() {
		console.log("user app details ",$scope.userdetails)
	var url ="http://localhost:8080/dropbox_api_.10/userapp/create/";
	$http({
		method : "POST",
		url    : url,
		data   : $scope.userdetails,
		headers : {
			"content-type" : "application/json",
			"Accept" : "application/json"
		},
	}).success(function(response) {
		console.log("stored data is ",$scope.userdetails)
		console.log("The userdetailsapp was created successfully !!!");			
	}).error(function(response) {
		console.log("Request Error: ", response);
	});
	
	}
	
	/**
	 * Retrieving the created userapp details
	 */
	$scope.editUserappDetails = function() {
		$scope.userdetails.id=2;
		
		var url = "http://localhost:8080/dropbox_api_.10/userapp/search/?id="+2;
		$http({
			method : "GET",
			url : url,
		}).success(function(response) {
			if(response != null) {
				$scope.userdetails = response;
				console.log("user app retrived data",$scope.userdetails);
			}
		}).error(function(response) {
			console.log("Request Error: ", response);
		});
	};
	
	/**
	 * Deleting the created userapp details
	 */
	$scope.deleteUserappDetails = function() {
		$scope.userdetails.id=2;
		var id=2;
	
		var url = "http://localhost:8080/dropbox_api_.10/userapp/delete/?id="+2;
		$http({
			method : "GET",
			url : url,
		}).success(function(response) {
			console.log("The has been deleted (*)");
		
		}).error(function(response) {
			console.log("Request Error: ", response);
		});
	};
	
	/**
	 * Updating user app details
	 * */
	$scope.updateUserappDetails = function() {
		$scope.userdetails.id=2;
		$scope.userdetails.username="kumaresan";
		$scope.userdetails.app_key="kumaresan";
		$scope.userdetails.app_secret_key="kumaresan";
		$scope.userdetails.access_token="kumaresan";
		
		var url ="http://localhost:8080/dropbox_api_.10/userapp/update/";
		$http({
			method : "POST",
			url    : url,
			data   : $scope.userdetails,
			headers : {
				"content-type" : "application/json",
				"Accept" : "application/json"
			},
		}).success(function(response) {
			console.log("stored data is ",$scope.userdetails)
			console.log("The userdetailsapp was created successfully !!!");			
		}).error(function(response) {
			console.log("Request Error: ", response);
		});	
		}
	
});