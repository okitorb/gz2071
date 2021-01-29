package com.gec.util;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import com.alibaba.druid.pool.DruidDataSourceFactory;


public class JDBCUtil {
	private static DataSource ds;
	private static Properties p = new Properties();
	private static Connection conn;
	private static PreparedStatement ps;
	private static ResultSet rs;
	
	static {
		try(InputStream is = JDBCUtil.class.getResourceAsStream("/jdbc.properties")){
			p.load(is);
			
			//加载驱动
			Class.forName(p.getProperty("driverClassName"));
			
			//创建数据源
			ds = DruidDataSourceFactory.createDataSource(p);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//提供连接数据库的方法
	public static Connection getConnection() {
		try {
			conn = ds.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	//把连接放回连接池
	public static void closeConn(Connection conn,PreparedStatement ps,ResultSet rs) {
		try {
			if(rs != null) rs.close();
			if(ps != null) ps.close();
			if(conn != null) conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static PreparedStatement getPs() {
		return ps;
	}

	public static ResultSet getRs() {
		return rs;
	}

	//统一查询 省略很多的代码
	public static ResultSet executeQuery(String sql, Object... strs) {
		try {
			conn = getConnection();
			//创建preparestatement对象
			ps = conn.prepareStatement(sql);
			//给参数设值
			if(strs != null) {
				for(int i = 0;i < strs.length;i++) {
					ps.setObject(i+1, strs[i]);
				}
			}
			
			//执行查询 
			rs = ps.executeQuery();
			return rs;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static Connection getConn() {
		return conn;
	}

	public static void setConn(Connection conn) {
		JDBCUtil.conn = conn;
	}

	public static void setPs(PreparedStatement ps) {
		JDBCUtil.ps = ps;
	}

	public static void setRs(ResultSet rs) {
		JDBCUtil.rs = rs;
	}

	public static void executeOrUpdate(String sql, Object... args) {
		try {
			conn = getConnection();
			//创建preparestatement对象
			ps = conn.prepareStatement(sql);
			//给参数设值
			if(args != null) {
				for(int i = 0;i < args.length;i++) {
					ps.setObject(i+1, args[i]);
				}
			}
			
			//执行 
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
}
