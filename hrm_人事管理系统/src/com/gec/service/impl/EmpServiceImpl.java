package com.gec.service.impl;

import java.util.List;

import com.gec.dao.EmpDao;
import com.gec.dao.impl.EmpDaoImpl;
import com.gec.domain.Employee;
import com.gec.query.EmpQueryObject;
import com.gec.service.EmpService;

public class EmpServiceImpl implements EmpService {

	EmpDao dao = new EmpDaoImpl();
	@Override
	public List<Employee> findEmpsByPage(EmpQueryObject emp) {
		return dao.findEmpsByPage(emp);
	}

	@Override
	public int getTotalEmpsCount(EmpQueryObject emp) {
		return dao.getTotalEmpsCount(emp);
	}

	@Override
	public void save(Employee emp) {
		dao.save(emp);	
	}

	@Override
	public Employee findEmpById(Integer id) {
		return dao.findEmpById(id);
	}

	@Override
	public void update(Employee emp) {
		dao.update(emp);
	}

	@Override
	public void delete(Integer id) {
		dao.delete(id);
	}

}
