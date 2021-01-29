package com.gec.dao.impl;

import java.sql.Connection;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.gec.dao.Sell_infoDao;
import com.gec.util.JDBCUtil;

public class Sell_infoDaoImpl implements Sell_infoDao {

	//会员的结账方式
	@Override
	public void addSettlement(int c_number, int quantity, String e_number, String v_number) {
		
		//时间
		Date d = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String datetime = sdf.format(d);

		Connection conn = null;
		Statement st = null;
		try {
			conn = JDBCUtil.getConnection();
			st = conn.createStatement();
			// 准备sql
			String sql = "insert into sell_info(s_c_number,s_quantity,s_time,s_e_number,s_vip_number)"
					+ "values("+ c_number +","+quantity +",'"+datetime +"','"+e_number +"','"+v_number +"')";
			//System.out.println("sql:" + sql);
			st.executeUpdate(sql);
			System.out.println("添加收银结算成功...");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.closeConn(conn, st, null);
		}
		
	}

	//非会员的结账方式
	@Override
	public void addSettlement(int c_number, int quantity, String e_number) {
		Date d = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String datetime = sdf.format(d);

		Connection conn = null;
		Statement st = null;
		try {
			conn = JDBCUtil.getConnection();
			st = conn.createStatement();
			// 准备sql
			String sql = "insert into sell_info(s_c_number,s_quantity,s_time,s_e_number)"
					+ "values("+ c_number +","+quantity +",'"+datetime +"','"+e_number +"')";
			//System.out.println("sql:" + sql);
			st.executeUpdate(sql);
			System.out.println("添加收银结算成功...");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.closeConn(conn, st, null);
		}
			
	}

}
