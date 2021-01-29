package com.gec.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.gec.dao.TypeDao;
import com.gec.domain.Type;
import com.gec.query.TypeQueryObject;
import com.gec.util.JDBCUtil;

public class TypeDaoImpl implements TypeDao {

	@Override
	public List<Type> findTypesByPage(TypeQueryObject type) {
		// 准备Sql 方便添加后续条件
		StringBuilder sql = new StringBuilder(
				"select t.*,u.username username from user_inf u,type_inf t where 1=1 and t.user_id=u.id and t.state != 0");

		// 组装动态条件
		List<Object> cond = new ArrayList<>();

		// 设置参数
		if (type != null && !"".equals(type.getName()) && type.getName() != null) {
			sql.append(" and t.name like ? ");
			cond.add("%" + type.getName() + "%");
		}

		// System.out.println("user:" + user);

		// 加上limit
		sql.append(" limit ").append(type.getStartRow()).append(",").append(type.getPageSize());

		// 转数组 list转数组后
		Object[] strs = cond.toArray(new Object[cond.size()]);

		ResultSet rs = null;
		try {
			rs = JDBCUtil.executeQuery(sql.toString(), strs);

			List<Type> types = new ArrayList<>();
			while (rs.next()) {
				Type t = new Type();
				t.setId(rs.getInt("id"));
				t.setName(rs.getString("name"));
				t.setState(rs.getInt("state"));
				t.setUser_id(rs.getInt("user_id"));
				t.setCreate_date(rs.getDate("create_date"));
				t.setModify_date(rs.getDate("modify_date"));
				t.setUsername(rs.getString("username"));

				types.add(t);
			}
			return types;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.closeConn(JDBCUtil.getConn(), JDBCUtil.getPs(), rs);
		}

		return null;
	}

	@Override
	public int getTotalTypesCount(TypeQueryObject type) {
		// 准备Sql 方便添加后续条件 count(列名｜*｜数字) from xxx 统计这个表的记录数
		StringBuilder sql = new StringBuilder(
				"select count(*) from user_inf u,type_inf t where 1=1 and t.user_id=u.id and t.state != 0");

		// 组装动态条件
		List<Object> cond = new ArrayList<>();

		// 设置参数
		if (type != null && !"".equals(type.getName()) && type.getName() != null) {
			sql.append(" and t.name like ? ");
			cond.add("%" + type.getName() + "%");
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
	public void save(Type type) {
		String sql = "insert into type_inf(name,create_date,state,user_id,modify_date)values(?,?,?,?,?)";

		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String modify_date = sdf.format(date);
		String create_date = sdf.format(date);

		Object[] strs = { type.getName(), create_date, type.getState(), type.getUser_id().toString(), modify_date };

		try {
			JDBCUtil.executeOrUpdate(sql, strs);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.closeConn(JDBCUtil.getConn(), JDBCUtil.getPs(), null);
		}
	}

	@Override
	public Type findTypeById(Integer id) {
		String sql = "select t.*,u.username username from user_inf u,type_inf t where 1=1 and t.user_id=u.id and t.state != 0 and t.id=?";
		String[] strs = { id.toString() };
		ResultSet rs = JDBCUtil.executeQuery(sql, strs);
		try {
			while (rs.next()) {
				Type t = new Type();
				t.setId(rs.getInt("id"));
				t.setName(rs.getString("name"));
				t.setState(rs.getInt("state"));
				t.setUser_id(rs.getInt("user_id"));
				t.setCreate_date(rs.getDate("create_date"));
				t.setModify_date(rs.getDate("modify_date"));
				t.setUsername(rs.getString("username"));

				return t;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.closeConn(JDBCUtil.getConn(), JDBCUtil.getPs(), rs);
		}
		return null;
	}

	@Override
	public void update(Type type) {

		// 准备Sql
		String sql = "update type_inf set name=?,state=?,modify_date=? where state != 0 and id = ?";

		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String modify_date = sdf.format(date);

		// 准备参数
		String[] strs = { type.getName(), type.getState().toString(), modify_date, type.getId().toString() };
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
		String sql = "update type_inf set state = 0 where id = ?";
		String[] args = { id.toString() };

		try {
			JDBCUtil.executeOrUpdate(sql, args);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.closeConn(JDBCUtil.getConn(), JDBCUtil.getPs(), null);
		}

	}

	@Override
	public List<Type> findAllTypes() {
		String sql = "select * from type_inf where state != 0";
		List<Type> types = new ArrayList<>();
		ResultSet rs = null;
		try {
			rs = JDBCUtil.executeQuery(sql, null);
			while (rs.next()) {

				Type type = new Type();
				type.setId(rs.getInt("id"));
				type.setName(rs.getString("name"));
				type.setState(rs.getInt("state"));
				type.setUser_id(rs.getInt("user_id"));
				type.setCreate_date(rs.getDate("create_date"));
				type.setModify_date(rs.getDate("modify_date"));

				types.add(type);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.closeConn(JDBCUtil.getConn(), JDBCUtil.getPs(), rs);
		}
		return types;
	}

}
