package com.gec.service;

import java.util.List;

import com.gec.domain.Document;
import com.gec.query.DocQueryObject;

public interface DocService {

	// 分页查询
	List<Document> findDocsByPage(DocQueryObject doc);

	// 文件总记录数
	int getTotalDocsCount(DocQueryObject doc);

	void save(Document doc);

	Document findDocById(Integer id);

	void update(Document doc);

	void delete(Integer id);
	
	List<Document> findAllDocs();
}
