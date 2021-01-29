package com.gec.dao;

import com.gec.domain.Employee;

public interface EmpLoginDao {
	
	Employee Login(String name,String password);

}
