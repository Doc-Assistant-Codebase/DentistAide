myApp.controller('setDoctorDataCtrl',['$scope', function($scope) {


	$scope.doctorDetails = document.getElementById('docDetails').innerHTML;	
	
	$scope.loadDoctorDetails = function(){

		$scope.doctorDetails = document.getElementById('docDetails').innerHTML;			
		
	};
	/*
	$scope.setDoctorIdVal = function(){

		alert('in doctorDetails' +$scope.doctorDetails);
		$scope.doctorId = doctorDetails.id;
		alert('doctorId' +$scope.doctorId);
		$('#doctorIdValue').val($scope.doctorId);
	};*/
	
}]);