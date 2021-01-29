package com.gec.dao;

import java.util.List;

import com.gec.domain.Clock_info;
import com.gec.domain.Employee;
import com.gec.domain.Sell_info;
import com.gec.domain.Vip;

public interface AdminDao {
	//删除并非删除数据而是修改状态 remark=0就行
	//查询时加多个条件
	
	//查看所有人员信息
	List<Employee> fingAllEmployee();
	
	//收银员账户管理：增删改查
	//先查所有的收银员
	List<Employee> findAllCashier();
	
	void addCashier();
	
	void delCashier();
	
	void updateCashier(Employee employee);
	
	//按编号查询收银员的信息
	Employee findCashierByNumber(String number);
	
	//采购员账户管理：增删改查
	//先查所有的采购员
	List<Employee> findAllBuyer();
	
	void addBuyer();
	
	void delBuyer();
	
	void updateBuyer(Employee employee);
	
	//按编号查询采购员的信息
	Employee findBuyerByNumber(String number);
	
	//会员管理
	List<Vip> findAllVip();
	
	void addVip();
	
	void delVip();
	
	void updateVip(Vip vip);
	
	Vip findVipByPhone(String v_phone);
	
	//查询超市营业额 有天营业额、月营业额、季度营业额、年营业额
	
	List<Sell_info> BusinessVolume();
	
	void viewAllBusiness(List<Sell_info> busv);
	
	void viewAllBusinessByDay();
	
	void viewAllBusinessByMonth();
	
	void viewAllBusinessBySeason();
	
	void viewAllBusinessByYear();
		
	//出勤管理
	//查询到工作日的出勤情况
	//再看考勤缺少的情况，也是查询
	//所有的
	List<Clock_info> checkAllOnDuty();
	//迟到
	List<Clock_info> checkLatedIn();
	//早退
	List<Clock_info> checkEarlyOut();
	//旷工
	List<Clock_info> checkAllMissing();
	
	//某天的打卡情况
	List<Clock_info> checkAllOnDutyByDay(String date);
	//某天的迟到
	List<Clock_info> checkLatedInByDay(String date);
	//某天的早退
	List<Clock_info> checkEarlyOutByDay(String date);
	//某天的旷工
	List<Clock_info> checkAllMissingByDay(String date);
	
	//计数
	int countAllOnDutyByDay(String date);
	//总的
	int countLatedIn();
	
	int countEarlyOut();
	
	int countMissing();
	
	
	
	
}
