package com.gec.dao;

public interface ClockDao {
	
	//打卡
	void clockIn(String number);
	
	void clockOut(String number);

}
