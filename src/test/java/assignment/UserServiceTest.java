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
import com.uxpsystems.assignment.model.User;
import com.uxpsystems.assignment.service.UserService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@ContextConfiguration(classes= {AppConfig.class ,HazelcastCacheConfig.class},loader=AnnotationConfigContextLoader.class)
public class UserServiceTest {
	private static final Logger logger = LoggerFactory.getLogger(UserServiceTest.class);

	@Autowired
	private UserService userService;

	@Test
	public void testUserService() {
		 assertNotNull(userService);
	}
	 @Test
	 public void testGetUserById(){
		         User existingUser= userService.get(1);
		         if (existingUser != null) {
		             assertThat(userService.get(1), instanceOf(User.class));
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
		 List<User> users= userService.list();
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
	        
	        userService.save(user);
	  	        
	  	         
		     }

}
