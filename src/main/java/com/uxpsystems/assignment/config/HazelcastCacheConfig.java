package com.uxpsystems.assignment.config;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.cache.interceptor.SimpleKeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.spring.cache.HazelcastCacheManager; 

@Configuration 
@EnableCaching
public class HazelcastCacheConfig extends CachingConfigurerSupport { 
	 
	 @Override 
	 @Bean 
	 public CacheManager cacheManager() { 
	  return new  HazelcastCacheManager(hazelcastInstance()); 
	 } 
	
	 @Bean
     public HazelcastInstance hazelcastInstance() {
         return Hazelcast.newHazelcastInstance();
     }

	
	 @Override 
	 @Bean 
	 public KeyGenerator keyGenerator() { 
	  return new SimpleKeyGenerator(); 
	 } 

	 
	}