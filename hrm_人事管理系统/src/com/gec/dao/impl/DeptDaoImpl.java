package com.gec.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.gec.dao.DeptDao;
import com.gec.domain.Dept;
import com.gec.domain.User;
import com.gec.query.DeptQueryObject;
import com.gec.util.JDBCUtil;

public class DeptDaoImpl implements DeptDao {

	@Override
	public List<Dept> findDeptsByPage(DeptQueryObject dept) {
		// 准备Sql 方便添加后续条件
		StringBuilder sql = new StringBuilder("select * from dept_inf where 1=1 and remark != '已删除'");

		// 组装动态条件
		List<Object> cond = new ArrayList<>();

		// 设置参数
		if (dept != null && !"".equals(dept.getName()) && dept.getName() != null) {
			sql.append(" and name like ? ");
			cond.add("%" + dept.getName() + "%");
		}

		// System.out.println("user:" + user);

		// 加上limit
		sql.append(" limit ").append(dept.getStartRow()).append(",").append(dept.getPageSize());

		// 转数组 list转数组后
		Object[] strs = cond.toArray(new Object[cond.size()]);

		ResultSet rs = null;
		try {
			rs = JDBCUtil.executeQuery(sql.toString(), strs);

			List<Dept> depts = new ArrayList<>();
			while (rs.next()) {
				Dept d = new Dept();
				d.setId(rs.getInt("id"));
				d.setName(rs.getString("name"));
				d.setRemark(rs.getString("remark"));

				depts.add(d);
			}
			return depts;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.closeConn(JDBCUtil.getConn(), JDBCUtil.getPs(), rs);
		}

		return null;
	}

	@Override
	public int getTotalDeptsCount(DeptQueryObject dept) {
		// 准备Sql 方便添加后续条件 count(列名｜*｜数字) from xxx 统计这个表的记录数
		StringBuilder sql = new StringBuilder("select count(*) from dept_inf where 1=1 and remark != '已删除'");

		// 组装动态条件
		List<Object> cond = new ArrayList<>();

		// 设置参数
		if (dept != null && !"".equals(dept.getName()) && dept.getName() != null) {
			sql.append(" and name like ? ");
			cond.add("%" + dept.getName() + "%");
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
	public void save(Dept dept) {
		String sql = "insert into dept_inf(name,remark)values(?,?)";

		String[] strs = { dept.getName(), dept.getRemark() };

		try {
			JDBCUtil.executeOrUpdate(sql, strs);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.closeConn(JDBCUtil.getConn(), JDBCUtil.getPs(), null);
		}
	}

	@Override
	public Dept findDeptById(Integer id) {
		String sql = "select * from dept_inf where remark != '已删除' and id=?";
		String[] strs = { id.toString() };
		ResultSet rs = JDBCUtil.executeQuery(sql, strs);
		try {
			while (rs.next()) {
				// 创建部门对象
				Dept dept = new Dept();
				dept.setId(rs.getInt("id"));
				dept.setName(rs.getString("name"));
				dept.setRemark(rs.getString("remark"));

				return dept;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.closeConn(JDBCUtil.getConn(), JDBCUtil.getPs(), rs);
		}
		return null;
	}

	@Override
	public void update(Dept dept) {
		// 准备Sql
		String sql = "update dept_inf set name=?,remark=? where remark != '已删除' and id = ?";
		// 准备参数
		String[] strs = { dept.getName(), dept.getRemark(),dept.getId().toString() };
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
		String sql = "update dept_inf set remark = '已删除' where id = ?";
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
	public List<Dept> findAllDepts() {
		String sql = "select * from dept_inf where remark != '已删除'";
		List<Dept> depts = new ArrayList<>();
		ResultSet rs = null;
		try {
			rs = JDBCUtil.executeQuery(sql, null);
			while (rs.next()) {
				// 创建部门对象
				Dept dept = new Dept();
				dept.setId(rs.getInt("id"));
				dept.setName(rs.getString("name"));
				dept.setRemark(rs.getString("remark"));

				depts.add(dept);		
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.closeConn(JDBCUtil.getConn(), JDBCUtil.getPs(), rs);
		}
		return depts;
	}

}
