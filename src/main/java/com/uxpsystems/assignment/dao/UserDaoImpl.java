package com.uxpsystems.assignment.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.uxpsystems.assignment.model.User;

@Repository
@Transactional
public class UserDaoImpl implements UserDao{
	
	@Autowired
	private SessionFactory sessionFactory;

    public void getCurrentSession() 
    {
        sessionFactory.getCurrentSession();
    }
	
	public long save(User user) {
		sessionFactory.getCurrentSession().persist(user);
		long id = user.getId();
		sessionFactory.getCurrentSession().flush();
		
		//sessionFactory.getCurrentSession().close();
	      return id;
	}

	public User get(long id) {

		return (User) sessionFactory.getCurrentSession().get(User.class, id);
	}

	public List<User> list() {
		
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(User.class);
        return (List<User>) criteria.list();
	}

	public void update(long id, User user) {
		Session session = sessionFactory.getCurrentSession();
	      User user2 = (User) session.byId(User.class).load(id);
	      user2.setUsername(user.getUsername());
	      user2.setStatus(user.getStatus());
	      session.flush();
		
	}

	public void delete(long id) {
		Session session = sessionFactory.getCurrentSession();
	      User user = (User) session.byId(User.class).load(id);
	      session.delete(user);
	}

}
