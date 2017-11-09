package com.uxpsystems.assignment.service;

import java.util.List;

import com.uxpsystems.assignment.model.User;

public interface UserService {

	public long save(User user) ;

	public User get(long id) ;

	public List<User> list() ;

	public void update(long id, User user) ;
	public void delete(long id) ;

}
