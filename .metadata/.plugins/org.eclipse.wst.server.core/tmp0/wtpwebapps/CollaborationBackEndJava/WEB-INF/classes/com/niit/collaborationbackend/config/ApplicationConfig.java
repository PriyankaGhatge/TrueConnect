package com.niit.collaborationbackend.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.niit.collaborationbackend.model.Bulletin;
import com.niit.collaborationbackend.model.ChatForum;
import com.niit.collaborationbackend.model.ChatForumComment;
import com.niit.collaborationbackend.model.EventMaster;
import com.niit.collaborationbackend.model.ForumCategory;
import com.niit.collaborationbackend.model.Friends;
import com.niit.collaborationbackend.model.Job;
import com.niit.collaborationbackend.model.JobApplication;
import com.niit.collaborationbackend.model.UserBlog;
import com.niit.collaborationbackend.model.UserForum;
import com.niit.collaborationbackend.model.UserForumComments;
import com.niit.collaborationbackend.model.UserProfile;
import com.niit.collaborationbackend.model.UserRole;
import com.niit.collaborationbackend.model.UserType;
@Configuration
@ComponentScan("com.niit.collaborationbackend")
@EnableTransactionManagement
public class ApplicationConfig {

	@Bean(name="dataSource")
	public DataSource getDataSource()
	{
    	BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName("oracle.jdbc.OracleDriver");
		dataSource.setUrl("jdbc:oracle:thin:@localhost:1521:ORCL");
		dataSource.setUsername("saa1");
		dataSource.setPassword("password");
		return dataSource;
	}
	
	private Properties getHibernateProperties()
	{
		Properties properties=new Properties();
		properties.put("hibernate.show", "true");
		properties.put("hibernate.dialect", "org.hibernate.dialect.OracleDialect");
		properties.put("hibernate.hbm2ddl.auto", "update");
		return properties;
	}

	@Autowired
	@Bean(name="sessionFactory")
	public SessionFactory getSessionFactory(DataSource dataSource)
	{
		LocalSessionFactoryBuilder sessionBuilder=new LocalSessionFactoryBuilder(dataSource);
		sessionBuilder.addProperties(getHibernateProperties());
		sessionBuilder.addAnnotatedClass(UserBlog.class);
		sessionBuilder.addAnnotatedClass(UserProfile.class);
		sessionBuilder.addAnnotatedClass(UserRole.class);
		sessionBuilder.addAnnotatedClass(EventMaster.class);
		sessionBuilder.addAnnotatedClass(ForumCategory.class); 
		sessionBuilder.addAnnotatedClass(UserForum.class);
		sessionBuilder.addAnnotatedClass(UserForumComments.class);
		sessionBuilder.addAnnotatedClass(UserType.class);
		sessionBuilder.addAnnotatedClass(Friends.class);
		sessionBuilder.addAnnotatedClass(Bulletin.class);
		sessionBuilder.addAnnotatedClass(Job.class);
		sessionBuilder.addAnnotatedClass(JobApplication.class);
		sessionBuilder.addAnnotatedClass(ChatForum.class);
		sessionBuilder.addAnnotatedClass(ChatForumComment.class);
		return sessionBuilder.buildSessionFactory();
	}
	
	@Autowired
	@Bean(name="transcationManager")
	public HibernateTransactionManager getTransactionManager(SessionFactory sessionFactory)
	{
		HibernateTransactionManager transactionManager=new HibernateTransactionManager(sessionFactory);
		return transactionManager;
	}
}
