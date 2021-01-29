package com.gec.dao;

import java.util.List;

import com.gec.domain.User;
import com.gec.query.UserQueryObject;

public interface UserDao {

	User login(String loginname,String password);
	
	List<User> findAllUsers();

	void save(User user);
	
	//分页查询
	List<User> findUsersByPage(UserQueryObject user);
		
	//用户总记录数
	int getTotalUsersCount(UserQueryObject user);

	User findUserById(Integer id);

	void update(User user);

	void delete(Integer id);
}
