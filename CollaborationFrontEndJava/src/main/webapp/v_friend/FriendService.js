'use strict';

app.factory('FriendService', ['$http','$q','$rootScope', function($http, $q, $rootScope) 
{
	console.log("user friend service");

	var baseurl = '/CollaborationBackEndJava'
	return {
		addfriend : function(useremail) {
		return $http
			.post(baseurl + '/addfriend/' + useremail + '/')
			.then(function(response) {
			return response.data;
			}, function(errResponse) {
				console.error('Error while fetching Users');
			});
			return $q.reject(errResponse);
		},
				
		listrequestedfriends : function(){
			return $http.get(baseurl + '/listrequestedfriends').then(function(response){
				return response.data;
			},
			function(errResponse){
				console.error('Error while fetching Users');
			});
			return $q.reject(errResponse);
		},
		
		viewmyfriends : function(){
			return $http.get(baseurl + '/viewmyfriends').then(function(response){
				return response.data;
			},
			function(errResponse){
				console.error('Error while fetching Users');
			});
			return $q.reject(errResponse);
		},
		
		acceptfriendrequest: function(reqid){
			return $http.post(baseurl + '/acceptfriendrequest/' + reqid + '/')
				.then(function(response){
					return response.data;
				},
				function(errResponse){
					console.error('Error while updating User request');
				}
			);
		},
		
		rejectfriendrequest: function(reqid){
			return $http.post(baseurl + '/rejectfriendrequest/' + reqid + '/')
				.then(function(response){
					return response.data;
				},
				function(errResponse){
					console.error('Error while updating User request reject');
				}
			);
		},

		unfollowfriendfrommylist: function(reqid){
			return $http.post(baseurl + '/unfollowfriendfrommylist/' + reqid + '/')
				.then(function(response){
					return response.data;
				},
				function(errResponse){
					console.error('Error while updating User request reject');
				}
			);
		},
	}
}]);
