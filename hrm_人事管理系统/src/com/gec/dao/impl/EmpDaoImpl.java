package com.gec.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.gec.dao.EmpDao;
import com.gec.domain.Dept;
import com.gec.domain.Employee;
import com.gec.domain.User;
import com.gec.query.EmpQueryObject;
import com.gec.util.JDBCUtil;

public class EmpDaoImpl implements EmpDao {

	@Override
	public List<Employee> findEmpsByPage(EmpQueryObject emp) {
		// 准备Sql 方便添加后续条件
		StringBuilder sql = new StringBuilder(
				"select e.*,d.name dept_name,j.name job_name from employee_inf e,job_inf j,dept_inf d "
						+ "where 1=1 and j.id = e.job_id and d.id = e.dept_id and e.remark != '已删除' ");

		// 组装动态条件
		List<Object> cond = new ArrayList<>();

		if (emp != null && !"".equals(emp.getName()) && emp.getName() != null) {
			sql.append(" and e.name like ? ");
			cond.add("%" + emp.getName() + "%");
		}

		if (emp != null && emp.getJob_id() != 0) {
			sql.append(" and e.job_id = ? ");
			cond.add(emp.getJob_id());
		}

		if (emp != null && emp.getDept_id() != 0) {
			sql.append(" and e.dept_id = ? ");
			cond.add(emp.getDept_id());
		}

		if (emp != null && emp.getSex() != 0) {
			sql.append(" and e.sex = ? ");
			cond.add(emp.getSex());
		}

		if (emp != null && !"".equals(emp.getCard_id()) && emp.getCard_id() != null) {
			sql.append(" and e.card_id like ? ");
			cond.add("%" + emp.getCard_id() + "%");
		}

		if (emp != null && !"".equals(emp.getPhone()) && emp.getPhone() != null) {
			sql.append(" and e.phone like ? ");
			cond.add("%" + emp.getPhone() + "%");
		}

		sql.append(" limit ").append(emp.getStartRow()).append(",").append(emp.getPageSize());

		// 转数组 list转数组后
		Object[] strs = cond.toArray(new Object[cond.size()]);

		ResultSet rs = null;
		try {
			rs = JDBCUtil.executeQuery(sql.toString(), strs);

			List<Employee> emps = new ArrayList<>();
			while (rs.next()) {
				Employee e = new Employee();
				e.setId(rs.getInt("id"));
				e.setName(rs.getString("name"));
				e.setDept_id(rs.getInt("dept_id"));
				e.setJob_id(rs.getInt("job_id"));
				e.setCard_id(rs.getString("card_id"));
				e.setAddress(rs.getString("address"));
				e.setPost_code(rs.getString("post_code"));
				e.setTel(rs.getString("tel"));
				e.setPhone(rs.getString("phone"));
				e.setQq_num(rs.getString("qq_num"));
				e.setEmail(rs.getString("email"));
				e.setSex(rs.getInt("sex"));
				e.setParty(rs.getString("party"));
				e.setBirthday(rs.getDate("birthday"));
				e.setRace(rs.getString("race"));
				e.setEducation(rs.getString("education"));
				e.setSpeciality(rs.getString("speciality"));
				e.setHobby(rs.getString("hobby"));
				e.setRemark(rs.getString("remark"));
				e.setCreate_date(rs.getDate("create_date"));
				e.setDept_name(rs.getString("dept_name"));
				e.setJob_name(rs.getString("job_name"));

				emps.add(e);
			}
			return emps;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.closeConn(JDBCUtil.getConn(), JDBCUtil.getPs(), rs);
		}

		return null;
	}

	@Override
	public int getTotalEmpsCount(EmpQueryObject emp) {
		// 准备Sql 方便添加后续条件 count(列名｜*｜数字) from xxx 统计这个表的记录数
		StringBuilder sql = new StringBuilder("select count(*) from employee_inf e,job_inf j,dept_inf d "
				+ "where 1=1 and j.id = e.job_id and d.id = e.dept_id and e.remark != '已删除' ");

		// 组装动态条件
		List<Object> cond = new ArrayList<>();

		// 设置参数
		if (emp != null && !"".equals(emp.getName()) && emp.getName() != null) {
			sql.append(" and e.name like ? ");
			cond.add("%" + emp.getName() + "%");
		}

		if (emp != null && emp.getJob_id() != 0) {
			sql.append(" and e.job_id = ? ");
			cond.add(emp.getJob_id());
		}

		if (emp != null && emp.getDept_id() != 0) {
			sql.append(" and e.dept_id = ? ");
			cond.add(emp.getDept_id());
		}

		if (emp != null && emp.getSex() != 0) {
			sql.append(" and e.sex = ? ");
			cond.add(emp.getSex());
		}

		if (emp != null && !"".equals(emp.getCard_id()) && emp.getCard_id() != null) {
			sql.append(" and e.card_id like ? ");
			cond.add("%" + emp.getCard_id() + "%");
		}

		if (emp != null && !"".equals(emp.getPhone()) && emp.getPhone() != null) {
			sql.append(" and e.phone like ? ");
			cond.add("%" + emp.getPhone() + "%");
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
	public void save(Employee emp) {
		
		String sql = "insert into employee_inf (name,remark,dept_id,job_id,card_id,address,post_code,tel,phone,"
				+ "qq_num,email,sex,party,birthday,race,education,speciality,hobby)values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		
		Date date = emp.getBirthday();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String birthday = sdf.format(date);
		
		// 准备参数
		String[] strs = { emp.getName(), emp.getRemark(), emp.getDept_id().toString(),emp.getJob_id().toString(),emp.getCard_id(),emp.getAddress(),emp.getPost_code(),emp.getTel(),emp.getPhone(),
				emp.getQq_num(),emp.getEmail(),emp.getSex().toString(),emp.getParty(),birthday,emp.getRace(),emp.getEducation(),emp.getSpeciality(),emp.getHobby()};
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
	public Employee findEmpById(Integer id) {
		String sql = "select e.*,d.name dept_name,j.name job_name from employee_inf e,job_inf j,dept_inf d "
				+ "where e.remark != '已删除' and e.id=?";
		String[] strs = { id.toString() };
		ResultSet rs = JDBCUtil.executeQuery(sql, strs);
		try {
			while (rs.next()) {
				Employee e = new Employee();
				e.setId(rs.getInt("id"));
				e.setName(rs.getString("name"));
				e.setDept_id(rs.getInt("dept_id"));
				e.setJob_id(rs.getInt("job_id"));
				e.setCard_id(rs.getString("card_id"));
				e.setAddress(rs.getString("address"));
				e.setPost_code(rs.getString("post_code"));
				e.setTel(rs.getString("tel"));
				e.setPhone(rs.getString("phone"));
				e.setQq_num(rs.getString("qq_num"));
				e.setEmail(rs.getString("email"));
				e.setSex(rs.getInt("sex"));
				e.setParty(rs.getString("party"));
				e.setBirthday(rs.getDate("birthday"));
				e.setRace(rs.getString("race"));
				e.setEducation(rs.getString("education"));
				e.setSpeciality(rs.getString("speciality"));
				e.setHobby(rs.getString("hobby"));
				e.setRemark(rs.getString("remark"));
				e.setCreate_date(rs.getDate("create_date"));
				e.setDept_name(rs.getString("dept_name"));
				e.setJob_name(rs.getString("job_name"));

				return e;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.closeConn(JDBCUtil.getConn(), JDBCUtil.getPs(), rs);
		}
		return null;
	}

	@Override
	public void update(Employee emp) {
		// 准备Sql
		String sql = "update employee_inf set name=?,remark=?,dept_id=?,job_id=?,card_id=?,address=?,post_code=?,tel=?,phone=?,"
				+ "qq_num=?,email=?,sex=?,party=?,birthday=?,race=?,education=?,speciality=?,hobby=? "
				+ "where remark != '已删除' and id = ?";
		
		Date date = emp.getBirthday();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String birthday = sdf.format(date);
		
		// 准备参数
		String[] strs = { emp.getName(), emp.getRemark(), emp.getDept_id().toString(),emp.getJob_id().toString(),emp.getCard_id(),emp.getAddress(),emp.getPost_code(),emp.getTel(),emp.getPhone(),
				emp.getQq_num(),emp.getEmail(),emp.getSex().toString(),emp.getParty(),birthday,emp.getRace(),emp.getEducation(),emp.getSpeciality(),emp.getHobby(),emp.getId().toString() };
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
		String sql = "update employee_inf set remark = '已删除' where id = ?";
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
