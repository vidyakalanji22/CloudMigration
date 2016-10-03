var viewDetailsApp = angular.module("viewDetailsApp",[]);

viewDetailsApp.controller("viewDetailsAppCtrl", function($scope, $http){


	$http.get("../rest/app/customers")
	.success(function(data, status, headers,config) {
		$scope.customers = data;
		console.log(data);
	}).error(function(data, status, headers,config) {
		alert("Something went wrong while listing the Customers. Please try again");
	});
});