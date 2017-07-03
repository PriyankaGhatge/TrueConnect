'use strict';

app.controller('UserTypeController', ['$scope', 'UserTypeService', function($scope, UserTypeService) 
{
	var self = this;
	self.usertype = {userid:'',username:''};
    self.usertypes=[];
    console.log("user type controller");
    
    self.getAllUserTypes = function()
    {
    	UserTypeService.getAllUserTypes()
    		.then(
    				function(d) {
    					self.usertypes = d;
    				},
    				function(errResponse){
    					console.error('Error while fething records from table' + errResponse);
    				}
    			);
    };
    
	self.getAllUserTypes();
	
    self.userTypeUpdate = function(usertype){
    	UserTypeService.userTypeUpdate(usertype)
    		.then(
    				self.getAllUserTypes, self.reset,
    				function(errResponse)
    				{
    					console.error('Error while creating record');
    				}
    		);
    };
    
    self.getUserTypeByID = function(usertypeid)
    {
    	UserTypeService.getUserTypeByID(usertypeid)
    		.then(
    				function(d)
    				{
    					self.usertype = d;
    				},
    				function(errResponse){
    					console.error('Error while fething records from table');
    				}
    		);
    };
    
    self.removeUserTypeByID = function(usertypeid)
    {
    	alert('in function data ' + usertypeid);
    	UserTypeService.removeUserTypeByID(usertypeid)
    		.then(
    				self.getAllUserTypes,
    				function(errResponse){
    					console.error('Error while fetching records from table');
    				}
    		)
    };
    
    self.delopt = function(usertypeid)
    {
    	if(confirm('Are you sure you want to remove this user type?')) {
    		self.removeUserTypeByID(usertypeid);
    	}
    };
    
    self.submit = function()
    {
    	self.userTypeUpdate(self.usertype);
    	console.log("Saving new user", self.usertype);
    };
    
    self.reset = function()
    {
    	self.usertype = {userid:'',username:''};
    	$scope.myform.$setPristine();
    };
    
    self.editopt = function()
    {
    	
    };
  }
])