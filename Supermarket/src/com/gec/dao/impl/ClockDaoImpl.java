package com.gec.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.gec.dao.ClockDao;
import com.gec.domain.Goods;
import com.gec.util.JDBCUtil;

public class ClockDaoImpl implements ClockDao {

	@Override
	public void clockIn(String number) {
		
		//时间
		Date d = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String clock_in_time = sdf.format(d);
		
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
		String clock_date = sdf2.format(d);
		
		//先查询数据库有没有打卡数据，没有才能打卡，有就不能打卡
		//再加个编号
		//插入编号、clock_in_time、clock_date
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		String sql = "select clock_in_time from clock_info where employee_no='" + number +"' and clock_date='"+ clock_date +"'";
		try {
			conn = JDBCUtil.getConnection();
			st = conn.createStatement();
			// 准备sql
			//System.out.println("sql:" + sql);
			rs = st.executeQuery(sql);
			
			if(!rs.next()) {
				Connection conn2 = null;
				Statement st2 = null;
				try {
					conn2 = JDBCUtil.getConnection();
					st2 = conn2.createStatement();
					// 准备sql
					String sql2 = "insert into clock_info(employee_no,clock_in_time,clock_date)"
							+ "VALUES('" + number +"','"+ clock_in_time +"','" + clock_date +"')";
					//System.out.println("sql:" + sql2);
					st2.executeUpdate(sql2);
					System.out.println("上班打卡成功...");
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					JDBCUtil.closeConn(conn2, st2, null);
				}
			}else {
				System.out.println("你已经打过上班卡了...");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.closeConn(conn, st, rs);
		}
	}

	@Override
	public void clockOut(String number) {
		
		Date d = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String clock_in_time = sdf.format(d);
		SimpleDateFormat sdf3 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String clock_off_time = sdf3.format(d);
		
		
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
		String clock_date = sdf2.format(d);

		//修改clock_off_time
		//clock_date用来判断
		Connection conn3 = null;
		Statement st3 = null;
		ResultSet rs3 = null;
		String sql3 = "select clock_in_time from clock_info where employee_no='" + number +"' and clock_date='"+ clock_date +"'";
		try {
			conn3 = JDBCUtil.getConnection();
			st3 = conn3.createStatement();
			// 准备sql
			//System.out.println("sql:" + sql3);
			rs3 = st3.executeQuery(sql3);
			
			if(!rs3.next()) {
				System.out.println("上班卡还没打呢...");
				Connection conn2 = null;
				Statement st2 = null;
				try {
					conn2 = JDBCUtil.getConnection();
					st2 = conn2.createStatement();
					// 准备sql
					String sql2 = "insert into clock_info(employee_no,clock_in_time,clock_date)"
							+ "VALUES('" + number +"','"+ clock_in_time +"','" + clock_date +"')";
					//System.out.println("sql:" + sql2);
					st2.executeUpdate(sql2);
					System.out.println("上班打卡成功...");
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					JDBCUtil.closeConn(conn2, st2, null);
				}
				
			}else {
				Connection conn4 = null;
				Statement st4 = null;
				try {
					conn4 = JDBCUtil.getConnection();
					st4 = conn4.createStatement();
					// 准备sql
					String sql4 = "update clock_info set clock_off_time ='"+ clock_off_time +"'"
							+ " where employee_no ='"+ number +"' and clock_date='"+ clock_date +"'";
					//System.out.println("sql:" + sql4);
					st4.executeUpdate(sql4);
					System.out.println("下班打卡成功...");
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					JDBCUtil.closeConn(conn4, st4, null);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.closeConn(conn3, st3, rs3);
		}
	
	}
}
