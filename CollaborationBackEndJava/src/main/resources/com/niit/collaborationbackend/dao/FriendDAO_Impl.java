package com.niit.collaborationbackend.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.niit.collaborationbackend.model.Friends;

@EnableTransactionManagement
@Repository("friendDao")
public class FriendDAO_Impl implements FriendDAO {

	private static final Logger log = LoggerFactory.getLogger(FriendDAO_Impl.class);

	boolean update = false;

	@Autowired
	private SessionFactory sessionFactory;

	public FriendDAO_Impl(SessionFactory sessionFactory){
		this.sessionFactory = sessionFactory;
	}

	 
	public boolean sendFriendRequest(Friends friend) {
		try
		{
			log.debug("Method => sendFriendRequest() execution is starting");
			sessionFactory.getCurrentSession().saveOrUpdate(friend);
			return true;
		}
		catch(HibernateException ex){
			log.debug("Data Save Error :" + ex.getMessage());
			ex.printStackTrace();
			return false;
		}
	}

	 
	public boolean confirmRequest(String fromUser, String toUser) {
		return getStatusUpdate(toUser, fromUser, 'Y');
	}

	 
	public boolean checkAlreadyFriendStatus(String fromUser, String toUser) {
		Friends frnds = (Friends) sessionFactory.getCurrentSession().createQuery("FROM Friends where requser = '" + fromUser + "' and touser = '" + toUser + "'").list();
		update = false;
		if(frnds!=null){
			update = frnds.getFollow()=='Y' ? true : false;
		}
		return false;
	}

	 
	public boolean updateUnFollow(String fromUser, String toUser) {
		return getUnFollowUpdate(toUser, fromUser, 'Y');
	}

	@SuppressWarnings("unchecked")
	 
	public List<Friends> viewFriends(String userName) {
		return sessionFactory.getCurrentSession().createSQLQuery("FROM Friends where touser = '" + userName + "' and status = 'Y' and follow = 'Y'").list();
	}

	@SuppressWarnings("unchecked")
	 
	public List<Friends> viewRequestedUsers(String userName) {
		return sessionFactory.getCurrentSession().createSQLQuery("FROM Friends where touser = '" + userName + "'").list();
	}
	
	private boolean getStatusUpdate(String toUser, String fromUser, char flag){
		try
		{
			log.debug("Method => confirmRequest() execution is starting");
			String SQL = "Update FRIENDS set status = '" + flag + "' where requser = '" + toUser + "' and touser = '" + fromUser + "'";
			Session session = sessionFactory.getCurrentSession();
		
			session.createQuery(SQL).executeUpdate();
			update = true;;
		}
		catch(HibernateException ex){
			log.debug("Error :" + ex.getMessage());
			update = false;
		}
		return update;
	}
	private boolean getUnFollowUpdate(String toUser, String fromUser, char flag){
		try
		{
			log.debug("Method => confirmRequest() execution is starting");
			String SQL = "Update FRIENDS set status = '" + flag + "' where requser = '" + toUser + "' and touser = '" + fromUser + "'";
			Session session = sessionFactory.getCurrentSession();
		
			session.createQuery(SQL).executeUpdate();
			update = true;;
		}
		catch(HibernateException ex){
			log.debug("Error :" + ex.getMessage());
			update = false;
		}
		return update;
	}
}
