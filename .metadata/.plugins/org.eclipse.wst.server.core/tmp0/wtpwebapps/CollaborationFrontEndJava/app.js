var app = angular.module('myApp', ['ngRoute', 'ngCookies']);

app.config(function($routeProvider)
{
	$routeProvider	

	.when('/', {
		templateUrl: 'v_home/welcome.html',
		controller: 'HomeController'
	})
	.when('/userrole', {
		templateUrl: 'v_userrole/frmUserRole.html',
		controller: 'UserRoleController'
	})
	.when('/userprofile', {
		templateUrl: 'v_user/frmUserProfile.html',
		controller: 'UserProfileController'
	})
	.when('/login', {
		templateUrl: 'v_user/frmLogin.html',
		controller: 'UserProfileController'
	})
	.when('/listusersadmin', {
		templateUrl: 'v_user/frmUserListAdmin.html',
		controller: 'UserProfileController'
	})
	.when('/listallusers', {
		templateUrl: 'v_friend/frmAllUsers.html',
		controller: 'FriendController'
	})
	.when('/listpending', {
		templateUrl: 'v_friend/frmPendingRequest.html',
		controller: 'FriendController'
	})
	.when('/listmyfriends', {
		templateUrl: 'v_friend/frmViewFriends.html',
		controller: 'FriendController'
	})
	.when('/usertype', {
		templateUrl: 'v_usertype/frmUserType.html',
		controller: 'UserTypeController'
	})
	.when('/viewjobs', {
		templateUrl: 'v_job/frmListJob.html',
		controller: 'JobController'
	})
	.when('/addjob', {
		templateUrl: 'v_job/frmJob.html',
		controller: 'JobController'
	})
	.when('/viewappliedjobs',
	{
		templateUrl: 'v_job/frmViewMyJobApply.html',
		controller: 'JobController'
	})
	.when('/approveblogs', {
		templateUrl: 'v_blog/frmUserBlogAdmin.html',
		controller: 'BlogController'
	})
	.when('/viewblogs', {
		templateUrl: 'v_blog/frmListBlog.html',
		controller: 'BlogController'
	})
	.when('/addblog', {
		templateUrl: 'v_blog/frmBlog.html',
		controller: 'BlogController'
	})
	.when('/myblog', {
		templateUrl: 'v_blog/frmMyBlog.html',
		controller: 'BlogController'
	})
	.when('/approveforums', {
		templateUrl: 'v_forum/frmUserForumAdmin.html',
		controller: 'ForumController'
	})
	.when('/viewforums', {
		templateUrl: 'v_forum/frmListForum.html',
		controller: 'ForumController'
	})
	.when('/addforum', {
		templateUrl: 'v_forum/frmForum.html',
		controller: 'ForumController'
	})
	.when('/myforum', {
		templateUrl: 'v_forum/frmMyForum.html',
		controller: 'ForumController'
	})
	.when('/addforumcmts', {
		templateUrl: 'v_forum/frmAddComment.html',
		controller: 'ForumController'
	})
	.when('/viewevents', {
		templateUrl: 'v_event/frmEventList.html',
		controller: 'EventController'
	})
	.when('/addevent', {
		templateUrl: 'v_event/frmEvent.html',
		controller: 'EventController'
	})
	.when('/viewbulletin', {
		templateUrl: 'v_bulletin/frmListBulletin.html',
		controller: 'BulletinController'
	})
	.when('/addbulletin', {
		templateUrl: 'v_bulletin/frmBulletin.html',
		controller: 'BulletinController'
	})
	.when('/listallusers', {
		templateUrl: 'v_friend/frmAllUsers.html',
		controller: 'FriendController'
	})
	.when('/enterchat', {
		templateUrl: 'v_chat/frmChat.html',
		controller: 'UserProfileController'
	})
	.when('/chatroom', {
		templateUrl: 'v_chat/frmEnterChat.html',
		controller: 'UserProfileController'
	})
	.otherwise({redirectTo: '/'});
});

app.run( function($rootScope,$location,$cookieStore,$http){
    
    $rootScope.$on('$locationChangeStart',function(event,next,current){
        console.log("$locationChangeStart")
        var restrictedPage=$.inArray($location.path(),['/login', '/userrole', '/userprofile', '/addjob','/addblog'])== -1;
        console.log("restrictedpage ;"+restrictedPage)
        var loggedIn=$rootScope.currentUser;
        console.log("loggedin:"+loggedIn)
        if(restrictedPage & !loggedIn){
            console.log("navigation to login page")
            $location.path('/home');
        }
        
    });
    
    $rootScope.currentUser=$cookieStore.get('currentUser')||{};
    if($rootScope.currentUser){
        $http.defaults.headers.common['Authorization']= 'Basic' + $rootScope.currentUser;
    }
    
});