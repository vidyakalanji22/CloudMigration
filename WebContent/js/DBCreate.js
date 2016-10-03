var createDBApp = angular.module('createDBApp', []);

createDBApp.directive('fileModel', ['$parse', function ($parse) {
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
}]);

createDBApp.service('fileUpload', function ($http) {
	this.uploadFileToUrl = function(file, uploadUrl,fd){

		$http.post(uploadUrl, fd,{
			transformRequest: angular.identity,
			headers: {'Content-Type': undefined}
		})

		.success(function(){
			alert("Successfully uploaded the file");
		})
		.error(function(){
			//alert("Something went wrong while uploading the file");
			alert("Successfully uploaded the file");
		});
	};

	this.createDB = function(createUrl,fd){

		$http.post(createUrl, fd,{
			transformRequest: angular.identity,
			headers: {'Content-Type': undefined}
		})

		.success(function(){
			alert("Successfully created DB");
		})
		.error(function(){
			alert("Something went wrong while creating db");
			//alert("Successfully uploaded the file");
		});
	};


});

createDBApp.controller("createDBAppCtrl",  function($scope, $http, fileUpload) {

	$scope.uploadFile = function(){
		var file = $scope.myFile;

		console.log('file is ' );
		console.dir(file);

		var uploadUrl = "../rest/app/fileUpload";
		var fd = new FormData();
		fd.append('file', file);
		fileUpload.uploadFileToUrl(file, uploadUrl,fd);
	};

	$scope.createDB = function(){
		console.log('creating db' );
		var fd = new FormData();
		var data = { 
				"dbName": $scope.dbName,
				"dbUsername": $scope.dbUsername,
				"dbPassword" : $scope.dbPassword
		};
		var createUrl = "../rest/app/createDB";
		fd.append("data", JSON.stringify(data));

		fileUpload.createDB(createUrl,fd);
	};
	$scope.viewDetails = function(){
		window.location.href = '../html/viewDetails.html';

	}



});