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


public class UserControllerTest {

	@Test
    public  void getUserById()
    {
        final String uri = "http://localhost:8080/assignment/user/{id}";
        RestTemplate restTemplate = new RestTemplate();
        Map<String, String> params = new HashMap<String, String>();
        params.put("id", "1"); 
        restTemplate.getInterceptors().add(new BasicAuthorizationInterceptor("guest", "guest"));
        
        User user = restTemplate.getForObject(uri, User.class,params);
		 System.out.println("ID::"+user.getId()+"   UserName ::" +user.getUsername());
    }
    
@Test
    public  void getUsers()
    {
        final String uri = "http://localhost:8080/assignment/users";
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getInterceptors().add(new BasicAuthorizationInterceptor("guest", "guest"));
        //ResponseEntity<? extends ArrayList<User>> responseEntity = restTemplate.getForEntity(uri, (Class<? extends ArrayList<User>>)ArrayList.class);
       
        //ResponseEntity< ArrayList<User>> responseEntity = restTemplate.getForEntity(uri, ArrayList<User>.class);
        
        //ResponseEntity<? extends ArrayList<User>> responseEntity = restTemplate.getForEntity(uri, (Class<? extends ArrayList<User>>)ArrayList.class);

        //ResponseEntity<ArrayList<User>> res = restTemplate.postForEntity(uri, myDTO, new ParameterizedTypeReference<List<MyObj>>() {});
        ResponseEntity<User[]> responseEntity = restTemplate.getForEntity(uri, User[].class);
        User[] users = responseEntity.getBody();
        MediaType contentType = responseEntity.getHeaders().getContentType();
        HttpStatus statusCode = responseEntity.getStatusCode();
        
       System.out.println("The number of Users Fetched are ::"+users.length);
       System.out.println("content type is = " + contentType);
       System.out.println("status code is = " + statusCode);
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
		System.out.println(result);
	
			
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
    }

    

    


}
