package com.gec.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gec.dao.JobDao;
import com.gec.domain.Dept;
import com.gec.domain.Job;
import com.gec.query.JobQueryObject;
import com.gec.util.JDBCUtil;

public class JobDaoImpl implements JobDao {

	@Override
	public List<Job> findJobsByPage(JobQueryObject job) {
		// 准备Sql 方便添加后续条件
		StringBuilder sql = new StringBuilder("select * from job_inf where 1=1 and remark != '已删除'");

		// 组装动态条件
		List<Object> cond = new ArrayList<>();

		// 设置参数
		if (job != null && !"".equals(job.getName()) && job.getName() != null) {
			sql.append(" and name like ? ");
			cond.add("%" + job.getName() + "%");
		}

		// System.out.println("user:" + user);

		// 加上limit
		sql.append(" limit ").append(job.getStartRow()).append(",").append(job.getPageSize());

		// 转数组 list转数组后
		Object[] strs = cond.toArray(new Object[cond.size()]);

		ResultSet rs = null;
		try {
			rs = JDBCUtil.executeQuery(sql.toString(), strs);

			List<Job> jobs = new ArrayList<>();
			while (rs.next()) {
				Job j = new Job();
				j.setId(rs.getInt("id"));
				j.setName(rs.getString("name"));
				j.setRemark(rs.getString("remark"));

				jobs.add(j);
			}
			return jobs;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.closeConn(JDBCUtil.getConn(), JDBCUtil.getPs(), rs);
		}

		return null;
	}

	@Override
	public int getTotalJobsCount(JobQueryObject job) {
		// 准备Sql 方便添加后续条件 count(列名｜*｜数字) from xxx 统计这个表的记录数
		StringBuilder sql = new StringBuilder("select count(*) from job_inf where 1=1 and remark != '已删除'");

		// 组装动态条件
		List<Object> cond = new ArrayList<>();

		// 设置参数
		if (job != null && !"".equals(job.getName()) && job.getName() != null) {
			sql.append(" and name like ? ");
			cond.add("%" + job.getName() + "%");
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
	public void save(Job job) {
		String sql = "insert into job_inf(name,remark)values(?,?)";

		String[] strs = { job.getName(), job.getRemark() };

		try {
			JDBCUtil.executeOrUpdate(sql, strs);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.closeConn(JDBCUtil.getConn(), JDBCUtil.getPs(), null);
		}
	}

	@Override
	public Job findJobById(Integer id) {
		String sql = "select * from job_inf where remark != '已删除' and id=?";
		String[] strs = { id.toString() };
		ResultSet rs = JDBCUtil.executeQuery(sql, strs);
		try {
			while (rs.next()) {
				// 创建部门对象
				Job job = new Job();
				job.setId(rs.getInt("id"));
				job.setName(rs.getString("name"));
				job.setRemark(rs.getString("remark"));

				return job;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.closeConn(JDBCUtil.getConn(), JDBCUtil.getPs(), rs);
		}
		return null;
	}

	@Override
	public void update(Job job) {
		// 准备Sql
		String sql = "update job_inf set name=?,remark=? where remark != '已删除' and id = ?";
		// 准备参数
		String[] strs = { job.getName(), job.getRemark(), job.getId().toString() };
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
		String sql = "update job_inf set remark = '已删除' where id = ?";
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
	public List<Job> findAllJobs() {
		String sql = "select * from job_inf where remark != '已删除'";
		List<Job> jobs = new ArrayList<>();
		ResultSet rs = null;
		try {
			rs = JDBCUtil.executeQuery(sql, null);
			while (rs.next()) {
				// 创建对象
				Job job = new Job();
				job.setId(rs.getInt("id"));
				job.setName(rs.getString("name"));
				job.setRemark(rs.getString("remark"));

				jobs.add(job);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.closeConn(JDBCUtil.getConn(), JDBCUtil.getPs(), rs);
		}
		return jobs;
	}

}
