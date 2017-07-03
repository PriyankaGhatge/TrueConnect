package com.niit.collaborationbackend.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.niit.collaborationbackend")
public class AppConfig extends WebMvcConfigurerAdapter {
	private static final Logger log = LoggerFactory.getLogger(AppConfig.class);
	
	@Bean
	public ViewResolver viewResolver(){
		log.debug("Starting of the Method viewResolver");
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setViewClass(JstlView.class);
		viewResolver.setPrefix("/WEB-INF/views/");
		viewResolver.setSuffix(".html");
		log.debug("Ending of the Method viewResolver");
		return viewResolver;		
	}
	
	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer){
		configurer.enable();
	}

}
