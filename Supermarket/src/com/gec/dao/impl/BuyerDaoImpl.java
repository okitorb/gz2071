package com.gec.dao.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import com.gec.dao.BuyerDao;
import com.gec.domain.Goods;
import com.gec.util.JDBCUtil;

public class BuyerDaoImpl implements BuyerDao {

	@Override
	public List<Goods> viewAllInventory() {
		//查询库存
		List<Goods> goods = new ArrayList<>();
		// 连接数据库查询
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		String sql = "select * from goods";
		try {
			conn = JDBCUtil.getConnection();
			st = conn.createStatement();
			// 准备sql
			//System.out.println("sql:" + sql);
			rs = st.executeQuery(sql);

			while (rs.next()) {
				Goods g = new Goods();
				g.setC_number(rs.getInt("c_number"));
				g.setC_name(rs.getString("c_name"));
				g.setC_price(rs.getDouble("c_price"));
				g.setVip_price(rs.getDouble("vip_price"));
				g.setInventory(rs.getInt("inventory"));

				goods.add(g);
			}
			return goods;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.closeConn(conn, st, rs);
		}
		return null;
	}

	
	@Override
	public void createNeededList(List<Goods> inventory) {
		//io流输出到一个文件
		File file =new File("F:\\A粤嵌JAVA培训\\A正课-第一阶段\\项目\\补货单.txt");
		try (OutputStream os = new FileOutputStream(file);
				PrintStream ps = new PrintStream(os)
			){
			ps.write("你需要进货的商品和数量：".getBytes());
			ps.println();
			ps.write("商品编号  商品名称  需要补货的数量 ".getBytes());
			ps.println();
			for (int i = 0; i < inventory.size(); i++) {
				Goods good = inventory.get(i);
				//System.out.println(good);
				if(good.getInventory() <= 100) {
					//System.out.println(good);
					
					int num = good.getC_number();
					String name = good.getC_name();
					int inv = good.getInventory();
					ps.printf("  %d 	%s 	%d ",num,name,(100-inv));
					ps.println();
				}
			}
			ps.close();
			os.close();		
		} catch (IOException e) {
			e.printStackTrace();
		}
	
	}

	@Override
	public void addGoods(List<Goods> inventory) {
		
		System.out.println("需要补货啦-----");
		System.out.println("以下商品的库存不足100件...");
		for (int i = 0; i < inventory.size(); i++) {
			Goods good = inventory.get(i);
			//System.out.println(good);
			if(good.getInventory() < 100) {
				//System.out.println(good);	
				int number = good.getC_number();
				String name = good.getC_name();
				int inv = good.getInventory();
				//int need = 100 -inv;//需要进货的数量
				
				System.out.println(number + " "+ name + " "+ inv);
			}
		}
		
		
		Scanner sc = new Scanner(System.in);
		System.out.println("进货环节...");
		System.out.println("输入商品编号");
		int num = sc.nextInt();
		//查库存
		int inv = findInventoryByNumber(num);
		
		//sql
		//更新数据：输入补货的商品编号和数量 update到数据库中
		String sql = "update goods ";

		System.out.println("输入进货数量");
		int count = sc.nextInt();
			
		sql += " set inventory = " + (count+inv);
		
		// 加上条件
		sql += " where c_number = " + num;

		Connection conn = null;
		Statement st = null;
		try {
			conn = JDBCUtil.getConnection();
			st = conn.createStatement();
			// 准备sql

			//System.out.println("sql:" + sql);
			st.executeUpdate(sql);
			System.out.println("进货成功...");
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.closeConn(conn, st, null);	
		}	
		createNowList(num, count);
	}

	public void createNowList(int num, int count) {
		
		//生成进货单
		//我进了多少货
		File file =new File("F:\\A粤嵌JAVA培训\\A正课-第一阶段\\项目\\进货单.txt");
		try (OutputStream os = new FileOutputStream(file,true);
				PrintStream ps = new PrintStream(os)
			){
			//思考如何只输出一次
			ps.write("你已经进货的商品和数量：".getBytes());
			ps.println();
			ps.write("商品编号  商品名称  进货数  进货日期".getBytes());
			ps.println();

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date d = new Date();
			String strDate = sdf.format(d);
			
			String name = findNameByNumber(num);
			
			ps.printf("  %d 	%s 	%d 	%s",num,name,count,strDate);
			ps.println();
				
			ps.close();
			os.close();		
		} catch (IOException e) {
			e.printStackTrace();
		}
	
	}


	private String findNameByNumber(int num) {
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		String sql = "select c_name from goods where c_number=" + num;
		try {
			conn = JDBCUtil.getConnection();
			st = conn.createStatement();
			// 准备sql
			//System.out.println("sql:" + sql);
			rs = st.executeQuery(sql);

			while (rs.next()) {
				Goods good = new Goods();
				good.setC_name(rs.getString("c_name"));
				String name = good.getC_name();
				return name;
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.closeConn(conn, st, rs);
		}
		return null;
	}


	public int findInventoryByNumber(int num) {
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		String sql = "select inventory from goods where c_number=" + num;
		try {
			conn = JDBCUtil.getConnection();
			st = conn.createStatement();
			// 准备sql
			//System.out.println("sql:" + sql);
			rs = st.executeQuery(sql);

			while (rs.next()) {
				Goods good = new Goods();
				good.setInventory(rs.getInt("inventory"));
				
				int inv = good.getInventory();
				return inv;
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.closeConn(conn, st, rs);
		}
		return -1;
	}


	@Override
	public void checkNeededList() {
		File file = new File("F:\\A粤嵌JAVA培训\\A正课-第一阶段\\项目\\补货单.txt");
		if(!file.exists()) {
			System.out.println("找不到数据...");
			System.out.println("还没生成补货单呢...");
			return;
		}
		try(InputStream is = new FileInputStream(file)){
			int len = 0;//已读的字节数
			byte[] b = new byte[1024];//utf-8 一个中文3个字节
			//循环
			while((len = is.read(b)) > 0) {
				//把已读的字节数组转成String
				String str = new String(b,0,b.length);
				System.out.println(str);
				System.out.println("-----");
			}
		}catch (Exception e) {
			e.printStackTrace();
		}

	}


	@Override
	public void checkNowList() {
		File file = new File("F:\\A粤嵌JAVA培训\\A正课-第一阶段\\项目\\进货单.txt");
		if(!file.exists()) {
			System.out.println("找不到数据...");
			System.out.println("还没生成进货单呢...");
			return;
		}
		try(InputStream is = new FileInputStream(file)){
			int len = 0;//已读的字节数
			byte[] b = new byte[1024];//utf-8 一个中文3个字节
			//循环
			while((len = is.read(b)) > 0) {
				//把已读的字节数组转成String
				String str = new String(b,0,b.length);
				System.out.println(str);
				System.out.println("-----");
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}


	
	
	
	@Override
	public double findPriceByNumber(int num) {
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		String sql = "select c_price from goods where c_number=" + num;
		try {
			conn = JDBCUtil.getConnection();
			st = conn.createStatement();
			// 准备sql
			//System.out.println("sql:" + sql);
			rs = st.executeQuery(sql);

			while (rs.next()) {
				Goods good = new Goods();
				good.setC_price(rs.getDouble("c_price"));
				
				double c_price = good.getC_price();
				return c_price;
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.closeConn(conn, st, rs);
		}
		return -1;
	}


	@Override
	public double findVipPriceByNumber(int num) {
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		String sql = "select vip_price from goods where c_number=" + num;
		try {
			conn = JDBCUtil.getConnection();
			st = conn.createStatement();
			// 准备sql
			//System.out.println("sql:" + sql);
			rs = st.executeQuery(sql);

			while (rs.next()) {
				Goods good = new Goods();
				good.setVip_price(rs.getDouble("vip_price"));
				
				double vip_price = good.getVip_price();
				return vip_price;
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.closeConn(conn, st, rs);
		}
		return -1;
	}


}
