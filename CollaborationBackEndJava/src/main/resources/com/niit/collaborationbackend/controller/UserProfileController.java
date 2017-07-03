package com.niit.collaborationbackend.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

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

import com.niit.collaborationbackend.dao.UserProfileDAO;
import com.niit.collaborationbackend.dao.UserRoleDAO;
import com.niit.collaborationbackend.dao.UserTypeDAO;
import com.niit.collaborationbackend.model.UserProfile;
import com.niit.collaborationbackend.model.UserRole;
import com.niit.collaborationbackend.model.UserType;

@RestController
public class UserProfileController {

	@Autowired
	UserProfileDAO service;
	
	@Autowired
	UserRoleDAO urdService;
	
	@Autowired
	UserTypeDAO utdService;
	
	
	private static final Logger log = LoggerFactory.getLogger(UserProfileController.class);
	
	@RequestMapping(value = "/adduserprofile/", method = RequestMethod.POST)
	//Method Tested
	public ResponseEntity<UserProfile> createUserProfile(@RequestBody UserProfile userprofile)
	{
		log.debug("calling => createUserProfile() method");

		if(service.checkUserEmail(userprofile.getUseremail())==false)
		{
			log.debug("error in calling => createUserProfile() method");
			return new ResponseEntity<UserProfile>(userprofile, HttpStatus.CONFLICT);
		}
		else
		{
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Date date = new Date();

			userprofile.setRegdate(dateFormat.format(date));
			userprofile.setLastmodifiedddate(dateFormat.format(date));
			userprofile.setApproved('N');
			userprofile.setUseronline('N');
			/*
			switch(userprofile.getUseridentity()){
				case "Student": userprofile.setCurrentrole("Role_Student");break;
				case "Alumini": userprofile.setCurrentrole("Role_Alumini");break;
				case "External": userprofile.setCurrentrole("Role_External");break;
			}
			*/
//			userprofile.setCurrentrole("Role_Student");
			userprofile.setCurrentrole("Role_Admin");
			service.saveUserProfile(userprofile);
			log.debug("Update new user type");
			return new ResponseEntity<UserProfile>(userprofile, HttpStatus.OK);
		}
	}
	
	@RequestMapping(value = "/updateuserprofile/", method = RequestMethod.POST)
	public ResponseEntity<UserProfile> udpateUserProfile(@RequestBody UserProfile userprofile)
	{
		log.debug("calling => udpateUserProfile() method");

		service.saveUserProfile(userprofile);
		log.debug("Update user profile");
		return new ResponseEntity<UserProfile>(userprofile, HttpStatus.OK);
	}
	@RequestMapping(value = "/allusers", method = RequestMethod.GET)
	//Method Tested
	public ResponseEntity<List<UserProfile>> listAllUsers()	{

		log.debug("calling => UserProfile() method");
		List<UserProfile> lsts = service.getAllUsers();
		log.debug("total records:" + lsts.size());
		if(lsts.isEmpty()){
			return new ResponseEntity<List<UserProfile>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<UserProfile>>(lsts, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/getuserbyemail/{useremail:.*}", method = RequestMethod.GET)
	//Method Tested
	public ResponseEntity<UserProfile> getuserbyemail(@PathVariable("useremail") String useremail)	{
		log.debug("calling => getuserbyemail() method " + useremail);
		UserProfile usrprofile = service.getUserProfileByEmail(useremail);
		if(usrprofile==null)
		{
			return new ResponseEntity<UserProfile>(HttpStatus.NO_CONTENT);
		}
		log.debug("Record :" + usrprofile.getUseremail());
		return new ResponseEntity<UserProfile>(usrprofile, HttpStatus.OK);
	}

	@RequestMapping(value = "/getuserapprove/{useremail}/{yesno}", method = RequestMethod.POST)
	//Method Tested
	public ResponseEntity<UserProfile> getuserapprove(@PathVariable("useremail") String useremail,
			@PathVariable("yesno") char yesno)	{

		log.debug("calling => getuserbyemail() method");
		boolean flag = service.updateApprove(useremail, yesno);
		if(!flag){
			return new ResponseEntity<UserProfile>(HttpStatus.BAD_REQUEST);
		}
		UserProfile usrprofile = service.getUserProfileByEmail(useremail);
		return new ResponseEntity<UserProfile>(usrprofile, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public ResponseEntity<UserProfile> authenticate(@RequestBody UserProfile userprofile, HttpSession session)
	{
		log.debug("calling => udpateUserProfile() method");
		UserProfile userobj = service.authenticate(userprofile.getUseremail(), userprofile.getPassword());		
		if(userobj==null){
			userobj = new UserProfile();
		}
		else
		{
			service.updateOnOffLine(userprofile.getUseremail(),'Y');
			session.setAttribute("profile", userobj);
			session.setAttribute("loggeduser", userobj.getUseremail());
		}
		return new ResponseEntity<UserProfile>(userobj, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/allroles", method = RequestMethod.GET)
	public ResponseEntity<List<UserRole>> list_AllRoles()	{

		log.debug("calling => listAllRoles() method");
		List<UserRole> lsts = urdService.getAllUserRoles();
		if(lsts.isEmpty()){
			return new ResponseEntity<List<UserRole>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<UserRole>>(lsts, HttpStatus.OK);
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public ResponseEntity<UserProfile> logoutuser(HttpSession session)	{
		String loggeduser = (String)session.getAttribute("loggeduser");
		session.invalidate();
		System.out.println("Logged user :" + loggeduser);
		service.updateOnOffLine(loggeduser, 'N');
		return new ResponseEntity<UserProfile>(HttpStatus.OK);
	}

	@RequestMapping(value = "/allusrtypes", method = RequestMethod.GET)
	public ResponseEntity<List<UserType>> list_AllTypes()	{

		log.debug("calling => listAllTypes() method");
		List<UserType> lsts = utdService.getAllUserTypes();
		if(lsts.isEmpty()){
			return new ResponseEntity<List<UserType>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<UserType>>(lsts, HttpStatus.OK);
	}
}