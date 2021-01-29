package com.gec.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class JDBCUtil {
	public static Properties p = new Properties();
	
	static {
		try(InputStream is = JDBCUtil.class.getResourceAsStream("/jdbc.properties")){
			p.load(is);
		}catch (IOException e) {
			e.printStackTrace();
		}
		
		//加载驱动
		try {
			Class.forName(p.getProperty("driver"));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	//提供一个连接的方法
	public static Connection getConnection() {
		try {
			return DriverManager.getConnection(p.getProperty("url"),p.getProperty("username"), p.getProperty("password"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	//提供一个关闭数据的方法
	public static void closeConn(Connection conn,Statement st,ResultSet rs) {
		try {
			if(rs != null) rs.close();
			if(st != null) st.close();
			if(conn != null) conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
