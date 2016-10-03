var signInApp = angular.module("signInApp",[]);

signInApp.controller("signInAppCtrl", function($scope){


	$scope.signIn = function(){
	
		if($scope.email=="vidya@gmail.com" && $scope.password == "vidya123"){
		
		window.location.href = "../html/DBCreate.html";
		}
		
	};
});