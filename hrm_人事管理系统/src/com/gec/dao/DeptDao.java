package com.gec.dao;

import java.util.List;

import com.gec.domain.Dept;
import com.gec.query.DeptQueryObject;


public interface DeptDao {
	
	//分页查询
	List<Dept> findDeptsByPage(DeptQueryObject dept);
			
	//部门总记录数
	int getTotalDeptsCount(DeptQueryObject dept);

	void save(Dept dept);

	Dept findDeptById(Integer id);

	void update(Dept dept);

	void delete(Integer id);

	List<Dept> findAllDepts();
	
	
	
}
