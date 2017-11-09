package com.uxpsystems.assignment.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.uxpsystems.assignment.dao.UserDao;
import com.uxpsystems.assignment.model.User;

@Service
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService{

	@Autowired
	private UserDao dao;
	
	@Transactional
	public long save(User user) {
		return dao.save(user);
	}

	public User get(long id) {
		// TODO Auto-generated method stub
		return dao.get(id);
	}

	public List<User> list() {
		// TODO Auto-generated method stub
		return dao.list();
	}

	@Transactional
	public void update(long id, User user) {
		dao.update(id, user);
		
	}

	@Transactional
	public void delete(long id) {
		dao.delete(id);
		
	}

}
