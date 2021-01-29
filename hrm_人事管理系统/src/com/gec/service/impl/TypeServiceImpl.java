package com.gec.service.impl;

import java.util.List;

import com.gec.dao.TypeDao;
import com.gec.dao.impl.TypeDaoImpl;
import com.gec.domain.Type;
import com.gec.query.TypeQueryObject;
import com.gec.service.TypeService;

public class TypeServiceImpl implements TypeService {

	TypeDao dao = new TypeDaoImpl();

	@Override
	public List<Type> findTypesByPage(TypeQueryObject type) {
		return dao.findTypesByPage(type);
	}

	@Override
	public int getTotalTypesCount(TypeQueryObject type) {
		return dao.getTotalTypesCount(type);
	}

	@Override
	public void save(Type type) {
		dao.save(type);
	}

	@Override
	public Type findTypeById(Integer id) {
		return dao.findTypeById(id);
	}

	@Override
	public void update(Type type) {
		dao.update(type);
	}

	@Override
	public void delete(Integer id) {
		dao.delete(id);
	}

	@Override
	public List<Type> findAllTypes() {
		return dao.findAllTypes();
	}

}
