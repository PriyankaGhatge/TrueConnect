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

import com.niit.collaborationbackend.model.Bulletin;

@EnableTransactionManagement
@Repository("bulletinDao")
public class BulletinDAO_Impl implements BulletinDAO {

	private static final Logger log = LoggerFactory.getLogger(BulletinDAO_Impl.class);

	@Autowired
	private SessionFactory sessionFactory;

	public BulletinDAO_Impl(SessionFactory sessionFactory){
		this.sessionFactory = sessionFactory;
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public List<Bulletin> getAllBulletin(char showflag) {
		List<Bulletin> allBulletin = null;
		try{
			log.debug("Method => getAllJobs() execution is starting");
			allBulletin = sessionFactory.getCurrentSession().createQuery("FROM Bulletin where flagshow='Y'").list();
			if(allBulletin==null || allBulletin.isEmpty()){
				log.debug("Record not found in Career table");
			}
		}
		catch(HibernateException ex){
			log.debug("Fetch Error :" + ex.getMessage());
			ex.printStackTrace();
		}
		return allBulletin;
	}

	@Transactional
	public Bulletin getBulletinByID(int v_bulid) {
		return (Bulletin) sessionFactory.getCurrentSession().get(Bulletin.class, v_bulid);
	}

	@Transactional
	public boolean saveBulletin(Bulletin bulletin) {
		try
		{
			log.debug("Method => saveBulletin() execution is starting");
			sessionFactory.getCurrentSession().saveOrUpdate(bulletin);
			return true;
		}
		catch(HibernateException ex){
			log.debug("Data Save Error :" + ex.getMessage());
			ex.printStackTrace();
			return false;
		}
	}
}
