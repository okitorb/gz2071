package com.gec.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import com.gec.dao.BuyerDao;
import com.gec.dao.CashierDao;
import com.gec.dao.Sell_infoDao;
import com.gec.dao.VipDao;
import com.gec.domain.Vip;
import com.gec.util.JDBCUtil;

public class CashierDaoImpl implements CashierDao {

	@Override
	public void findScoreByPhone(String phone) {
		// 接收手机号
		// 连接数据库查询
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			conn = JDBCUtil.getConnection();
			st = conn.createStatement();
			// 准备sql
			String sql = "select * from vip where v_phone = '" + phone + "'";
			//System.out.println("sql:" + sql);
			rs = st.executeQuery(sql);

			while (rs.next()) {
				System.out.println("您好！尊敬的会员：" + rs.getString("v_name"));
				System.out.println("您的积分余额为：" + rs.getString("v_score"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.closeConn(conn, st, rs);
		}
	}

	@Override
	public void addVip() {
		// 输入VIP信息
		Scanner sc = new Scanner(System.in);
		// 创建VIP对象
		Vip vip = new Vip();
		
		System.out.println("请输入VIP姓名");
		vip.setV_name(sc.next());
		System.out.println("请输入VIP的手机号码");
		String phone = sc.next();
		vip.setV_phone(phone);
		// 处理日期
		String date = getDate();
		String str = date.replaceAll("-", "");
		System.out.println(str);
		// 拼接字符串
		String vip_num = "vip" + str + phone.substring(phone.length()-4, phone.length());
		System.out.println(vip_num);
		vip.setV_number(vip_num);
		
		// 初始积分
		vip.setV_score(0);
		// 开通会员日期
		vip.setV_date(date);

		Connection conn = null;
		Statement st = null;
		try {
			conn = JDBCUtil.getConnection();
			st = conn.createStatement();
			// 准备sql
			String sql = "insert into vip(v_number,v_name,v_score,v_phone,v_date)" + "values('" + vip.getV_number()
					+ "','" + vip.getV_name() + "'," + vip.getV_score() + ",'" + vip.getV_phone() + "','"
					+ vip.getV_date() + "')";
			//System.out.println("sql:" + sql);
			st.executeUpdate(sql);
			System.out.println("添加会员成功...");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.closeConn(conn, st, null);
		}

	}

	private String getDate() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date d = new Date();
		String strDate = sdf.format(d);
		return strDate;
	}

	@Override
	public void settleAccounts(String e_number) {
		//添加数据到sell_info表
		//收银员登录进来，有个编号，登录是返回一个对象，对象.get方法可以找到编号
		//结账时，先判断是否是会员 ，输入商品编号，查是不是有库存，有就把 商品编号 要的数量 时间 收银员的编号 vip的编号 插入数据库，没有库存就返回
		VipDao vdao = new VipDaoImpl();
		BuyerDao bdao = new BuyerDaoImpl();
		Sell_infoDao sdao = new Sell_infoDaoImpl();
 		Scanner sc = new Scanner(System.in);
		
		System.out.println("请问你是不是会员？ 1、是  2、不是");
		int op = sc.nextInt();
		if(op == 1) {
			System.out.println("请输入手机号码：");
			String phone = sc.next();
			//用phone找vip
			
			//返回一个vip编号
			String v_number = vdao.findNumberByPhone(phone);
			if(v_number == null) {
				System.out.println("是不是记错了，这个手机号还不是会员");
				System.out.println("需要开通会员吗 1需要 2 不需要");
				int op3 =sc.nextInt();
				if(op3 == 1) {
					addVip();
					System.out.println("再次输入手机号：");
					String phone2 = sc.next();
					v_number = vdao.findNumberByPhone(phone2);	
				}
				
			}else {
				System.out.println("您好会员！可以享受本超市的优惠价格");
			}
			
			double count = 0;
			boolean flag = true;
			while(flag) {
				System.out.println("接下来请输入你要购买的商品编号：");
				int c_num = sc.nextInt();
				int inventory = bdao.findInventoryByNumber(c_num);
				double vip_price = bdao.findVipPriceByNumber(c_num);
				
				int quantity = 0;
				
				if(inventory > 0 ) {
					System.out.println("有库存，请输入要购买的数量：");
					quantity = sc.nextInt();
					
					if(inventory >= quantity) {
						//连接数据库，插入操作
						//注意的是收银员的编号
						sdao.addSettlement(c_num, quantity, e_number, v_number);
						
					}else {
						System.out.println("库存不足，需要减少购买数量...");
					}
					
					
				}else {
					System.out.println("没有库存了，需要联系采购员补货");
					bdao.checkNeededList();
				}
				
				count += quantity * vip_price;
				
				System.out.println("继续购买？1继续 2已经买完了");
				int op4 = sc.nextInt();
				if(op4 == 2) {
					System.out.println("会员你好！你的账单为："+count);
					System.out.println("欢迎下次光临...");
					flag =false;	
				}else{
					flag =true;
				}
			}			
			
		}else if(op == 2) {   //不是会员的情况
			String v_number = null;
			System.out.println("需要开通会员吗 1需要 2 不需要");
			int op5 =sc.nextInt();
			if(op5 ==1) {
				addVip();
				System.out.println("再次输入手机号：");
				String phone3 = sc.next();
				v_number = vdao.findNumberByPhone(phone3);
			}
			
			double count = 0;
			boolean flag = true;
			while(flag) {
				System.out.println("接下来请输入你要购买的商品编号：");
				int c_num = sc.nextInt();
				int inventory = bdao.findInventoryByNumber(c_num);
				double c_price = bdao.findPriceByNumber(c_num);
				
				int quantity = 0;
				if(inventory > 0 ) {
					System.out.println("有库存，请输入要购买的数量：");
					quantity = sc.nextInt();
					
					if(inventory >= quantity) {
						//连接数据库，插入操作
						//注意的是收银员的编号
						if(v_number != null) {
							sdao.addSettlement(c_num, quantity, e_number, v_number);
						}else {
							sdao.addSettlement(c_num, quantity, e_number);
						}
					}else {
						System.out.println("库存不足，需要减少购买数量...");
					}	
				}else {
					System.out.println("没有库存了，需要联系采购员补货");
				}
				
				count = quantity * c_price;
				
				System.out.println("继续购买？1继续 2已经买完了");
				int op4 = sc.nextInt();
				if(op4 == 2) {
					System.out.println("你好！你的账单为："+count);
					System.out.println("欢迎下次光临...");
					flag =false;
					
				}else{
					flag =true;
				}
			}
			
		}
		
	}

}
