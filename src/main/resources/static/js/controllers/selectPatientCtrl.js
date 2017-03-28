myApp.controller('selectPatientCtrl',['$scope','selectPatientService', function($scope, selectPatientService) {
	
	$scope.patientIdSelected = "";
	$scope.patientDetails = [];
	$scope.selectedRow = null;
	 
	$scope.loadPatientDetails = function(){
		
		$scope.patientDetails = $.parseJSON(document.getElementById('userDetails').innerHTML);			
		
	};
	
	$scope.takeToThisPatient = function(patientInfo){
		//var patientId = '{"id":'+patientInfo.id+', "firstName":"'+patientInfo.firstName+'", "lastName":"'+patientInfo.lastName+'", "mobileNumber":"'+patientInfo.mobileNumber+'", "address":"'+patientInfo.address+'", "email":"'+patientInfo.email+'"}';
		var patientId = patientInfo.id;
		$scope.patientIdSelected = patientId;
		 $scope.selectedRow = patientId;
		 
		 $('#selectedPatientDiv').show();
		//selectPatientService.takeToSelectedPatient(patientId);
	};

	$scope.takeBackToHistory = function(){
		window.history.back();
	};
		
	$scope.loadPatient = function(){
		document.myForm.action='/loadPatientDetailsById?patientHiddenId='+$scope.patientIdSelected;
		selectPatientService.takeToSelectedPatient($scope.patientIdSelected);
	};
		
}]);