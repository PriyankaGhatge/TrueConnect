package com.niit.collaborationbackend.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.niit.collaborationbackend.model.UserRole;

@EnableTransactionManagement
@Repository("userRoleDao")
public class UserRoleDAO_Impl implements UserRoleDAO {

	private static final Logger log = LoggerFactory.getLogger(UserRoleDAO_Impl.class);

	@Autowired
	private SessionFactory sessionFactory;

	public UserRoleDAO_Impl(SessionFactory sessionFactory){
		this.sessionFactory = sessionFactory;
	}
	
	@SuppressWarnings("unchecked")
	 
	@Transactional
	public List<UserRole> getAllUserRoles() {
		List<UserRole> allRoles = null;
		try{
			
			log.debug("Method => getAllUserRoles() execution is starting");
			allRoles = sessionFactory.getCurrentSession().createQuery("FROM UserRole").list();
			if(allRoles==null || allRoles.isEmpty()){
				log.debug("Record not found in Userrole table");
			}
		}
		catch(HibernateException ex){
			log.debug("Fetch Error :" + ex.getMessage());
			ex.printStackTrace();
		}
		return allRoles;
	}

	 
	@Transactional
	public boolean userRoleUpdate(UserRole userRole) {
		try
		{
			log.debug("Method => userRoleUpdate() execution is starting");
			sessionFactory.getCurrentSession().saveOrUpdate(userRole);
			return true;
		}
		catch(HibernateException ex){
			log.debug("Data Save Error :" + ex.getMessage());
			ex.printStackTrace();
			return false;
		}
	}

	 
	@Transactional
	public UserRole getUserRoleByID(int roleid) {
		try
		{
			log.debug("Method => getUserRoleByID() execution is starting");
			return (UserRole) sessionFactory.getCurrentSession().get(UserRole.class, roleid);
		}
		catch(HibernateException ex){
			log.debug("Data Save Error :" + ex.getMessage());
			ex.printStackTrace();
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	 
	@Transactional
	public boolean checkUserRole(String urole) {
		String SQL = "FROM UserRole where upper(rolename) = '" + urole.toUpperCase() + "'";
		List<UserRole> obj = sessionFactory.getCurrentSession().createQuery(SQL).list();
		return obj != null ? true : false;
	}

	 
	public boolean deleteusertype(int userroleid) {
		boolean flag;
		String SQL = "Delete from UserRole where roleid = " + userroleid;
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
