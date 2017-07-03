'use strict';

app.controller('UserProfileController', ['$scope', '$http', '$cookieStore', 'UserProfileService', 'BlogService', 'BulletinService','EventService','JobService',
                         '$location', '$rootScope',
             function($scope, $http,  $cookieStore, UserProfileService, BlogService, BulletinService, EventService, JobService,
            		 $location, $rootScope, $routeParams) {
	
	console.log("user profile controller");

	var self = this;
    self.user=
    {
    	approved:'',
    	avtar:'',
    	city:'',
    	currentrole:'',
    	dateofbirth:'',
    	fstname:'',
    	gender:'',
    	lastmodifieddate:'',
    	lstname:'',
    	midname:'',
    	mobileno:'',
    	password:'',
    	regdate:'',
    	useremail:'',
    	useridentity:'',
    	useronline:''
    };
    self.users=[];
    self.blogs=[];
    self.bulletins = [];
    self.events=[];
    self.jobs=[];
    
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
    
	self.fetchAllBlogs();
	
	self.fetchAllBulletin = function() 
	{
		BulletinService.fetchAllBulletin().
			then(function(d) 
			{
				self.bulletins = d;
				console.log("Fetch all butlletins sucess")
			}, 
			function(errResponse) 
			{
				console.error('Error while fetching butlletins' + errResponse);
			});
	};

	self.fetchAllBulletin();
	
    self.fetchAllUsers = function(){
        UserProfileService.fetchAllUsers().then
        (function(d)
        		{
        		self.users = d;
            	console.log("Fetch all users sucess")
        	},
        	function(errResponse)
        	{
        	console.error('Error while fetching Users'+ errResponse);
        	}
        );
    };

    self.fetchAllEvents = function()
    {
        EventService.fetchAllEvents().then(function(d)
    	{
        	self.events = d;
            console.log("Fetch all event sucess")
        },
        function(errResponse)
        {
        	console.error('Error while fetching Events'+ errResponse);
        }
      );
   };
    
   self.fetchAllEvents();
   
   self.fetchAllJobs = function()
   {
       JobService.fetchAllJobs().then(function(d)
   	{
       	self.jobs = d;
           console.log("Fetch all jobs sucess")
       },
       function(errResponse)
       {
       	console.error('Error while fetching jobs'+ errResponse);
       }
     );
  };
   
  self.fetchAllJobs();
   /* 
 	alluserroles = function(){
        UserProfileService.alluserroles().then
        (function(d)
        		{
        		self.userroles = d;
            	console.log("Fetch all user role sucess")
        	},
        	function(errResponse)
        	{
        	console.error('Error while fetching User roles'+ errResponse);
        	}
        );
    };

    allusertypes = function(){
        UserProfileService.allusertypes().then
        (function(d)
        		{
        		self.usertypes = d;
            	console.log("Fetch all users type sucess")
        	},
        	function(errResponse)
        	{
        	console.error('Error while fetching User types'+ errResponse);
        	}
        );
    };
    
    self.alluserroles();
    self.allusertypes();

    */
    self.createUser = function(user)
    {
        UserProfileService.createUser(user)
        	.then(
        			self.reset,
        			function(errResponse)
        			{
        				console.error('Error while creating User');
        			}
        		);
    };

    self.authenticate = function(user){
         UserProfileService.authenticate(user)
             .then(
            		 function(d) {
                         self.user = d;
                         if(self.user.useremail!=="undefined")
                         {
                        	 if(self.user.approved=='Y')
     	    				{
     	    					$rootScope.currentUser = 
     	    					{
     	    							useremail: self.user.useremail,
     	    							username: self.user.fstname + '' + self.user.lstname,
     	    							currentrole: self.user.currentrole
     	    					};
     	    					$http.defaults.headers.common['Authorization'] = 'Basic' + $rootScope.currentUser;
            					$cookieStore.put('currentUser',$rootScope.currentUser);
            					console.log("Valid credentials, navigate to home page");
            					$location.path('/');
     	    				}
     	    				else
     	    				{
     	    					alert('Your request is still pending with Administrator, try later');
     	    				}
                         }
                         else
                         {
                        	 alert("Invalid user credentials, try again!!!");
                        	 $location.path('/login');
                        	 self.user.useremail = "";
                        	 self.user.password = "";
                         }
                     },
                     function(errResponse)
                     {
                    	 console.error('Error while authenticate User');
                     }
             );
    };
     
    self.fetchAllUsers();

    self.loginclick = function(){
   		self.authenticate(self.user);
    };

    self.approveduser = function(useremail,yesno)
    {
        UserProfileService.approveduser(useremail,yesno)
        	.then(
        			self.fetchAllUsers,    			
        			function(errResponse)
        			{
        				console.error('Error while creating User');
        			}
        		);
    };

    self.approveuser = function(useremail){
    	if(confirm('Are you sure you want to approve this user?')) {
    		self.approveduser(useremail,'Y');
    	}
    }

    self.rejectuser = function(useremail){
    	if(confirm('Are you sure you want to reject this user?')) {
    		self.approveduser(useremail,'R');
    	}
    }
    
    self.logout=function()
    {
    	$rootScope.currentUser={};
    	$cookieStore.remove('currentUser');
    	UserProfileService.logout()
    	alert("Thanks for visiting website,\n Visit again....");
    };

    self.logoutuser=function()
    {
    	self.logout();
    };

    self.submit = function() 
    {
        console.log('Saving New User', self.user);
        self.createUser(self.user);
        self.reset();
    };

    self.reset = function()
    {
        self.user=
        {
        	approved:'',
        	avtar:'',
        	city:'',
        	currentrole:'',
        	dateofbirth:'',
        	fstname:'',
        	gender:'',
        	lastmodifieddate:'',
        	lstname:'',
        	midname:'',
        	mobileno:'',
        	password:'',
        	regdate:'',
        	useremail:'',
        	useridentity:'',
        	useronline:''};
        $scope.myForm.$setPristine(); //reset Form
    }
}]);