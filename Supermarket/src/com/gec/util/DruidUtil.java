package com.gec.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javax.sql.DataSource;

import com.alibaba.druid.pool.DruidDataSourceFactory;

public class DruidUtil {

	public static Properties p = new Properties();

	// 连接池接口
	private static DataSource ds;

	static {
		try (InputStream is = DruidUtil.class.getResourceAsStream("/druid.properties")) {
			p.load(is);
		} catch (IOException e) {
			e.printStackTrace();
		}

		// 加载驱动
		try {
			Class.forName(p.getProperty("driverClassName"));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		try {
			// 用阿里连接池工厂创建的连接池
			ds = DruidDataSourceFactory.createDataSource(p);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 提供一个连接的方法
	public static Connection getConnection() {
		try {
			// 连接池获得的连接
			return ds.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	// 提供一个关闭数据的方法
	public static void closeConn(Connection conn, Statement st, ResultSet rs) {
		try {
			if (rs != null)
				rs.close();
			if (st != null)
				st.close();
			if (conn != null)
				conn.close();// 不是关闭了，而是把连接放回池子里面了
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
