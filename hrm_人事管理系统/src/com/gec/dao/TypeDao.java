package com.gec.dao;

import java.util.List;

import com.gec.domain.Type;
import com.gec.query.TypeQueryObject;

public interface TypeDao {
	
	//分页查询
	List<Type> findTypesByPage(TypeQueryObject type);
			
	//总记录数
	int getTotalTypesCount(TypeQueryObject type);

	void save(Type type);

	Type findTypeById(Integer id);

	void update(Type type);

	void delete(Integer id);
	
	List<Type> findAllTypes();
	
	
	
}
