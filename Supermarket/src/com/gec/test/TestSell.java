package com.gec.test;

import com.gec.dao.CashierDao;
import com.gec.dao.impl.CashierDaoImpl;

public class TestSell {
	public static void main(String[] args) {
		
		CashierDao cdao = new CashierDaoImpl();
		
		//这里需要传入收银员的编号，在登录时获取
		//String number = "S0002";
		//cdao.settleAccounts(number);
		
		//打卡上下班也需要 编号
	}

}
