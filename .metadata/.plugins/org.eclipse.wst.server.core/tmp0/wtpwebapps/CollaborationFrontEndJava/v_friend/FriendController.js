'use strict';

app.controller('FriendController', ['$scope', 'UserProfileService', 'FriendService', '$location', 
                                    '$rootScope', '$cookieStore', '$http',
             function($scope, UserProfileService, FriendService, $location, $rootScope, 
            		 $routeParams, $cookieStore, $http) 
{
	var self = this;
	self.friend = {reqid:'',follow:'',reqdate:'',requser:'',touser:'',status:'',isonline:''};
	
	self.friends=[];
	self.users=[];    
	
	self.fetchAllUsers = function()
	{
		UserProfileService.fetchAllUsers().then(function(d)
        	{
        		self.users = d;
        		$rootScope.total = {totalrec: self.users.length};
            	console.log("Fetch all users sucess")
        	},
        	function(errResponse)
        	{
        	console.error('Error while fetching Users'+ errResponse);
        	}
        );
    };

    self.fetchAllUsers();
    
    self.listrequestedfriends = function(){
    	FriendService.listrequestedfriends().then(function(d){
    		self.friends = d;
        	console.log("Fetch all peding request users sucess")
    	},
    	function(errResponse)
    	{
    		console.error('Error while fetching Users'+ errResponse);
    	}
    	);
    };

    self.listrequestedfriends();
    
    self.viewmyfriends = function(){
    	FriendService.viewmyfriends().then(function(d){
    		self.friends = d;
        	console.log("Fetch all view friends sucess")
    	},
    	function(errResponse)
    	{
    		console.error('Error while fetching friends'+ errResponse);
    	}
    	);
    };

    self.viewmyfriends();
    
    self.addfriendReq = function(useremail){
        FriendService.addfriend(useremail).then(self.fetchAllUsers,
        	function(errResponse)
    		{
    			console.error('Error while creating User request');
    		}
    	);
	};
	
    self.addfriend = function(useremail){
    	if(confirm('Are you sure, you want to send friend request ?')) {
    		self.addfriendReq(useremail);
    	}
    };
    
    self.pendingfriends = function(){
    	self.listrequestedfriends();
    };
    
    self.viewfriends = function(){
    	self.viewmyfriends();
    };

    self.acceptfriendrequest = function(reqid){
        FriendService.acceptfriendrequest(reqid).then(self.listrequestedfriends,
        	function(errResponse)
    		{
    			console.error('Error while creating User request');
    		}
    	);
	};
	
    self.rejectfriendrequest = function(reqid){
        FriendService.rejectfriendrequest(reqid).then(self.listrequestedfriends,
        	function(errResponse)
    		{
    			console.error('Error while creating User reject request');
    		}
    	);
	};

	self.unfollowfriendfrommylist = function(reqid){
        FriendService.unfollowfriendfrommylist(reqid).then(self.listrequestedfriends,
            	function(errResponse)
        		{
        			console.error('Error while creating User reject request');
        		}
        	);
	};
	
    self.acceptrequest = function(reqid)
    {
    	self.acceptfriendrequest(reqid);
    };
    
    self.rejectrequest = function(reqid)
    {
    	self.rejectfriendrequest(reqid);
    };
    
    self.unfollowfriend = function(reqid){
    	if(confirm('Are you sure, want to unfollow selected friend ?\nif OK then you want be able to send message to friend'))
    	{
    		self.unfollowfriendfrommylist(reqid);
    	}
    }

}]);