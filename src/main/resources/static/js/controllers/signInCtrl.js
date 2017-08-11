myApp.controller('signInCtrl',['$scope', function($scope) {

	$scope.signInError = $.parseJSON(document.getElementById('signInError').innerHTML);	
	
	$scope.checkIfSignInErrorDisplayed = function(){
		if(!angular.isUndefined($scope.signInError) && $scope.signInError!=null && $scope.signInError!=""){
			return true;
		}
		return false;
	};
	
}]);