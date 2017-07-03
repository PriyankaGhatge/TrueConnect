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
import com.niit.collaborationbackend.model.EventMaster;

@EnableTransactionManagement
@Repository("eventmasterDao")
public class EventMasterDAO_Impl implements EventMasterDAO {

	private static final Logger log = LoggerFactory.getLogger(BulletinDAO_Impl.class);

	@Autowired
	private SessionFactory sessionFactory;

	public EventMasterDAO_Impl(SessionFactory sessionFactory){
		this.sessionFactory = sessionFactory;
	}
	
	@SuppressWarnings("unchecked")
	 
	@Transactional
	public List<EventMaster> getAllEvents(char status) {
		List<EventMaster> allEvent = null;
		try{
			
			log.debug("Method => getAllEvents() execution is starting");
			allEvent = sessionFactory.getCurrentSession().createQuery("FROM EventMaster where showflag = 'Y'").list();
			if(allEvent==null || allEvent.isEmpty()){
				log.debug("Record not found in UserType table");
			}
		}
		catch(HibernateException ex){
			log.debug("Fetch Error :" + ex.getMessage());
			ex.printStackTrace();
		}
		return allEvent;
	}

	 
	@Transactional
	public EventMaster getEventByID(int evtid) {
		try
		{
			log.debug("Method => getEventByID() execution is starting");
			return (EventMaster) sessionFactory.getCurrentSession().get(EventMaster.class, evtid);
		}
		catch(HibernateException ex){
			log.debug("Data Save Error :" + ex.getMessage());
			ex.printStackTrace();
			return null;
		}
	}

	 
	@Transactional
	public boolean saveEvent(EventMaster event) {
		try
		{
			log.debug("Method => saveEvent() execution is starting");
			sessionFactory.getCurrentSession().saveOrUpdate(event);
			return true;
		}
		catch(HibernateException ex){
			log.debug("Data Save Error :" + ex.getMessage());
			ex.printStackTrace();
			return false;
		}
	}

	 
	@Transactional
	public boolean removeEvent(int evtid) {
		boolean flag;
		String SQL = "Delete from EventMaster where eventid = " + evtid;
		try{
			
			int res = sessionFactory.openStatelessSession().createQuery(SQL).executeUpdate();
			flag =  res == 1 ? true : false;
		}
		catch(HibernateException ex){
			flag=false;
		}
		return flag;
	}
}
