myApp.service('firstService',['$http', function ($http) {
	
    this.users = [];
    
    this.getUsers = function (ajaxUrl) {
        return $http({
            method : "POST",
            url: '/' + ajaxUrl
        }).then( function(data){
        	users = data;
            return users;
        });
    };
    
    this.sendUsers = function (userList){
    	return $http({
			method : 'POST',
			url : '/postSelectedData',
			data : userList,
			headers: {'Content-Type': 'application/json; charset=utf-8'}
		}).then( function(status){
			return status;
        });
    };
    
}]);