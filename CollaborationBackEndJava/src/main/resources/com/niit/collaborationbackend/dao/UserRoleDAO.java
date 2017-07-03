package com.niit.collaborationbackend.dao;

import java.util.List;

import com.niit.collaborationbackend.model.UserRole;

public interface UserRoleDAO {

	public List<UserRole> getAllUserRoles();
	
	public boolean userRoleUpdate(UserRole userRole);
		
	UserRole getUserRoleByID(int roleid);

	boolean checkUserRole(String urole);
	
	public boolean deleteusertype(int userroleid);
}
