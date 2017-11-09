package com.uxpsystems.assignment.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.uxpsystems.assignment.model.User;

@Repository
public interface UserDao {

	   long save(User user);
	   User get(long id);
	   List<User> list();
	   void update(long id, User user);
	   void delete(long id);
}
