myApp.service('prescriptionService',['$http', function ($http) {
	
    this.prescriptionHistory = [];
    
    this.getPrescriptionHistory = function (ajaxUrl) {
        return $http({
            method : "POST",
            url: '/searchPrescriptionsForPatientId?patientId=' + ajaxUrl
        }).then( function(data){
        	prescriptionHistory = data;
            return prescriptionHistory;
        });
    };
    
}]);