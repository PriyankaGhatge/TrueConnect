package com.niit.collaborationbackend.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.hibernate.HibernateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.niit.collaborationbackend.dao.FriendsDAO;
import com.niit.collaborationbackend.model.Friends;

@RestController
public class FriendsController {

	@Autowired
	FriendsDAO frndservice;
		
	private static final Logger log = LoggerFactory.getLogger(FriendsController.class);

	@SuppressWarnings("unused")
	@RequestMapping(value = "/addfriend/{useremail}/", method = RequestMethod.POST)
	public ResponseEntity<Friends> addfriend(@PathVariable("useremail") String useremail, 
				HttpSession session){
		log.debug("Execute....addfriend() method");
		String loggeduser = (String)session.getAttribute("loggeduser");
		
		Friends frnd = new Friends();
		boolean flag = false;
		try
		{
			Friends temp = frndservice.getFriend(loggeduser, useremail);
			if (temp==null)
			{
				frnd.setRequser(loggeduser);
				frnd.setTouser(useremail);
				frnd.setStatus('N');
				frnd.setFollow('N');
				frnd.setIsonline('N');
				flag = frndservice.saveRequest(frnd);				
			}
		}
		catch(HibernateException ex)
		{
			System.out.println(ex.getMessage());
		}
		return new ResponseEntity<Friends>(frnd, HttpStatus.OK);
	}
	@RequestMapping(value = "/listrequestedfriends", method = RequestMethod.GET)
	public ResponseEntity<List<Friends>> listrequestedfriends(HttpSession session){

		log.debug("Execute....addfriend() method");
		String loggeduser = (String)session.getAttribute("loggeduser");
		
		List<Friends> lsts = frndservice.listRequestedFriends(loggeduser);
		if(!lsts.isEmpty())
			return new ResponseEntity<List<Friends>>(lsts, HttpStatus.OK);
		else
			return new ResponseEntity<List<Friends>>(lsts, HttpStatus.NO_CONTENT);
	}

	@RequestMapping(value = "/viewmyfriends", method = RequestMethod.GET)
	public ResponseEntity<List<Friends>> viewmyfriends(HttpSession session){

		log.debug("Execute....addfriend() method");
		String loggeduser = (String)session.getAttribute("loggeduser");
		
		List<Friends> lsts = frndservice.listAllFriends(loggeduser);
		if(!lsts.isEmpty())
			return new ResponseEntity<List<Friends>>(lsts, HttpStatus.OK);
		else
			return new ResponseEntity<List<Friends>>(lsts, HttpStatus.NO_CONTENT);
	}

	@RequestMapping(value = "/acceptfriendrequest/{reqid}/", method = RequestMethod.POST)
	public ResponseEntity<Friends>acceptfriendrequest(@PathVariable("reqid") int reqid){
		Friends friend = new Friends();
		boolean flag = frndservice.updateRequest(reqid, 'A');
		if(flag)
			return new ResponseEntity<Friends>(friend, HttpStatus.OK);
		else
			return new ResponseEntity<Friends>(friend, HttpStatus.NOT_FOUND);
	}

	@RequestMapping(value = "/rejectfriendrequest/{reqid}/", method = RequestMethod.POST)
	public ResponseEntity<Friends>rejectfriendrequest(@PathVariable("reqid") int reqid, 
			HttpSession session){
		Friends friend = new Friends();
		boolean flag = frndservice.updateRequest(reqid, 'R');
		if(flag)
			return new ResponseEntity<Friends>(friend, HttpStatus.OK);
		else
			return new ResponseEntity<Friends>(friend, HttpStatus.NOT_FOUND);
	}

	@RequestMapping(value = "/unfollowfriendfrommylist/{reqid}/", method = RequestMethod.POST)
	public ResponseEntity<Friends>unfollowfriendfrommylist(@PathVariable("reqid") int reqid, 
			HttpSession session){
		Friends friend = new Friends();
		boolean flag = frndservice.updateRequest(reqid, 'U');
		if(flag)
			return new ResponseEntity<Friends>(friend, HttpStatus.OK);
		else
			return new ResponseEntity<Friends>(friend, HttpStatus.NOT_FOUND);
	}

}
