package com.gec.service;

import java.util.List;

import com.gec.domain.Employee;
import com.gec.query.EmpQueryObject;

public interface EmpService {

	// 分页查询
	List<Employee> findEmpsByPage(EmpQueryObject emp);

	// 员工总记录数
	int getTotalEmpsCount(EmpQueryObject emp);

	void save(Employee emp);

	Employee findEmpById(Integer id);

	void update(Employee emp);

	void delete(Integer id);
}
