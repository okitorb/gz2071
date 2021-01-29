package com.gec.service.impl;

import java.util.List;

import com.gec.dao.DocumentDao;
import com.gec.dao.impl.DocumentDaoImpl;
import com.gec.domain.Document;
import com.gec.query.DocQueryObject;
import com.gec.service.DocService;

public class DocServiceImpl implements DocService {

	DocumentDao dao = new DocumentDaoImpl();

	@Override
	public List<Document> findDocsByPage(DocQueryObject doc) {
		return dao.findDocsByPage(doc);
	}

	@Override
	public int getTotalDocsCount(DocQueryObject doc) {
		return dao.getTotalDocsCount(doc);
	}

	@Override
	public void save(Document doc) {
		dao.save(doc);
	}

	@Override
	public Document findDocById(Integer id) {
		return dao.findDocById(id);
	}

	@Override
	public void update(Document doc) {
		dao.update(doc);
	}

	@Override
	public void delete(Integer id) {
		dao.delete(id);
	}

	@Override
	public List<Document> findAllDocs() {

		return dao.findAllDocs();
	}

}
