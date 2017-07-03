'use strict';

app.controller('CommonController', ['$scope', 'BlogService','BulletinService','EventService','JobService',
                                    'CommonService', '$location', 
                                    '$rootScope', '$cookieStore', '$http',
             function($scope, BlogService,BulletinService,EventService,JobService, CommonService, 
            		 $location, $rootScope, $routeParams, $cookieStore, $http) 
{
	
	self.blogs=[];
	
	self.fetchAllBlogs = function()
	{
		BlogService.fetchAllBlogs().then(function(d)
		{
			self.blogs = d;
		    console.log("Fetch all user blogs")
		},
		function(errResponse)
		{
			console.error('Error while fetching User blog'+ errResponse);
		});
	};
	
}]);