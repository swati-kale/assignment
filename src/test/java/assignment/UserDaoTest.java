package assignment;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.transaction.annotation.Transactional;

import com.uxpsystems.assignment.config.AppConfig;
import com.uxpsystems.assignment.config.HazelcastCacheConfig;
import com.uxpsystems.assignment.dao.UserDao;
import com.uxpsystems.assignment.model.User;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@ContextConfiguration(classes= {AppConfig.class ,HazelcastCacheConfig.class},loader=AnnotationConfigContextLoader.class)
public class UserDaoTest {
	private static final Logger logger = LoggerFactory.getLogger(UserDaoTest.class);

    @Autowired
    private UserDao userDao;   
    
    @Test
	public void testUserDao() {
		 assertNotNull(userDao);
	}
	 //@Test
	 public void testGetUserById(){
		         User existingUser= userDao.get(1);
		         if (existingUser != null) {
		             assertThat(userDao.get(1), instanceOf(User.class));
	   	             assertNotNull("Name isn't null",
		                     existingUser.getUsername());
		             assertNotNull("PWD isn't null",
		                     existingUser.getPassword());
		             logger.debug("User Found UserName:"+existingUser.getUsername());
		         }
	  	  
	  	         assertNotNull("Object is not null", existingUser);
	  	         
		     }
	 
	 @Test
	 public void testGetUsers() {
		 List<User> users= userDao.list();
		         if (users != null) {
		        	 logger.debug("No of Users Found:"+users.size());
		         }
		         else {
		        	 logger.debug("Users Not Found.");
		         }
	  	         assertNotNull("Object is not null", users);
	  	        
	  	         
		     }
	 
	 @Test
	 public void testCreateUsers() {
		 	User user =  new User();
	        user.setUsername("testuser1");
	        user.setPassword("test");
	        user.setStatus("Deactivated");
	        
	        long userId = userDao.save(user);
	        logger.debug("Users Created with ID:"+userId); 
	  	         
		     }

}
