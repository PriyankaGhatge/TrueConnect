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

import com.niit.collaborationbackend.dao.UserTypeDAO;
import com.niit.collaborationbackend.model.UserType;

@RestController
public class UserTypeController {
	
	@Autowired
	UserTypeDAO service;
	
	private static final Logger log = LoggerFactory.getLogger(UserTypeController.class);

	@RequestMapping(value = "/addusertype/", method = RequestMethod.POST)
	//Method tested
	public ResponseEntity<UserType> createUserType(@RequestBody UserType utype)
	{
		log.debug("calling => createUserType() method");
		System.out.println(utype.getUserid() + " " + utype.getUsername());
		if(service.checkUserType(utype.getUsername())==false)
		{
			log.debug("error in calling => createUserType() method");
			return new ResponseEntity<UserType>(utype, HttpStatus.CONFLICT);
		}
		else
		{
			service.userTypeUpdate(utype);
			log.debug("Update new user type");
			return new ResponseEntity<UserType>(utype, HttpStatus.OK);
		}
	}
	
	@RequestMapping(value = "/allusertype", method = RequestMethod.GET)
	//Method tested
	public ResponseEntity<List<UserType>> listAllUserType()	{

		log.debug("calling => listAllUserType() method");
		List<UserType> lsts = service.getAllUserTypes();
		if(lsts.isEmpty()){
			return new ResponseEntity<List<UserType>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<UserType>>(lsts, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/getusertype/{usertypeid}", method = RequestMethod.GET)
	//Method tested
	public ResponseEntity<UserType> getUserType(@PathVariable("usertypeid") int usertypeid)	{

		log.debug("calling => getUserType() method");
		UserType usrtype = service.getUserTypeByID(usertypeid);
		if(usrtype==null){
			return new ResponseEntity<UserType>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<UserType>(usrtype, HttpStatus.OK);
	}

	@RequestMapping(value = "/delusertype/{usertypeid}", method = RequestMethod.DELETE)
	//Method tested
	public ResponseEntity<UserType> delusertype(@PathVariable("usertypeid") int usertypeid)	{

		log.debug("calling => delUserType() method");
		boolean flag = service.deleteusertype(usertypeid);
		UserType usrtype = new UserType();
		if(!flag){
			return new ResponseEntity<UserType>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<UserType>(usrtype, HttpStatus.OK);
	}

}
