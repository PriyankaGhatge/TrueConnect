package com.niit.collaborationbackend.dao;

import java.util.List;

import com.niit.collaborationbackend.model.Friends;

public interface FriendDAO {

	public boolean sendFriendRequest(Friends friend);
	
	public boolean confirmRequest(String fromUser, String toUser);
	
	public boolean checkAlreadyFriendStatus(String fromUser, String toUser);
	
	public boolean updateUnFollow(String fromUser, String toUser);
	
	public List<Friends> viewFriends(String userName);
	
	public List<Friends> viewRequestedUsers(String userName);

	/*
	    getFriendDetails(friendid);
	    sendfriendrequest(friendid);
	    approve(), reject();
	    getallfriends();
	 */
}
