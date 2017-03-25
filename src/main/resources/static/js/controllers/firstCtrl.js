myApp.controller('firstCtrl',['$scope','firstService', function($scope, firstService) {
	
	$scope.users = [];
	$scope.url1 = 'getUserData1';
	$scope.url2 = 'getUserData2';
	
	$scope.postUsers = [];
	
	firstService.getUsers($scope.url1).then(function (response) {
	    $scope.users = response.data;
	});
	
	$scope.getData = function(url){
		firstService.getUsers(url).then(function (response) {
		    $scope.users = response.data;
		});
	};
	
	$scope.addItem = function(user){
		$scope.postUsers.push(user);
	};
	
	$scope.printData = function(divName) {
		  var printContents = document.getElementById(divName).innerHTML;
		  var popupWin = window.open('', '_blank', 'width=700,height=700');
		  popupWin.document.open();
		  popupWin.document.write('<html><head><link rel="stylesheet" type="text/css" href="/css/bootstrap.css" /><title>Vodafone</title></head><body onload="window.print()">' + printContents + '</body></html>');
		  popupWin.document.close();
	}; 
	
	$scope.sendData = function (content){
		firstService.sendUsers($scope.postUsers).then( function(status){
			$scope.postUsers = [];
        });
		$scope.printData(content);
	};
	
}]);