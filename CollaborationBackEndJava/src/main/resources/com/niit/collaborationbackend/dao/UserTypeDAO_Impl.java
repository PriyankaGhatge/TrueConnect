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

import com.niit.collaborationbackend.model.UserType;

@EnableTransactionManagement
@Repository("userTypeDao")
public class UserTypeDAO_Impl implements UserTypeDAO {

	private static final Logger log = LoggerFactory.getLogger(UserTypeDAO_Impl.class);

	@Autowired
	private SessionFactory sessionFactory;

	public UserTypeDAO_Impl(SessionFactory sessionFactory){
		this.sessionFactory = sessionFactory;
	}

	@SuppressWarnings("unchecked")
	 
	@Transactional
	public List<UserType> getAllUserTypes() {
		List<UserType> allUType = null;
		try{
			
			log.debug("Method => getAllUserTypes() execution is starting");
			allUType = sessionFactory.getCurrentSession().createQuery("FROM UserType").list();
			if(allUType==null || allUType.isEmpty()){
				log.debug("Record not found in UserType table");
			}
		}
		catch(HibernateException ex){
			log.debug("Fetch Error :" + ex.getMessage());
			ex.printStackTrace();
		}
		return allUType;
	}

	 
	@Transactional
	public boolean userTypeUpdate(UserType userType) {
		try
		{
			log.debug("Method => userTypeUpdate() execution is starting");
			sessionFactory.getCurrentSession().saveOrUpdate(userType);
			return true;
		}
		catch(HibernateException ex){
			log.debug("Data Save Error :" + ex.getMessage());
			ex.printStackTrace();
			return false;
		}
	}

	 
	@Transactional
	public UserType getUserTypeByID(int usertypeid) {
		try
		{
			log.debug("Method => getUserTypeByID() execution is starting");
			return (UserType) sessionFactory.getCurrentSession().get(UserType.class, usertypeid);
		}
		catch(HibernateException ex){
			log.debug("Data Save Error :" + ex.getMessage());
			ex.printStackTrace();
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	 
	@Transactional
	public boolean checkUserType(String utype) {
		String SQL = "FROM UserType where upper(username) = '" + utype.toUpperCase() + "'";
		log.debug("SQL Statement :" + SQL);
		List<UserType> obj = sessionFactory.getCurrentSession().createQuery(SQL).list();
		return obj.isEmpty() ? true : false;
	}

	 
	public boolean deleteusertype(int usertypeid) {
		boolean flag;
		String SQL = "Delete from USerType where userid = " + usertypeid;
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
