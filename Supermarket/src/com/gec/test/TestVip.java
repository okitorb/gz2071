package com.gec.test;

import com.gec.dao.VipDao;
import com.gec.dao.impl.VipDaoImpl;

public class TestVip {
	public static void main(String[] args) {
		
		VipDao dao = new VipDaoImpl();
		
		//输入一个手机号 可以查询积分 应该也算是登陆了
		dao.findScoreByPhone("15844760501");
	}
}
