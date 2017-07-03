package com.niit.collaborationbackend.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.niit.collaborationbackend.model.UserProfile;

@EnableTransactionManagement
@Repository("userProfileDao")
public class UserProfileDAO_Impl implements UserProfileDAO {

	
	private static final Logger log = LoggerFactory.getLogger(UserProfileDAO_Impl.class);

	@Autowired
	private SessionFactory sessionFactory;

	public UserProfileDAO_Impl(SessionFactory sessionFactory){
		this.sessionFactory = sessionFactory;
	}

	@SuppressWarnings("unchecked")
	 
	@Transactional
	public List<UserProfile> getAllUsers() {
		List<UserProfile> allUsers = null;
		try{
			
			log.debug("Method => getAllUsers() execution is starting");
			allUsers = sessionFactory.getCurrentSession().createQuery("FROM UserProfile").list();
			if(allUsers==null || allUsers.isEmpty()){
				log.debug("Record not found in UserProfile table");
			}
		}
		catch(HibernateException ex){
			log.debug("Fetch Error :" + ex.getMessage());
			ex.printStackTrace();
		}
		return allUsers;
	}

	 
	@Transactional
	public boolean saveUserProfile(UserProfile userprofile) {
		try
		{
			log.debug("Method => saveUserProfile() execution is starting");
			sessionFactory.getCurrentSession().saveOrUpdate(userprofile);
			sessionFactory.getCurrentSession().flush();
			return true;
		}
		catch(HibernateException ex){
			log.debug("Data Save Error :" + ex.getMessage());
			ex.printStackTrace();
			return false;
		}
	}

	 
	@Transactional
	public boolean updateApprove(String useremail, char flag) {
		
		try{
			Session session = sessionFactory.getCurrentSession();
			String message = "";
			if(flag=='Y'){
				message = "You profile has been accepted";
			}
			else
			{
				message = "You profile has been rejected";
			}
	        Query query = session.createQuery("update UserProfile set Approved = '" + flag + "', reason = '" + message + "' where useremail like '" + useremail + "%'");
			
	        return query.executeUpdate()==1 ? true : false;
		}
		catch(HibernateException ex){
			log.debug("Data update Error :" + ex.getMessage());
			ex.printStackTrace();
			return false;
		}
	}

	 
	@Transactional
	public UserProfile getUserProfileByEmail(String useremail) {
		try
		{
			log.debug("Method => getBlogByID() execution is starting : " + useremail);
			return (UserProfile) sessionFactory.getCurrentSession().get(UserProfile.class, useremail);
		}
		catch(HibernateException ex){
			log.debug("Data fetch Error :" + ex.getMessage());
			ex.printStackTrace();
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	 
	@Transactional
	public boolean checkUserEmail(String useremail) {
		String SQL = "FROM UserProfile where upper(useremail) = '" + useremail.toUpperCase() + "'";
		log.debug("SQL :" + SQL);
		List<UserProfile> obj = sessionFactory.getCurrentSession().createQuery(SQL).list();
		return obj.isEmpty() ? true : false;
	}

	@SuppressWarnings("unchecked")
	 
	@Transactional
	public UserProfile authenticate(String useremail, String password) {
		Query query = sessionFactory.getCurrentSession().createQuery("from UserProfile where useremail = '" +
	      useremail + "' and password = '" + password + "'");
		
		List<UserProfile> userprofile = query.list();
		if(userprofile != null && !userprofile.isEmpty()){
			updateOnOffLine(useremail, 'Y');
			return userprofile.get(0);
		}
		return null;
	}

	 
	@Transactional
	public boolean updateOnOffLine(String useremail, char onoff) {
		try{
			Session session = sessionFactory.getCurrentSession();
	        Query query = session.createQuery("update UserProfile set useronline = '" + onoff + "' where useremail like '" + useremail + "%'");
			return query.executeUpdate()==1 ? true : false;
		}
		catch(HibernateException ex){
			log.debug("Data update Error :" + ex.getMessage());
			ex.printStackTrace();
			return false;
		}
	}
}
