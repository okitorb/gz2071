package com.gec.test;

import com.gec.dao.ClockDao;
import com.gec.dao.impl.ClockDaoImpl;

public class TestClock {
	public static void main(String[] args) {
		
		ClockDao kdao = new ClockDaoImpl();
		
		//登录时接收对象编号
		String number = "S0012";
		kdao.clockIn(number);
		
		//如何只能一天打一次下班卡？
		//kdao.clockOut(number);
	}

}
