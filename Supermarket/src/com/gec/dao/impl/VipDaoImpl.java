package com.gec.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import com.gec.dao.VipDao;
import com.gec.util.JDBCUtil;

public class VipDaoImpl implements VipDao {

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
	public String findNumberByPhone(String phone) {
		// 接收手机号
		// 连接数据库查询
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			conn = JDBCUtil.getConnection();
			st = conn.createStatement();
			// 准备sql
			String sql = "select v_number from vip where v_phone = '" + phone + "'";
			//System.out.println("sql:" + sql);
			rs = st.executeQuery(sql);

			while (rs.next()) {
				String number = rs.getString("v_number");
				return number;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.closeConn(conn, st, rs);
		}
		return null;

	}

}
