package com.gec.service.impl;

import java.util.List;

import com.gec.dao.NoticeDao;
import com.gec.dao.impl.NoticeDaoImpl;
import com.gec.domain.Notice;
import com.gec.query.NoticeQueryObject;
import com.gec.service.NoticeService;

public class NoticeServiceImpl implements NoticeService {

	NoticeDao dao = new NoticeDaoImpl();

	@Override
	public List<Notice> findNoticesByPage(NoticeQueryObject notice) {
		return dao.findNoticesByPage(notice);
	}

	@Override
	public int getTotalNoticesCount(NoticeQueryObject notice) {
		return dao.getTotalNoticesCount(notice);
	}

	@Override
	public void save(Notice notice) {
		dao.save(notice);
	}

	@Override
	public Notice findNoticeById(Integer id) {
		return dao.findNoticeById(id);
	}

	@Override
	public void update(Notice notice) {
		dao.update(notice);
	}

	@Override
	public void delete(Integer id) {
		dao.delete(id);
	}

}
