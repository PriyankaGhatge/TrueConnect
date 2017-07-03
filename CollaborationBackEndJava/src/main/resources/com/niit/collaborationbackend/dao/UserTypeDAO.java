package com.niit.collaborationbackend.dao;

import java.util.List;

import com.niit.collaborationbackend.model.UserType;

public interface UserTypeDAO {

	public List<UserType> getAllUserTypes();
	
	public boolean userTypeUpdate(UserType userType);
		
	public UserType getUserTypeByID(int usertypeid);
	
	public boolean checkUserType(String utype);
	
	public boolean deleteusertype(int usertypeid);
}
