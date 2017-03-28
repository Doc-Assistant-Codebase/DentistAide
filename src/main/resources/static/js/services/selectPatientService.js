myApp.service('selectPatientService',['$http', function ($http) {
	
    this.takeToSelectedPatient = function (patientId){
    	$http({
			method : 'POST',
			url : '/loadPatientDetailsById?patientHiddenId=' + patientId
			//data : patientId
		});
    };
  
    
}]);