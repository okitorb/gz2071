package com.gec.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import com.gec.dao.EmpLoginDao;
import com.gec.domain.Employee;
import com.gec.util.JDBCUtil;

public class EmpLoginDaoImpl implements EmpLoginDao {

	@Override
	public Employee Login(String name, String password) {
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		try{
			conn = JDBCUtil.getConnection();
			st = conn.createStatement();
			//准备sql
			String sql = "select * from employee where username='" + name + "'and password='" + password+"'";
			//System.out.println("sql:" + sql);
			rs = st.executeQuery(sql);
			
			//组装Person对象
			while(rs.next()) {
				Employee emp = new Employee();
				emp.setNumber(rs.getString("number"));
				emp.setUsername(rs.getString("username"));
				emp.setPassword(rs.getString("password"));
				emp.setSex(rs.getString("sex"));
				emp.setPhone(rs.getString("phone"));
				emp.setRole(rs.getInt("role"));
				emp.setRemark(rs.getInt("remark"));
				//返回 这个对象
				return emp;
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.closeConn(conn, st, rs);
		}
		return null;
	}

}
