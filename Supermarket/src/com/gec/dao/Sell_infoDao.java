package com.gec.dao;

public interface Sell_infoDao {
	
	//插入表操作
	//会员
	void addSettlement(int c_number,int quantity,String e_number,String v_number);
	//非会员
	void addSettlement(int c_number,int quantity,String e_number);

}
