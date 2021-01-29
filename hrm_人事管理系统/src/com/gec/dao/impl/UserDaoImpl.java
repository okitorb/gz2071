package com.gec.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.gec.dao.UserDao;
import com.gec.domain.User;
import com.gec.query.UserQueryObject;
import com.gec.util.JDBCUtil;

public class UserDaoImpl implements UserDao {

	@Override
	public User login(String loginname, String password) {
		// 准备sql
		String sql = "select * from user_inf where status != 0 and loginname=? and password=?";

		String[] strs = { loginname, password };

		ResultSet rs = null;
		try {
			rs = JDBCUtil.executeQuery(sql, strs);
			while (rs.next()) {
				// 创建用户对象
				User user = new User();
				user.setId(rs.getInt("id"));
				user.setLoginname(rs.getString("loginname"));
				user.setPassword(rs.getString("password"));
				user.setStatus(rs.getInt("status"));
				user.setCreatedate(rs.getDate("createdate"));
				user.setUsername(rs.getString("username"));

				return user;
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.closeConn(JDBCUtil.getConn(), JDBCUtil.getPs(), rs);
		}

		return null;
	}

	@Override
	public List<User> findAllUsers() {
		// 定义用户集合
		List<User> users = new ArrayList<>();
		// 准备Sql
		String sql = "select * from user_inf where status != 0";
		// 查询
		ResultSet rs = null;
		try {
			rs = JDBCUtil.executeQuery(sql, null);
			while (rs.next()) {
				// 创建用户对象
				User user = new User();
				user.setId(rs.getInt("id"));
				user.setLoginname(rs.getString("loginname"));
				user.setPassword(rs.getString("password"));
				user.setStatus(rs.getInt("status"));
				user.setCreatedate(rs.getDate("createdate"));
				user.setUsername(rs.getString("username"));

				// 添加到集合
				users.add(user);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.closeConn(JDBCUtil.getConn(), JDBCUtil.getPs(), rs);
		}
		return users;
	}

	@Override
	public void save(User user) {
		String sql = "insert into user_inf(loginname,password,status,username,createdate)values(?,?,?,?,?)";

		// 需要写一个时间，加入进去
		Date date = new Date();
		Timestamp ts = new Timestamp(date.getTime());

		String[] strs = { user.getLoginname(), user.getPassword(), user.getStatus().toString(), user.getUsername(),
				ts.toString() };

		try {
			JDBCUtil.executeOrUpdate(sql, strs);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.closeConn(JDBCUtil.getConn(), JDBCUtil.getPs(), null);
		}

	}

	@Override
	public List<User> findUsersByPage(UserQueryObject user) {
		// 准备Sql 方便添加后续条件
		StringBuilder sql = new StringBuilder("select * from user_inf where 1=1 and status != 0 ");

		// 组装动态条件
		List<Object> cond = new ArrayList<>();

		// 设置参数
		if (user != null && !"".equals(user.getLoginname()) && user.getLoginname() != null) {
			sql.append(" and loginname like ? ");
			cond.add("%" + user.getLoginname() + "%");
		}

		if (user != null && !"".equals(user.getUsername()) && user.getUsername() != null) {
			sql.append(" and username like ? ");
			cond.add("%" + user.getUsername() + "%");
		}

		if (user != null && user.getStatus() != null) {
			sql.append(" and status = ? ");
			cond.add(user.getStatus());
		}

		//System.out.println("user:" + user);

		// 加上limit
		sql.append(" limit ").append(user.getStartRow()).append(",").append(user.getPageSize());

		// 转数组 list转数组后
		Object[] strs = cond.toArray(new Object[cond.size()]);

		ResultSet rs = null;
		try {
			rs = JDBCUtil.executeQuery(sql.toString(), strs);

			List<User> users = new ArrayList<>();
			while (rs.next()) {
				User u = new User();
				u.setId(rs.getInt("id"));
				u.setLoginname(rs.getString("loginname"));
				u.setPassword(rs.getString("password"));
				u.setStatus(rs.getInt("status"));
				u.setUsername(rs.getString("username"));
				u.setCreatedate(rs.getDate("createdate"));

				users.add(u);
			}
			return users;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.closeConn(JDBCUtil.getConn(), JDBCUtil.getPs(), rs);
		}

		return null;
	}

	@Override
	public int getTotalUsersCount(UserQueryObject user) {
		// 准备Sql 方便添加后续条件 count(列名｜*｜数字) from xxx 统计这个表的记录数
		StringBuilder sql = new StringBuilder("select count(*) from user_inf where 1=1 and status != 0");

		// 组装动态条件
		List<Object> cond = new ArrayList<>();

		// 设置参数
		if (user != null && !"".equals(user.getLoginname()) && user.getLoginname() != null) {
			sql.append(" and loginname like ? ");
			cond.add("%" + user.getLoginname() + "%");
		}

		if (user != null && !"".equals(user.getUsername()) && user.getUsername() != null) {
			sql.append(" and username like ? ");
			cond.add("%" + user.getUsername() + "%");
		}

		if (user != null && user.getStatus() != null) {
			sql.append(" and status = ? ");
			cond.add(user.getStatus());
		}

		// 转数组 list转数组后
		Object[] strs = cond.toArray(new Object[cond.size()]);

		ResultSet rs = null;
		try {
			rs = JDBCUtil.executeQuery(sql.toString(), strs);
			while (rs.next()) {
				int count = rs.getInt(1);
				return count;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.closeConn(JDBCUtil.getConn(), JDBCUtil.getPs(), rs);
		}
		return 0;
	}

	@Override
	public User findUserById(Integer id) {
		String sql = "select * from user_inf where status != 0 and id=?";
		String[] strs = { id.toString() };
		ResultSet rs = JDBCUtil.executeQuery(sql, strs);
		try {
			while (rs.next()) {
				// 创建用户对象
				User user = new User();
				user.setId(rs.getInt("id"));
				user.setLoginname(rs.getString("loginname"));
				user.setPassword(rs.getString("password"));
				user.setStatus(rs.getInt("status"));
				user.setUsername(rs.getString("username"));
				user.setCreatedate(rs.getDate("createdate"));

				return user;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.closeConn(JDBCUtil.getConn(), JDBCUtil.getPs(), rs);
		}

		return null;
	}

	@Override
	public void update(User user) {
		// 准备Sql
		String sql = "update user_inf set loginname=?,password=?,status=?,username=? where status != 0 and id = ?";
		// 准备参数
		String[] strs = { user.getLoginname(), user.getPassword(), user.getStatus().toString(), user.getUsername(),user.getId().toString() };
		// 调用excuteOrupdate
		try {
			JDBCUtil.executeOrUpdate(sql, strs);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.closeConn(JDBCUtil.getConn(), JDBCUtil.getPs(), null);
		}

	}

	@Override
	public void delete(Integer id) {
		
		String sql = "update user_inf set status = 0 where id = ?";
		String[] args = { id.toString() };

		try {
			JDBCUtil.executeOrUpdate(sql, args);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.closeConn(JDBCUtil.getConn(), JDBCUtil.getPs(), null);
		}
		
	}

}
