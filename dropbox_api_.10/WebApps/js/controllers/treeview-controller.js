 app.directive('bindFile', [function () {
	 console.log("it works");
    return {
        require: "ngModel",
        restrict: 'A',
        link: function ($scope, el, attrs, ngModel) {
            el.bind('change', function (event) {
                ngModel.$setViewValue(event.target.files[0]);
                $scope.$apply();
            });
            
            $scope.$watch(function () {
                return ngModel.$viewValue;
            }, function (value) {
                if (!value) {
                    el.val("");
                }
            });
        }
    };
}]);
 
/*app.directive('fileModel', ['$parse', function ($parse) {
	console.log('app directive works');
    return {
        restrict: 'A',
        link: function(scope, element, attrs) {
            var model = $parse(attrs.fileModel);
            var modelSetter = model.assign;
            
            element.bind('change', function(){
                scope.$apply(function(){
                    modelSetter(scope, element[0].files[0]);
                });
            });
        }
    };
}]);*/

app.service('fileUpload', ['$http', function ($http) {
	console.log('app service works');
    this.uploadFileToUrl = function(file, uploadUrl){
        var fd = new FormData();
        fd.append('file', file);
        $http.post(uploadUrl, fd, {
            transformRequest: angular.identity,
            headers: {'Content-Type': undefined}
        })
        .success(function(){
        })
        .error(function(){
        });
    }
}]);


app.controller('treeController',['$scope', '$http','fileUpload', function($scope, $http,fileUpload) {
	
	console.log("$$$ tree controller has been initialized $$$")
	
	/** To get list of files from the dropbox */
    var files  = [];
	$scope.list = [];
	var foldername = '';
	/***  we call dropbox cloud when the user wants and we get files and
	 *  folders for an initialization*/
	
	$scope.getListOfFiles = function() {
		var foldername = "/";
		$http.get("http://localhost:8080/dropbox_api_.10/dropbox/getListOfFiles/?folderPath=" + foldername)
		.success(function(data, status) {
			console.log("List of files from dropbox folder &&&&", angular.toJson(data));
			$scope.list = data;
		}).error(function(data, status, headers, config) {
			alert('error');
		});
	}
	
	var folders = [];
	var buildFloderPath = function(scope) {
		if (scope.$parentNodeScope != null) {
			folders.push(scope.$parentNodeScope.$modelValue.title);
			buildFloderPath(scope.$parentNodeScope);
		}
	};
	
	/** When we call, we expand tree here and clear when collapse tree*/
	$scope.toggleMe = function(scope) {
		folders = [];
		foldername="";
		if (!scope.collapsed) {
			var nodeData = scope.$modelValue;
			folders.push(nodeData.title);
			buildFloderPath(scope);
			console.log(angular.toJson(folders));
			
			for (var i = folders.length - 1; i >= 0; i--) {
				foldername += "/" + folders[i];
			}
			/***/
			//continueFileUploading(foldername);
			
			$http.get("http://localhost:8080/dropbox_api_.10/dropbox/getListOfFiles/?folderPath=" + foldername)
			.success(function(data, status) {
				console.log(" @@@@ Selected path @@@",foldername);
				console.log("List of files from dropbox folder &&&&", angular.toJson(data));
				for (var i = 0; i < data.length; i++) {
					nodeData.items.push(data[i]);
				}
			}).error(function(data, status, headers, config) {
				alert('error');
			});
		}
		else{
			var nodeData = scope.$modelValue;
			nodeData.items = [];
		
		}
	};
	     
	/** To upload a file to dropbox cloud */
	$scope.continueFileUpload=function (){
		console.log('bind file',$scope.theFile); 
	  /**var file = $scope.myFile;
	  console.log('file is ',file );        
	  console.log('it is working');
	  console.log("file ",file);*/
	  var file =$scope.theFile;
	  var uploadUrl="http://localhost:8080/dropbox_api_.10/login/continueFileUpload";
	  console.log("uploadUrl",uploadUrl);
	  var formData=new FormData();
	  formData.append("file",file);
	  formData.append("path",foldername);
	  $http({
	  method: 'POST',
	  url: uploadUrl,
	  headers: {'Content-Type': undefined},
	  data: formData,
	  transformRequest: function(data, headersGetterFunction) {
	  return data;
	  }
	  })
	  .success(function(data, status) {   
	   alert("success");
	   })
	};	
	
	/***  we call dropbox cloud when the user wants and 
	 * we get files and folders for an initialization
	 * */
	
	$scope.getListOfFiles();
	/*****************/
	
	$scope.remove = function(scope) {
		scope.remove();
	};

	$scope.toggle = function(scope) {
		scope.toggle();
	};

	$scope.newSubItem = function(scope) {
		var nodeData = scope.$modelValue;
		nodeData.items.push({
			id : nodeData.id * 10 + nodeData.items.length,
			title : nodeData.title + '.' + (nodeData.items.length + 1),
			items : []
	});
	};
}]);