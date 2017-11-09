package com.uxpsystems.assignment.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.cache.interceptor.SimpleKeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import static org.hibernate.cfg.Environment.*;

@Configuration
@PropertySource("classpath:hsqldb.properties")
@EnableTransactionManagement
@EnableCaching
@ComponentScans(value = { @ComponentScan("com.uxpsystems.assignment.dao"),
      @ComponentScan("com.uxpsystems.assignment.service") })
public class AppConfig {

	@Autowired
   private Environment env;
   
   //@Autowired
   
  // private Datasource ds;

   @Bean
   public LocalSessionFactoryBean getSessionFactory() {
      LocalSessionFactoryBean factoryBean = new LocalSessionFactoryBean();
      Properties props = new Properties();
      // Setting JDBC properties
      props.put(DRIVER, env.getProperty("driver"));
      props.put(URL, env.getProperty("url"));
      props.put(USER, env.getProperty("user"));
     props.put(PASS, env.getProperty("password"));

      // Setting Hibernate properties
      props.put(DIALECT, env.getProperty("hibernate.dialect"));
      props.put(SHOW_SQL, env.getProperty("hibernate.show_sql"));
      props.put(HBM2DDL_AUTO, env.getProperty("hibernate.hbm2ddl.auto"));
      
      //caching
      
      props.put(USE_SECOND_LEVEL_CACHE , env.getProperty("hibernate.cache.use_second_level_cache"));
      //props.put(USE_QUERY_CACHE , env.getProperty("hibernate.cache.use_query_level_cache"));
   //   props.put(SHOW_SQL, env.getProperty("hibernate.cache.provider_class"));
      props.put(CACHE_REGION_FACTORY, env.getProperty("hibernate.cache.region.factory_class"));
      
      

      // Setting C3P0 properties
      props.put(C3P0_MIN_SIZE, env.getProperty("hibernate.c3p0.min_size"));
      props.put(C3P0_MAX_SIZE, env.getProperty("hibernate.c3p0.max_size"));
      props.put(C3P0_ACQUIRE_INCREMENT, 
            env.getProperty("hibernate.c3p0.acquire_increment"));
      props.put(C3P0_TIMEOUT, env.getProperty("hibernate.c3p0.timeout"));
      props.put(C3P0_MAX_STATEMENTS, env.getProperty("hibernate.c3p0.max_statements"));
      factoryBean.setDataSource(getDataSource());
      factoryBean.setHibernateProperties(props);
      factoryBean.setPackagesToScan("com.uxpsystems.assignment.model");

      return factoryBean;
   }

   @Bean
	public DataSource getDataSource() {
	   EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
	  //  EmbeddedDatabase db = builder.type(HSQL).script("schema.sql").script("test-data.sql").build();e;
	    
	    EmbeddedDatabase db = builder.setType(EmbeddedDatabaseType.HSQL)
	    		.addScript("userschema.sql")
	    		.addScript("userdata.sql")
	    		.build();
	 //db.shutdown();
	    
	    return db;
	}
   
   @Bean
   public HibernateTransactionManager getTransactionManager() {
      HibernateTransactionManager transactionManager = new HibernateTransactionManager();
      transactionManager.setSessionFactory(getSessionFactory().getObject());
      return transactionManager;
   }
   
   
   

}		
