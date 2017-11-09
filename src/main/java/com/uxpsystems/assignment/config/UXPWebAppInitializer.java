package com.uxpsystems.assignment.config;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;


public class UXPWebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	   @Override
	   protected Class<?>[] getRootConfigClasses() {
	      return new Class[] { AppConfig.class, UXPWebAppSecurityConfig.class ,HazelcastCacheConfig.class};
	   }

	   @Override
	   protected Class<?>[] getServletConfigClasses() {
	      return new Class[] { UXPWebConfig.class };
	   }

	   @Override
	   protected String[] getServletMappings() {
	      return new String[] { "/" };
	   }
	    
	   
}