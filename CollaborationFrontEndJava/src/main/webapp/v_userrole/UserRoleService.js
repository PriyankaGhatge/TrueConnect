'user strict'

app.factory('UserRoleService',['$http','$q','$rootScope',function($http,$q,$rootScope){
	console.log("USer role service");
	var baseurl='/CollaborationBackEndJava'
	return {
		getAllUserRoles: function()
		{
			return $http.get(baseurl + "/alluserroles").then(function (response) 
			{
				return response.data;
			},
			function(errResponse)
			{
                console.error('Error while fetching Users' + errResponse);
                deferred.reject(errResponse);
			}
		);
    },
	userRoleUpdate : function (userrole)
  	  {
		  alert(userrole);
  		  return $http.post(baseurl + '/adduserrole/', userrole).then(function (response) 
  		  {
  			  return response.data;
  		  },
  	      function(errResponse)
  	      {
  	           console.error('Error while creating User role');
  	           return $q.reject(errResponse);
  	      }
  	  );
  	},

  	removeUserRoleByID : function (roleid)
  	{
  		return $http['delete'](baseurl + '/deluserrole/' + roleid).then(function(response)
  		{
  			return response.data;
  		},
  		function(errResponse){
  			console.error('Error while in fetching data');
  			return $q.reject(errResponse);
  		}
  		);
  	},
  	 
  	getUserRoleByID : function (roleid)
  	{
  		return $http.get(baseurl + '/getuserrole', roleid).then(function(response)
  		{
  			return response.data;
  		},
  		function(errResponse){
  			console.error('Error while in fetching data');
  			return $q.reject(errResponse);
  		}
  		);
  	 } 
	} 
  }
]);