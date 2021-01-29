package com.gec.test;

import java.util.List;
import java.util.Scanner;

import com.gec.dao.AdminDao;
import com.gec.dao.impl.AdminDaoImpl;
import com.gec.domain.Employee;
import com.gec.domain.Sell_info;
import com.gec.domain.Vip;

public class TestEmp {

	public static void main(String[] args) throws Exception {

		AdminDao dao = new AdminDaoImpl();

		//List<Employee> emps = dao.fingAllEmployee();
		//System.out.println(emps);
		//Employee employee = emps.get(2);
		//System.out.println(employee);
		//查询所有人员的信息
		//dao.fingAllEmployee().forEach(e -> System.out.println(e));
		
		//dao.findAllCashier().forEach(c->System.out.println(c));
		//dao.addCashier();
		//dao.delCashier();
		/*
		//修改收银员信息
		//先选择要修改的对象
		System.out.println("请输入你要修改的收银员编号：");
		Scanner sc = new Scanner(System.in);
		String number = sc.next();
		Employee employee = dao.findCashierByNumber(number);
		dao.updateCashier(employee);
		*/
		
		//dao.findAllBuyer().forEach(b->System.out.println(b));
		//dao.addBuyer();
		//dao.delBuyer();
		/*
		//修改采购员信息
		System.out.println("请输入你要修改的采购员编号：");
		Scanner sc = new Scanner(System.in);
		String number = sc.next();
		Employee employee = dao.findBuyerByNumber(number);
		dao.updateCashier(employee);
		*/
		

		//dao.findAllCashier().forEach(c->System.out.println(c));
		
		//会员管理
		//dao.findAllVip().forEach(v->System.out.println(v));
		//dao.addVip();
		//dao.delVip();
		/*
		//修改会员信息
		System.out.println("请输入你要修改的会员手机号：");
		Scanner sc = new Scanner(System.in);
		String phone = sc.next();
		Vip vip = dao.findVipByPhone(phone);
		dao.updateVip(vip);
		*/
		
		//查询超市营业额
		//dao.BusinessVolume().forEach(v->System.out.println(v));
		//List<Sell_info> busv = dao.BusinessVolume();
		
		//dao.viewAllBusiness(busv);
		//dao.viewAllBusinessByDay(busv);
		
		//dao.viewAllBusinessByDay();
		
		//出勤管理 测试
		//dao.checkAllOnDuty().forEach(d->System.out.println(d));
		
		//System.out.println("请输入你要查询的日期(如2020-12-03)：");
		//Scanner sc = new Scanner(System.in);
		//String date = sc.next();
		//dao.checkAllOnDutyByDay(date).forEach(d->System.out.println(d));
		
		//dao.checkLatedIn().forEach(d->System.out.println(d));
		
		//dao.checkEarlyOut().forEach(d->System.out.println(d));
		
		//dao.checkAllMissing().forEach(d->System.out.println(d));
		
		
		//System.out.println("请输入你要查询的日期(如2020-12-03)：");
		//Scanner sc = new Scanner(System.in);
		//String date = sc.next();
		//String date = "2020-12-03";
		//dao.countAllOnDutyByDay(date);
		
		//dao.countLatedIn();
		
		//dao.countEarlyOut();
		
		//dao.countMissing();
		
		//dao.checkLatedInByDay(date).forEach(d->System.out.println(d));
		
		//dao.checkEarlyOutByDay(date).forEach(d->System.out.println(d));
				
		//dao.checkAllMissingByDay(date).forEach(d->System.out.println(d));
	}

	
	
}
