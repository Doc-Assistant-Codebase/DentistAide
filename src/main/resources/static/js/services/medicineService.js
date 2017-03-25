myApp.service('medicineService',['$http', function ($http) {
	
    this.medicines = [];
    
    this.getMedicines = function (ajaxUrl) {
        return $http({
            method : "POST",
            url: '/searchMedicineByDrugFormJson?drugForm=' + ajaxUrl
        }).then( function(data){
        	medicines = data;
            return medicines;
        });
    };
    
    this.sendMedicines = function (medList){
    	return $http({
			method : 'POST',
			url : '/postSelectedData',
			data : medList,
			headers: {'Content-Type': 'application/json; charset=utf-8'}
		}).then( function(status){
			return status;
        });
    };
    
    this.getHistory = function (ajaxUrl) {
        $http({
            method : "POST",
            url: '/fetchPrescriptions?patientHiddenId=' + ajaxUrl
        });
    };
    
}]);