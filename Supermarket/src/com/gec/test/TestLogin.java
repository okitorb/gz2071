package com.gec.test;

import java.util.List;
import java.util.Scanner;

import com.gec.dao.AdminDao;
import com.gec.dao.BuyerDao;
import com.gec.dao.CashierDao;
import com.gec.dao.ClockDao;
import com.gec.dao.EmpLoginDao;
import com.gec.dao.Sell_infoDao;
import com.gec.dao.VipDao;
import com.gec.dao.impl.AdminDaoImpl;
import com.gec.dao.impl.BuyerDaoImpl;
import com.gec.dao.impl.CashierDaoImpl;
import com.gec.dao.impl.ClockDaoImpl;
import com.gec.dao.impl.EmpLoginDaoImpl;
import com.gec.dao.impl.Sell_infoDaoImpl;
import com.gec.dao.impl.VipDaoImpl;
import com.gec.domain.Clock_info;
import com.gec.domain.Employee;
import com.gec.domain.Goods;
import com.gec.domain.Sell_info;
import com.gec.domain.Vip;

public class TestLogin {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		AdminDao dao = new AdminDaoImpl();
		EmpLoginDao ldao = new EmpLoginDaoImpl();
		VipDao vdao = new VipDaoImpl();
		CashierDao cdao = new CashierDaoImpl();
		ClockDao kdao = new ClockDaoImpl();
		BuyerDao bdao = new BuyerDaoImpl();
		Sell_infoDao sdao = new Sell_infoDaoImpl();
		
		
		while(true) {
			System.out.println("-------欢迎使用超市收银系统-----------");
			System.out.println("1、员工登录\t2、会员登录\t3、退出系统");
			int op = sc.nextInt();
			if(op == 1) {
				System.out.println("--------员工登录---------");
				System.out.println("请输入用户名：");
				String username = sc.next();
				System.out.println("请输入密码：");
				String password = sc.next();
				
				//登录获取到对象
				Employee emp = ldao.Login(username, password);
				
				if(emp == null) {
					System.out.println("用户名或密码错误");
				}else {
					//权限
					if(emp.getRole() == 1) {
						System.out.println("欢迎管理员"+emp.getUsername()+"登录");
						boolean flaga = true;
						while(flaga) {
							System.out.println("-------------------管理员首页----------------------");
							System.out.println("1、收银员账户管理\t2、采购员账户管理\t3、员工出勤管理 ");
							System.out.println("4、查询超市营业额\t5、会员管理\t6、查询所有人的信息 ");
							System.out.println("7、上班打卡\t8、下班打卡\t9、退出登录,返回上一层 ");
							int op2 = sc.nextInt();
							switch(op2) {
							case 1:
								//收银员的管理
								boolean flag = true;
								while(flag) {
									System.out.println("-------收银员的账户管理------");
									System.out.println("1、查询所有收银员\t2、添加收银员\t3、删除收银员");
									System.out.println("4、修改收银员信息\t5、根据编号查找收银员\t6、返回上一层");
									int op3 = sc.nextInt();
									switch(op3) {
									case 1:
										//查询所有收银员
										List<Employee> cashiers = dao.findAllCashier();
										System.out.println("编号\t用户名\t密码\t性别\t手机");
										for (Employee c : cashiers) {
											
											System.out.println(c.getNumber()+"\t"+c.getUsername()+"\t"+c.getPassword()+"\t"+c.getSex()+"\t"+c.getPhone());
										}
										break;
									case 2:
										//添加收银员
										dao.addCashier();
										break;
									case 3:
										//删除收银员
										dao.delCashier();
										break;
									case 4:
										//修改收银员信息
										System.out.println("请输入你要修改的收银员编号");
										String number = sc.next();
										Employee cashier = dao.findCashierByNumber(number);
										dao.updateCashier(cashier);	
										break;
									case 5:
										//根据编号查找收银员
										System.out.println("请输入你要查找的收银员编号");
										String num = sc.next();
										Employee cas = dao.findCashierByNumber(num);
										System.out.println("编号\t用户名\t密码\t性别\t手机");
										if(cas.getNumber() == null) {
											System.out.println("没有找到该收银员..");
										}else {
											System.out.println(cas.getNumber()+"\t"+cas.getUsername()+"\t"+cas.getPassword()+"\t"+cas.getSex()+"\t"+cas.getPhone());
										}
										
										break;
									case 6:
										flag = false;
										break;
									default:
										break;
									}
								}
								break;
							case 2:
								//采购员的管理
								boolean flag2 = true;
								while(flag2) {
									System.out.println("-------采购员的账户管理------");
									System.out.println("1、查询所有采购员\t2、添加采购员\t3、删除采购员");
									System.out.println("4、修改采购员信息\t5、根据编号查找采购员\t6、返回上一层");
									int op4 = sc.nextInt();
									switch(op4) {
									case 1:
										//查询所有采购员
										List<Employee> buyers = dao.findAllBuyer();
										System.out.println("编号\t用户名\t密码\t性别\t手机");
										for (Employee b : buyers) {
											System.out.println(b.getNumber()+"\t"+b.getUsername()+"\t"+b.getPassword()+"\t"+b.getSex()+"\t"+b.getPhone());
										}
										break;
									case 2:
										//添加采购员
										dao.addBuyer();
										
										break;
									case 3:
										//删除采购员
										dao.delBuyer();
										
										break;
									case 4:
										//修改采购员信息
										System.out.println("请输入你要修改的采购员编号");
										String number = sc.next();
										Employee buyer = dao.findBuyerByNumber(number);
										dao.updateCashier(buyer);
										
										break;
									case 5:
										//根据编号查找采购员
										System.out.println("请输入你要查找的采购员编号");
										String num = sc.next();
										Employee buy = dao.findBuyerByNumber(num);
										System.out.println("编号\t用户名\t密码\t性别\t手机");
										System.out.println(buy.getNumber()+"\t"+buy.getUsername()+"\t"+buy.getPassword()+"\t"+buy.getSex()+"\t"+buy.getPhone());
										
										break;
									case 6:
										flag2 = false;
										break;
									default:
										break;
									
									}			
								}
							break;
							case 3:
								boolean flag3 = true;
								while(flag3) {
									//员工出勤管理
									System.out.println("-----------------员工出勤管理-----------------");
									System.out.println("1、查询所有的考勤状况\t2、查询所有迟到的状况\t3、查询所有早退的状况");
									System.out.println("4、查询所有旷工的状况\t5、查询某天的打卡状况\t6、查询某天的迟到状况");
									System.out.println("7、查询某天的早退状况\t8、查询某天的旷工状况\t9、计算某天的打卡条数");
									System.out.println("10、计算迟到总数\t11、计算早退总数\t12、计算旷工总数\t13、返回上一层");
									int op5 = sc.nextInt();
									switch(op5) {
									case 1:
										//查询所有的考勤状况
										List<Clock_info> checkAllOnDuty = dao.checkAllOnDuty();
										System.out.println("员工编号\t上班打卡时间\t下班打卡时间\t打卡日期");
										for (Clock_info clock_info : checkAllOnDuty) {
											
											System.out.println(clock_info.getEmployee_no()+"\t"+clock_info.getClock_in_time()+"\t"
											+clock_info.getClock_off_time()+"\t"+clock_info.getClock_date());
										}
										break;
									case 2:
										//查询所有迟到的状况
										List<Clock_info> checkLatedIn = dao.checkLatedIn();
										System.out.println("(迟到)员工编号\t上班打卡时间\t打卡日期");
										for (Clock_info clock_info : checkLatedIn) {
											
											System.out.println(clock_info.getEmployee_no()+"\t"+clock_info.getClock_in_time()+"\t"
											+clock_info.getClock_date());
										}
										break;
									case 3:
										//查询所有早退的状况
										List<Clock_info> checkEarlyOut = dao.checkEarlyOut();
										System.out.println("(早退)员工编号\t下班打卡时间\t打卡日期");
										for (Clock_info clock_info : checkEarlyOut) {
											
											System.out.println(clock_info.getEmployee_no()+"\t"+clock_info.getClock_off_time()+"\t"
											+clock_info.getClock_date());
										}
										break;
									case 4:
										//查询所有旷工的状况
										List<Clock_info> checkAllMissing = dao.checkAllMissing();
										System.out.println("(旷工)员工编号\t上班打卡时间\t下班打卡时间\t打卡日期");
										for (Clock_info clock_info : checkAllMissing) {
											
											System.out.println(clock_info.getEmployee_no()+"\t"+clock_info.getClock_in_time()+"\t"
											+clock_info.getClock_off_time()+"\t"+clock_info.getClock_date());
										}
										break;
									case 5:
										//查询某天的打卡状况
										System.out.println("请输入你要查询的日期(如2020-12-03)");
										String date = sc.next();
										List<Clock_info> checkAllOnDutyByDay = dao.checkAllOnDutyByDay(date);
										System.out.println("员工编号\t\t上班打卡时间\t\t下班打卡时间\t打卡日期");
										for (Clock_info clock_info : checkAllOnDutyByDay) {
											
											System.out.println(clock_info.getEmployee_no()+"\t"+clock_info.getClock_in_time()+"\t"
											+clock_info.getClock_off_time()+"\t"+clock_info.getClock_date());
										}
										break;
									case 6:
										//查询某天的迟到状况
										System.out.println("请输入你要查询的日期(如2020-12-03)");
										String date2 = sc.next();
										List<Clock_info> checkLatedInByDay = dao.checkLatedInByDay(date2);
										System.out.println("(迟到)员工编号\t\t上班打卡时间\t打卡日期");
										for (Clock_info clock_info : checkLatedInByDay) {
											System.out.println(clock_info.getEmployee_no()+"\t"+clock_info.getClock_in_time()+"\t"
											+clock_info.getClock_date());
										}
										break;
									case 7:
										//查询某天的早退状况
										System.out.println("请输入你要查询的日期(如2020-12-03)");
										String date3 = sc.next();
										List<Clock_info> checkEarlyOutByDay = dao.checkEarlyOutByDay(date3);
										System.out.println("(早退)员工编号\t\t下班打卡时间\t打卡日期");
										for (Clock_info clock_info : checkEarlyOutByDay) {
											
											System.out.println(clock_info.getEmployee_no()+"\t"+clock_info.getClock_off_time()+"\t"
											+clock_info.getClock_date());
										}
										break;
									case 8:
										//查询某天的旷工状况
										System.out.println("请输入你要查询的日期(如2020-12-03)");
										String date4 = sc.next();
										List<Clock_info> checkAllMissingByDay = dao.checkAllMissingByDay(date4);
										System.out.println("(旷工)员工编号\t\t上班打卡时间\t\t下班打卡时间\t打卡日期");
										for (Clock_info clock_info : checkAllMissingByDay) {
											
											System.out.println(clock_info.getEmployee_no()+"\t"+clock_info.getClock_in_time()+"\t"
											+clock_info.getClock_off_time()+"\t"+clock_info.getClock_date());
										}
										break;
									case 9:
										//计算某天的打卡条数
										System.out.println("请输入你要查询的日期(如2020-12-03)");
										String date5 = sc.next();
										int countAllOnDutyByDay = dao.countAllOnDutyByDay(date5);
										System.out.println(countAllOnDutyByDay);
										break;
									case 10:
										//计算迟到总数
										int countLatedIn = dao.countLatedIn();
										System.out.println(countLatedIn);
										break;
									case 11:
										//计算早退总数
										int countEarlyOut = dao.countEarlyOut();
										System.out.println(countEarlyOut);
										break;
									case 12:
										int countMissing = dao.countMissing();
										System.out.println(countMissing);
										//计算旷工总数
										break;
									case 13:
										flag3 = false;
										break;
									default:
										break;
									}
								}
							break;
							case 4:
								boolean flag4 = true;
								while(flag4) {
									//查询超市营业额
									System.out.println("-----------------查询超市营业额-----------------");
									System.out.println("1、查询总营业额\t2、查询日营业额\t3、查询月营业额");
									System.out.println("4、查询季度营业额\t5、查询年营业额\t6、返回上一层");
									
									List<Sell_info> busv = dao.BusinessVolume();
									int op6 = sc.nextInt();
									switch(op6) {
									case 1:
										//查询总营业额
										dao.viewAllBusiness(busv);
										break;
									case 2:
										//查询日营业额
										dao.viewAllBusinessByDay();
										break;
									case 3:
										//查询月营业额
										dao.viewAllBusinessByMonth();
										break;
									case 4:
										//查询季度营业额
										dao.viewAllBusinessBySeason();
										break;
									case 5:
										//查询年营业额
										dao.viewAllBusinessByYear();
										break;
									case 6:
										flag4 = false;
									default:
										break;
									}			
								}	
							break;
							case 5:
								boolean flag5 = true;
								while(flag5) {
									//会员管理
									System.out.println("-----------------会员管理-----------------");
									System.out.println("1、查询所有会员\t2、添加会员\t3、删除会员");
									System.out.println("4、修改会员信息\t5、按手机号查询会员\t6、返回上一层");
									int op7= sc.nextInt();
									switch(op7) {
									case 1:
										//查询所有会员
										List<Vip> findAllVip = dao.findAllVip();
										System.out.println("会员编号\t会员姓名\t会员积分\t会员手机\t开通日期");
										for (Vip vip : findAllVip) {
											
											System.out.println(vip.getV_number()+"\t"+vip.getV_name()+"\t"
											+vip.getV_score()+"\t"+vip.getV_phone()+"\t"+vip.getV_date());
										}
										break;
									case 2:
										//添加会员
										dao.addVip();
										break;
									case 3:
										//删除会员
										dao.delVip();
										break;
									case 4:
										//修改会员信息
										System.out.println("请输入会员手机号");
										String v_phone = sc.next();
										Vip vip = dao.findVipByPhone(v_phone);
										dao.updateVip(vip);
										break;
									case 5:
										//按手机号查询会员
										System.out.println("请输入会员手机号");
										String phone = sc.next();
										Vip vip2 = dao.findVipByPhone(phone);
										System.out.println("会员编号\t会员姓名\t会员积分\t手机\t开通日期");
										System.out.println(vip2.getV_number()+"\t"+vip2.getV_name()+"\t"+vip2.getV_score()+"\t"+vip2.getV_phone()+"\t"+vip2.getV_date());
										break;
									case 6:
										flag5 = false;
										break;
									default:
										break;
									}
								}	
							break;
							case 6:
								//查询所有人的信息
								List<Employee> fingAllEmployee = dao.fingAllEmployee();
								System.out.println("员工编号\t员工姓名\t密码\t性别\t手机");
								for (Employee empl : fingAllEmployee) {
									
									System.out.println(empl.getNumber()+"\t"+empl.getUsername()+"\t"+empl.getPassword()+"\t"+empl.getSex()+"\t"+empl.getPhone());
								}
								break;
							case 7:
								//上班打卡
								//传入管理员对象的编号
								kdao.clockIn(emp.getNumber());
								break;
							case 8:
								//下班打卡
								kdao.clockOut(emp.getNumber());
								break;
							case 9:
								flaga = false;
								break;
							default:
								break;
							}			
						}
	
					}else if(emp.getRole() == 2) {
						System.out.println("欢迎收银员"+emp.getUsername()+"登录");
						boolean flagb = true;
						while(flagb) {
							System.out.println("------------收银员首页--------------");
							System.out.println("1、收银结算\t2、会员积分查询\t3、开通会员\t4、上班打卡\t5、下班打卡\t6、返回上一层");
							int op8 =sc.nextInt();
							switch(op8) {
							case 1:
								//收银结算
								List<Goods> viewAllInventory = bdao.viewAllInventory();
								System.out.println("------商品列表------");
								System.out.println("商品编号\t商品名称\t原价\t会员价");
								for (Goods goods : viewAllInventory) {
									System.out.println(goods.getC_number()+"\t"+goods.getC_name()+"\t"+goods.getC_price()+"\t"+goods.getVip_price());
								}
								
								cdao.settleAccounts(emp.getNumber());
								break;
							case 2:
								//会员积分查询 
								System.out.println("请输入会员手机号：");
								String phone = sc.next();
								cdao.findScoreByPhone(phone);
								break;
							case 3:
								//开通会员
								cdao.addVip();
								break;
							case 4:
								//上班打卡
								kdao.clockIn(emp.getNumber());
								break;
							case 5:
								//下班打卡
								kdao.clockOut(emp.getNumber());
								break;
							case 6:
								flagb = false;
								break;
							default:
								break;
							}
						}

					}else if(emp.getRole() == 3) {
						System.out.println("欢迎采购员"+emp.getUsername()+"登录");
						boolean flag10 = true;
						while(flag10) {
							System.out.println("-----------采购员首页-----------------");
							System.out.println("1、进行商品补货\t2、查询进货单\t3、上班打卡\t4、下班打卡\t5、返回上一层");
							int op9 =sc.nextInt();
							switch(op9) {
							case 1:
								//进行商品补货
								boolean flag9 = true;
								while(flag9) {
									System.out.println("1、查询库存\t2、按商品编号查询库存\t3、查询补货单\t4、进行商品补货\t5、返回上一层");
									int op10 = sc.nextInt();
									switch(op10) {
									case 1:
										//查询库存
										List<Goods> viewAllInventory = bdao.viewAllInventory();
										System.out.println("商品编号\t商品名称\t原价\t会员价\t库存");
										for (Goods goods : viewAllInventory) {
											
											System.out.println(goods.getC_number()+"\t"+goods.getC_name()+"\t"+goods.getC_price()+"\t"+goods.getVip_price()+"\t"+goods.getInventory());
										}
										break;
									case 2:
										//按商品编号查询库存
										System.out.println("请输入商品编号：");
										int num = sc.nextInt();
										int inventory = bdao.findInventoryByNumber(num);
										System.out.println("该商品的库存还有："+inventory);
										break;
									case 3:
										
										//查询补货单
										List<Goods> inventorys = bdao.viewAllInventory();
										bdao.createNeededList(inventorys);
										
										bdao.checkNeededList();
										
										break;
									case 4:
										//进行商品补货
										List<Goods> inventoryss = bdao.viewAllInventory();
										bdao.addGoods(inventoryss);
										break;
									case 5:
										flag9 = false;
										break;
									default:
										break;
									}																	
								}							
							break;
							case 2:
								//查询进货单
								bdao.checkNowList();
								break;
							case 3:
								//上班打卡
								kdao.clockIn(emp.getNumber());
								break;
							case 4:
								//下班打卡
								kdao.clockOut(emp.getNumber());
								break;
							case 5:
								flag10 = false;
								break;
							default:
								break;
							
							}
						}
						
					}
				}
			}else if(op == 2){
				System.out.println("--------会员登录---------");
				
				System.out.println("请输入手机号：");
				String phone = sc.next();
				Vip vip = dao.findVipByPhone(phone);
				if(vip == null) {
					System.out.println("该手机号码还未注册会员");
				}else {
					while(true) {
						System.out.println("1、查询积分\t2、返回上一层");
						int op11 = sc.nextInt();
						if(op11 == 1) {
							vdao.findScoreByPhone(vip.getV_phone());
						}else {
							break;
						}
					}
		
				}
				
			}else if(op == 3) {
				System.exit(0);
			}	
		}
			
	}

}
