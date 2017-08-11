myApp.service('selectPatientService',['$http', function ($http) {
	
    this.takeToSelectedPatient = function (patientId, doctorDetails){
    	$http({
			method : 'POST',
			url : '/loadPatientDetailsById?patientHiddenId=' + patientId+'&doctorHiddenId='+doctorDetails
			//data : patientId
		});
    };
  
    
}]);