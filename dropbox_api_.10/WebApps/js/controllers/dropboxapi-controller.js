
app.controller('DropBoxAPIController', function($scope,$http,$location) {

/**  To add new when the user clicks a this button */
$scope.getuserappform = function() {
	console.log("user redirection called !!");
	$location.url('/en-US/userappdetails.html');
};
	
$scope.user={
		"name":'',
};

$scope.files = [];

/** To upload a file to dropbox cloud */
//$scope.continueFileUpload=function (){
//  var uploadUrl="http://localhost:8080/dropbox_api_.10/dropbox/"+"continueFileUpload";
//  console.log("file",file.files[0]);
//  var formData=new FormData();
//  formData.append("file",file.files[0]);
//  $http({
//  method: 'POST',
//  url: uploadUrl,
//  headers: {'Content-Type': undefined},
//  data: formData,
//  transformRequest: function(data, headersGetterFunction) {
//  return data;
//  }
//  })
//  .success(function(data, status) {   
//   alert("success");
//   })
//};	

/** To upload a file to dropbox cloud */
/***$scope.continueFileUpload=function (){
  var uploadUrl="http://localhost:8080/dropbox_api_.10/login/"+"continueFileUpload";
  var formData=new FormData();
  formData.append("file",file.files[0]);
  formData.append("user",JSON.stringify($scope.user.name));
  console.log("file",file.files[0]);
  console.log("parsed data",JSON.stringify($scope.user.name))
  $http({
  method: 'POST',
  url: uploadUrl,
  headers: {'content-type' : undefined},
  data: formData,
  transformRequest: function(data, headersGetterFunction) {
  return data;
  }
  })
  .success(function(data, status) {   
   alert("success");
   })
};	
*/

/**To get list of files from the dropbox   */	
$scope.getListOfFiles=function(){
	var foldername="/";
	$http.get("http://localhost:8080/dropbox_api_.10/dropbox/getListOfFiles/?folderPath="+foldername)
	.success(function(data,status) {   
	console.log("List of files from dropbox folder &&&&",data);
	angular.forEach(data, function(value, key) {
	console.log('for each works');
	$scope.files.push(value.title);
	console.log('under files'+$scope.files)
	});
	
	}).error(function(data, status, headers, config) {
    	alert('error');
    });
}

/** To create a folder on the dropbox */
$scope.createFolder=function(){
	var foldername="sample";
	$http.get("http://localhost:8080/dropbox_api_.10/dropbox/createFolder/?folderName="+foldername)
	.success(function(data,status) {
	console.log("The folder has been created on the dropbox !!!")
	}).error(function(data, status, headers, config) {
    	alert('error');
    });
}

/** To download the file from the dropbox cloud*/
$scope.downloadFileFromDropbox=function(filename){
	console.log("given file ",filename);
	//var filename="SpringSecurityHelloController.java";
	var url= "http://localhost:8080/dropbox_api_.10/dropbox/downloadFileFromDropbox/?fileName="+filename;
	//var url= "http://localhost:8080/dropbox_api_.10/login/downloadFileFromDropbox/";
		$http({
        method: 'GET',
        url: url,
        responseType: 'arraybuffer'
    })
    .success(function(data, status){
        console.log(data);
        var blob = new Blob([data]);
        //var blob = new Blob([data], {type: 'image/jpg'});
        saveAs(blob, filename);
    console.log("file downloading is successful!!");
    })
    .error(function(data, status){
    console.log("file downloading error!!"); 
    })
}

/** To delete data from the dropbox cloud */
$scope.deleteFileFromDropbox=function(filename){
	console.log('we entered in to delete a file ');
	var url= "http://localhost:8080/dropbox_api_.10/dropbox/deleteFileFromDropbox/?fileName="+filename;
	$http({
    method: 'GET',
    url: url
})
.success(function(data, status){
console.log("file deleting is successful!!");
})
.error(function(data, status){
console.log("file deleting error!!"); 
})
}
  
	/** It calls when the container initializes.*/
    //$scope.getListOfFiles();
    /** Test code for our controller*/
	console.log("it works");
	console.log("we have got it !!!")
});