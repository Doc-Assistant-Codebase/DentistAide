myApp.controller('prescriptionCtrl',['$scope','prescriptionService', function($scope, prescriptionService) {
	
	$scope.prescriptionHistory = [];
	
	$scope.patientDetails = [];
		
	$scope.takeToHistory = function(){
		$scope.patientDetails = $.parseJSON(document.getElementById('pastPrescriptionDetails').innerHTML);			
		
		var today = new Date();
		var date = today.getFullYear()+'-'+(today.getMonth()+1)+'-'+today.getDate();
		var time = today.getHours() + ":" + today.getMinutes() + ":" + today.getSeconds();
		var dateTime = date+' '+time;

		var id = '';
		for(var i=0; i < $scope.patientDetails.length; i++){
			id=$scope.patientDetails[0].id;
		}
		
		$scope.orderByField='id';
		$scope.reverseSort=true;
		
		prescriptionService.getPrescriptionHistory(id).then(function (response) {
		    $scope.prescriptionHistory = response.data;
		});
		
	};
	
	$scope.takeBackToHistory = function(){
		window.history.back();
	};
		
}]);