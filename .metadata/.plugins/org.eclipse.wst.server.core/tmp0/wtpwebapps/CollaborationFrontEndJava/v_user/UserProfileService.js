'use strict';

app.factory('UserProfileService',['$http', '$q', '$rootScope', function($http,$q,$rootScope)
{
	console.log("user profile service");

	var baseurl = '/CollaborationBackEndJava'
	return{
		 fetchAllUsers: function() {
	        return $http.get(baseurl+'/allusers')
	            .then(
	            function (response) {
	                return response.data;
	            },
	            function(errResponse){
	                console.error('Error while fetching Users');
	                return $q.reject(errResponse);
	            }
	        );
	        
	    },
	    
	    createUser: function(user)
	    {
	        return $http.post(baseurl+'/adduserprofile/',user)
	            .then(
	            function (response) {
	                return response.data;
	            },
	            function(errResponse){
	                console.error('Error while fetching Users');
	                return $q.reject(errResponse);
	            }
	        );
	    },
	    
	    approveduser: function(useremail,yesno)
	    {
	        return $http.post(baseurl+'/getuserapprove/' + useremail + '/' + yesno)
	            .then(
	            function (response) {
	                return response.data;
	            },
	            function(errResponse){
	                console.error('Error while fetching Users');
	                return $q.reject(errResponse);
	            }
	        );
	    },
	    
	    authenticate: function(user){
	    	return $http.post(baseurl + '/authenticate', user)
	    		.then(function(response)
	    		{
	    			return response.data;
	    		},
	    		function(errResponse)
	    		{
	    			$rootScope.UserLoggedIn = false;
	    			console.error('Error while getting user');
	    		}
	    	);
	    },
	    
	    logout: function(id){
	    	return $http.get(baseurl + '/logout')
            .then(
    	            function (response) {
    	                return response.data;
    	            },
    	            function(errResponse){
    	                console.error('Error while logout User');
    	                return $q.reject(errResponse);
    	            }
	    	
            );
	    },

 	}
}]);