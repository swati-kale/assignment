package assignment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.support.BasicAuthorizationInterceptor;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.uxpsystems.assignment.model.User; 

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserControllerTest {
	
	private static final Logger logger = LoggerFactory.getLogger(UserControllerTest.class);

	@Test
    public  void getUserById()
    {
        final String uri = "http://localhost:8080/assignment/user/{id}";
        RestTemplate restTemplate = new RestTemplate();
        Map<String, String> params = new HashMap<String, String>();
        params.put("id", "1"); 
        restTemplate.getInterceptors().add(new BasicAuthorizationInterceptor("guest", "guest"));
        
        User user = restTemplate.getForObject(uri, User.class,params);
        logger.debug("ID::"+user.getId()+"   UserName ::" +user.getUsername());
    }
    
@Test
    public  void getUsers()
    {
        final String uri = "http://localhost:8080/assignment/users";
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getInterceptors().add(new BasicAuthorizationInterceptor("guest", "guest"));
        ResponseEntity<User[]> responseEntity = restTemplate.getForEntity(uri, User[].class);
        User[] users = responseEntity.getBody();
        MediaType contentType = responseEntity.getHeaders().getContentType();
        HttpStatus statusCode = responseEntity.getStatusCode();
        
        logger.debug("The number of Users Fetched are ::"+users.length);
        logger.debug("content type is = " + contentType);
        logger.debug("status code is = " + statusCode);
    }
	
	//@Test
    public  void createUser()
    {
        final String uri = "http://localhost:8080/assignment/user";
        User user =  new User();
        
        user.setUsername("testuser");
        user.setPassword("test");
        user.setStatus("Deactivated");
     
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getInterceptors().add(new BasicAuthorizationInterceptor("guest", "guest"));
        ObjectMapper mapper = new ObjectMapper();
   

	   //Object to JSON in String
	   try {
		String jsonInString = mapper.writeValueAsString(user);
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
	
		HttpEntity<String> entity = new HttpEntity<String>(jsonInString,headers);
		String result = restTemplate.postForObject(uri, entity, String.class);
		logger.debug(result);
	
			
			// User  result = restTemplate.postForObject( uri, user, User.class);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  
    }
    
  //  @Test
    public  void updateUser()
    {
        final String uri = "http://localhost:8080/assignment/user/{id}";
        Map<String, String> params = new HashMap<String, String>();
        params.put("id", "2");

     
        User user = new User(new Long(2), "skale11", "pwd", "Deactivated");
     
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getInterceptors().add(new BasicAuthorizationInterceptor("guest", "guest"));
        restTemplate.put ( uri, user, params); 
        logger.debug("Successfully updated user = 2");

        
    }
    
    //@Test
    public  void deleteUser()
    {
        final String uri = "http://localhost:8080/assignment/user/{id}";
        Map<String, String> params = new HashMap<String, String>();
        params.put("id", "2");

        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getInterceptors().add(new BasicAuthorizationInterceptor("admin", "admin"));
        restTemplate.delete ( uri, params);
        logger.debug("Successfully deleted user = 2");
    }

}
