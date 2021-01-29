package com.gec.dao;

import java.util.List;

import com.gec.domain.Employee;
import com.gec.query.EmpQueryObject;

public interface EmpDao {

	List<Employee> findEmpsByPage(EmpQueryObject emp);

	int getTotalEmpsCount(EmpQueryObject emp);

	void save(Employee emp);

	Employee findEmpById(Integer id);

	void update(Employee emp);

	void delete(Integer id);

}
