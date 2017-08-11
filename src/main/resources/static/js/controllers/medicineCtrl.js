myApp.controller('medicineCtrl',['$scope','medicineService','$window', function($scope, medicineService, $window) {
	
	$scope.medicines = [];
	$scope.url1 = 'Tab';
	$scope.url2 = 'Cap';
	$scope.url3 = 'Syp';
	$scope.url4 = 'Gel';
	$scope.url5 = 'Mouthwash';
	$scope.url6 = 'Suspension';
	
	$scope.patientDetails = [];
	
	$scope.postMedicines = [];
	$scope.doctorDetails = document.getElementById('docDetails').innerHTML;	
	
	medicineService.getMedicines($scope.url1).then(function (response) {
		$scope.patientDetails = $.parseJSON(document.getElementById('userDetails').innerHTML);	
		$scope.signInError = $.parseJSON(document.getElementById('signInError').innerHTML);	
				
		var today = new Date();
		var date = today.getFullYear()+'-'+(today.getMonth()+1)+'-'+today.getDate();
		var time = today.getHours() + ":" + today.getMinutes() + ":" + today.getSeconds();
		var dateTime = date+' '+time;

		$scope.patientConsultationDate = dateTime;

		$scope.patientPrescriptionNUmber= dateTime;

	    $scope.medicines = response.data;
	});
	

	$scope.getMedicine = function(url){
		medicineService.getMedicines(url).then(function (response) {
		    $scope.medicines = response.data;
		});
	};
	
	$scope.addMed = function(med){
		var selectedId = med.id;
		var isAlreadyAdded = false;
		for(var i=0; i < $scope.postMedicines.length; i++){
			var addedid=$scope.postMedicines[i].id;
			if(addedid == selectedId){
				isAlreadyAdded = true;
			}
		}
		
		if(!isAlreadyAdded){
			$scope.postMedicines.push(med);
		}
		
	};
	
	$scope.printData = function(divName) {
		  var printContents = document.getElementById(divName).innerHTML;
		  var popupWin = window.open('', 'PRINT', 'width=700,height=700');
		  popupWin.document.open();
		  popupWin.document.write('<html><head><style> tr > td { width:15%; padding:5px}</style><title>Vodafone</title></head><body>' + printContents + '</body></html>');
		  popupWin.document.close(); // necessary for IE >= 10
		  popupWin.focus(); // necessary for IE >= 10
		
		  popupWin.print();
		  popupWin.close();
	}; 
	
	$scope.sendData = function (content){
		var finalJson = '{"patientInfo":'+JSON.stringify($scope.patientDetails)+',"medDataList":'+JSON.stringify($scope.postMedicines)+',"doctorId":'+$scope.doctorDetails+'}';
		medicineService.sendMedicines(finalJson).then( function(status){
			$scope.postMedicines = [];
        });
		$scope.printData(content);
	};

	$scope.removeItem = function (index){
		//To remove highlighted color of recently removed med array
		// get med object
		var tempMed = $scope.postMedicines[index];
		if($scope.postMedicines.length !== 0){
			for(i=0; i < $scope.postMedicines.length; i++){	
				if($scope.postMedicines[i].id === tempMed.id){
					delete  $scope.postMedicines[i].isSelected;
				}
			}
		}
		$scope.postMedicines.splice(index, 1); 
	};
	
	$scope.takeToHistory = function(){
		var id = '';
		for(var i=0; i < $scope.patientDetails.length; i++){
			id=$scope.patientDetails[0].id;
		}
		medicineService.getHistory(id);
	};
	
	/* Function: To highlight the selected row on tab change
	 * Params: accept medicine object to match is in print selected
	 * Return : update med object
	 */
	
	$scope.medSelected = function (med){
		if($scope.postMedicines.length !== 0){
			for(i=0; i < $scope.postMedicines.length; i++){	
				if($scope.postMedicines[i].id===med.id){
					
					med.isSelected = true;
				}
			}
		}
		
	};
	
		
}]);