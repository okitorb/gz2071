package com.gec.service;

import java.util.List;

import com.gec.domain.Notice;
import com.gec.query.NoticeQueryObject;

public interface NoticeService {

	// 分页查询
	List<Notice> findNoticesByPage(NoticeQueryObject notice);

	// 文件总记录数
	int getTotalNoticesCount(NoticeQueryObject notice);

	void save(Notice notice);

	Notice findNoticeById(Integer id);

	void update(Notice notice);

	void delete(Integer id);
	
}
