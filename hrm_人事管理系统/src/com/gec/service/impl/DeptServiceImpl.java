package com.gec.service.impl;

import java.util.List;

import com.gec.dao.DeptDao;
import com.gec.dao.impl.DeptDaoImpl;
import com.gec.domain.Dept;
import com.gec.query.DeptQueryObject;
import com.gec.service.DeptService;

public class DeptServiceImpl implements DeptService {

	DeptDao dao = new DeptDaoImpl();
	@Override
	public List<Dept> findDeptsByPage(DeptQueryObject dept) {
		return dao.findDeptsByPage(dept);
	}

	@Override
	public int getTotalDeptsCount(DeptQueryObject dept) {
		return dao.getTotalDeptsCount(dept);
	}

	@Override
	public void save(Dept dept) {
		dao.save(dept);	
	}

	@Override
	public Dept findDeptById(Integer id) {
		return dao.findDeptById(id);
	}

	@Override
	public void update(Dept dept) {
		dao.update(dept);
	}

	@Override
	public void delete(Integer id) {
		dao.delete(id);
	}

	@Override
	public List<Dept> findAllDepts() {
		
		return dao.findAllDepts();
	}

}
