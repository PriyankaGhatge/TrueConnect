'use strict';

app.controller('UserRoleController',['$scope','UserRoleService',function($scope,UserRoleService) 
{
	var self = this;
	self.userrole = {roleid:'',rolename:''};
	self.userroles=[];

	console.log("user role controller");

	self.getAllUserRoles = function() 
	{
			UserRoleService.getAllUserRoles().then(function(d) 
			{
				self.userroles=d;
			}, 
			function(errResponse) {
					console.error('Error while fetching records from table' + errResponse);
			}
		);
	};

	self.getAllUserRoles();

	self.userRoleUpdate = function(userrole){
				UserRoleService.userRoleUpdate(userrole)
					.then(
							self.getAllUserRoles, self.reset,
							function(errResponse)
							{
								console.error('Error while creating record');
							}
					);
			};
			
			self.getUserRoleByID = function(roleid)
			{
				UserRoleService.getUserRoleByID(roleid)
					.then(
							function(d)
							{
								self.userrole = d;
							},
							function(errResponse){
								console.error('Error while fetching records from table');
							}
					);
			};
			
			self.removeUserRoleByID = function(roleid)
			{
				UserRoleService.removeUserRoleByID(roleid)
					.then(
							self.getAllUserRoles,
							function(errResponse){
								console.error('Error while fetching records from table');
							}
					)
			};
			
			self.delopt = function(roleid)
			{
				if(confirm('Are you sure you want to remove this user type?' + roleid)) {
					self.removeUserRoleByID(roleid);
				}
			};
			
			self.submit = function()
			{
				self.userRoleUpdate(self.userrole);
				console.log("Saving new user", self.userrole);
			};
			
			self.reset = function()
			{
				self.userrole = {roleid:'',rolename:''};
				$scope.myform.$setPristine();
			};
			
			self.editopt = function()
			{
				
			};
   }
])