package com.uxpsystems.assignment.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.uxpsystems.assignment.model.User;
import com.uxpsystems.assignment.service.UserService;

@RestController
public class UserController {
		@Autowired
	   private UserService userService;

	   /*---Add new user---*/
	   @PostMapping("/assignment/user")
	   public ResponseEntity<?> save(@RequestBody User user) {
	      long id = userService.save(user);
	      return ResponseEntity.ok().body("New User has been saved with ID:" + id);
	   }

	   /*---Get a user by id---*/
	   @GetMapping("/assignment/user/{id}")
	   public ResponseEntity<User> get(@PathVariable("id") long id) {
	      User user = userService.get(id);
	      return ResponseEntity.ok().body(user);
	   }

	   /*---get all users---*/
	   @GetMapping("/assignment/users")
	   public ResponseEntity<List<User>> list() {
	      List<User> users = userService.list();
	      return ResponseEntity.ok().body(users);
	   }

	   /*---Update a user by id---*/
	   @PutMapping("/assignment/user/{id}")
	   public ResponseEntity<?> update(@PathVariable("id") long id, @RequestBody User user) {
	      userService.update(id, user);
	      return ResponseEntity.ok().body("User has been updated successfully.");
	   }

	   /*---Delete a user by id---*/
	   @DeleteMapping("/assignment/user/{id}")
	   public ResponseEntity<?> delete(@PathVariable("id") long id) {
	      userService.delete(id);
	      return ResponseEntity.ok().body("User has been deleted successfully.");
	   }

}
