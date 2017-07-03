package com.niit.collaborationbackend.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.niit.collaborationbackend.dao.UserRoleDAO;
import com.niit.collaborationbackend.model.UserRole;

@RestController
public class RoleController {

	@Autowired
	UserRoleDAO service;
	
	private static final Logger log = LoggerFactory.getLogger(RoleController.class);

	@RequestMapping(value = "/adduserrole/", method = RequestMethod.POST)
	//Method Tested
	public ResponseEntity<UserRole> createUserRole(@RequestBody UserRole urole)
	{
		System.out.println("Hi in controller" + urole.getRolename());
		log.debug("calling => adduserrole() method");
		if(service.checkUserRole(urole.getRolename()))
		{
			log.debug("error in calling => adduserrole() method");
			return new ResponseEntity<UserRole>(urole, HttpStatus.CONFLICT);
		}
		else
		{
			service.userRoleUpdate(urole);
			log.debug("Update new user type");
			return new ResponseEntity<UserRole>(urole, HttpStatus.OK);
		}
	}
	
	@RequestMapping(value = "/alluserroles", method = RequestMethod.GET)
	//Method Tested
	public ResponseEntity<List<UserRole>> listAllUserRole()	{

		log.debug("calling => listAllUserRole() method");
		List<UserRole> lsts = service.getAllUserRoles();
		if(lsts.isEmpty()){
			return new ResponseEntity<List<UserRole>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<UserRole>>(lsts, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/getuserrole/{roleid}", method = RequestMethod.GET)
	//Method Tested
	public ResponseEntity<UserRole> getUserRole(@PathVariable("roleid") int roleid)	{

		log.debug("calling => getUserRole() method");
		UserRole role = service.getUserRoleByID(roleid);
		if(role==null){
			return new ResponseEntity<UserRole>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<UserRole>(role, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/deluserrole/{userroleid}", method = RequestMethod.DELETE)
	//Method tested
	public ResponseEntity<UserRole> deluserrole(@PathVariable("userroleid") int userroleid)	{

		log.debug("calling => deluserrole() method");
		boolean flag = service.deleteusertype(userroleid);
		UserRole usrrole = new UserRole();
		if(!flag){
			return new ResponseEntity<UserRole>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<UserRole>(usrrole, HttpStatus.OK);
	}

}
