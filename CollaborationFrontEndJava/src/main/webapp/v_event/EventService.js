'user strict'

app.factory('EventService',['$http','$q','$rootScope',function($http,$q,$rootScope)
{
	var baseurl='/CollaborationBackEndJava'

		return {
			fetchAllEvents: function()
			{
				return $http.get(baseurl + "/allevents").then(function (response) 
				{	
					return response.data;
				},
				function(errResponse)
				{
					console.error('Error while fetching events' + errResponse);
					deferred.reject(errResponse);
				}
			);
	 },
	 
	 createNewevent : function (event)
  	 {
  		  return $http.post(baseurl + '/addevent/', event).then(function (response) 
  		  {
  			  return response.data;
  		  },
  	      function(errResponse)
  	      {
  	           console.error('Error while creating Event');
  	           return $q.reject(errResponse);
  	      });
  	  }
	}
  }
]);