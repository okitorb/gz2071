package com.gec.dao;

public interface VipDao {
	//登录并查询积分
	void findScoreByPhone(String phone);
	
	String findNumberByPhone(String phone);
	
}
