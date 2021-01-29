package com.gec.service.impl;

import java.util.List;

import com.gec.dao.UserDao;
import com.gec.dao.impl.UserDaoImpl;
import com.gec.domain.User;
import com.gec.query.UserQueryObject;
import com.gec.service.UserService;

public class UserServiceImpl implements UserService {

	UserDao dao = new UserDaoImpl();
	
	@Override
	public User login(String loginname, String password) {
		User user = dao.login(loginname, password);
		return user;
	}

	@Override
	public List<User> findAllUsers() {
		List<User> users = dao.findAllUsers();
		return users;
	}

	@Override
	public void save(User user) {
		dao.save(user);
		
	}

	@Override
	public List<User> findUsersByPage(UserQueryObject user) {
		return dao.findUsersByPage(user);
	}

	@Override
	public int getTotalUsersCount(UserQueryObject user) {
		return dao.getTotalUsersCount(user);
	}

	@Override
	public User findUserById(Integer id) {
		User user = dao.findUserById(id);
		return user;
	}

	@Override
	public void update(User user) {
		dao.update(user);
	}

	@Override
	public void delete(Integer id) {
		dao.delete(id);
	}

}
