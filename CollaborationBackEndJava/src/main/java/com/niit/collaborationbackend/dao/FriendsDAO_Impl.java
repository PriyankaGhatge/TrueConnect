package com.niit.collaborationbackend.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.niit.collaborationbackend.model.Friends;

@EnableTransactionManagement
@Repository("friendsDAO")
public class FriendsDAO_Impl implements FriendsDAO {

	private static final Logger log = LoggerFactory.getLogger(FriendsDAO_Impl.class);

	@Autowired
	private SessionFactory sessionFactory;

	public FriendsDAO_Impl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@SuppressWarnings("unchecked")
	 
	@Transactional
	public List<Friends> listAllFriends(String useremail) {
		List<Friends> allFriends = null;
		try {

			log.debug("Method => listAllFriends() execution is starting");
			allFriends = sessionFactory.getCurrentSession()
					.createQuery("FROM Friends where (requser ='" + useremail + "' or touser = '" +  useremail + "') and status = 'A'").list();
			if (allFriends == null || allFriends.isEmpty()) {
				log.debug("Record not found in Friends table");
			}
		} catch (HibernateException ex) {
			log.debug("Fetch Error :" + ex.getMessage());
			ex.printStackTrace();
		}
		return allFriends;
	}

	@SuppressWarnings("unchecked")
	 
	@Transactional
	public Friends getFriend(String useremail, String friendid) {

		List<Friends> friend = null;
		log.debug("Method => getFriend() execution is starting");
		String SQL = "FROM Friends where requser ='" + useremail + "' and touser like '" + friendid + "%'";
		System.out.println("SQL :" + SQL);
		friend = sessionFactory.getCurrentSession().createQuery(SQL).list();
		if (!friend.isEmpty()) {
			return friend.get(0);
		}
		return null;
	}

	 
	@Transactional
	public boolean saveRequest(Friends friend) {
		try
		{
			log.debug("Method => saveRequest() execution is starting");
			sessionFactory.getCurrentSession().saveOrUpdate(friend);
			return true;
		}
		catch(HibernateException ex){
			log.debug("Data Save Error :" + ex.getMessage());
			ex.printStackTrace();
			return false;
		}
	}

	 
	@Transactional
	public boolean updateRequest(int reqid, char reply) {
		try
		{
			log.debug("Method => updateRequest() execution is starting");
			int result = sessionFactory.getCurrentSession().createQuery("update Friends set status = '" + reply + 
					"' where reqid = " + reqid).executeUpdate();
			return result == 1 ? true : false;
		}
		catch(HibernateException ex){
			log.debug("Data Save Error :" + ex.getMessage());
			ex.printStackTrace();
			return false;
		}
	}

	 
	@Transactional
	public boolean deleteFriend(String useremail, String friendid) {
		
		Friends friend = new Friends();
		friend.setRequser(useremail);
		friend.setTouser(friendid);
		try{
			sessionFactory.openSession().delete(friend);
			return true;
		}
		catch(HibernateException ex){
			return false;
		}
	}

	@SuppressWarnings("unchecked")
	 
	@Transactional
	public List<Friends> listRequestedFriends(String useremail) {
		List<Friends> allFriends = null;
		try {

			log.debug("Method => listAllFriends() execution is starting");
			allFriends = sessionFactory.getCurrentSession()
					.createQuery("FROM Friends where touser ='" + useremail + "' and status = 'N'").list();
			System.out.println("Pending : " + allFriends.size());
			if (allFriends == null || allFriends.isEmpty()) {
				log.debug("Record not found in Friends table");
			}
		} catch (HibernateException ex) {
			log.debug("Fetch Error :" + ex.getMessage());
			ex.printStackTrace();
		}
		return allFriends;
	}

	 
	@Transactional
	public boolean updateStatus(String useremail, char status) {
		
		try
		{
			sessionFactory.openSession().createQuery("update Friends set isonline = '" + 
					status + "' where requser = '" + useremail + "'").executeUpdate();
			return true;
		}
		catch (HibernateException ex) 
		{
			log.debug("Uddate Error :" + ex.getMessage());
			ex.printStackTrace();
		}
		return false;
	}
}
