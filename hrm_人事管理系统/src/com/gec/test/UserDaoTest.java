package com.gec.test;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.jasper.tagplugins.jstl.core.ForEach;
import org.junit.jupiter.api.Test;

import com.gec.dao.UserDao;
import com.gec.dao.impl.UserDaoImpl;
import com.gec.domain.User;

class UserDaoTest {

	UserDao dao = new UserDaoImpl();
	@Test
	void testLogin() {
		String loginname = "admin";
		String password = "123456";
		
		User user = dao.login(loginname, password);
		System.out.println("user:"+ user);
	}
	
	@Test
	void testAll() {
		//ok
		List<User> users = dao.findAllUsers();
		for (User user : users) {
			System.out.println(user);
			System.out.println(user.getUsername());
		}
	}

	@Test
	void testTime() {
		Date date = new Date();
		Timestamp ts = new Timestamp(date.getTime());
		System.out.println(ts);
		
	}
	@Test
	void testAdd() {

		//ok
		User user = new User();
		user.setLoginname("test");
		user.setPassword("123");
		user.setStatus(1);
		//user.setCreatedate();
		user.setUsername("测试1");
		dao.save(user);
		
	}
}
