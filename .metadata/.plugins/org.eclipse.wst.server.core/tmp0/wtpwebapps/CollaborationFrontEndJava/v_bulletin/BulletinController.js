'use strict';

app.controller('BulletinController', ['$scope', 'BulletinService', '$location', '$rootScope',
                                         function($scope, BulletinService, $location, $rootScope) {
                            	
	console.log("Bulletin controller");

	var self = this;
	self.bulletin = {bulid : '',postdt : '',bulsubject : '',buldescription : '',flagShow : ''};
	self.bulletins = [];

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

	self.createNewBulletin = function(bulletin) 
	{
		BulletinService.createNewBulletin(bulletin).then(self.reset,
		function(errResponse) 
		{
			console.error('Error while creating record');
		});
	};

	self.submit = function() 
	{
		self.createNewBulletin(self.bulletin);
		console.log("Saving new Bulletin", self.bulletin);
	};

	self.reset = function() 
	{
		self.bulletin = {bulid : '',postdt : '',bulsubject : '',buldescription : '',flagShow : ''};
		$scope.myform.$setPristine();
	};
}]);
