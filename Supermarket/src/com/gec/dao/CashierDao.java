package com.gec.dao;

public interface CashierDao {
	
	//收银结算
	void settleAccounts(String number);
	//查积分
	void findScoreByPhone(String phone);
	//开会员
	void addVip();
}
