package com.gec.service.impl;

import java.util.List;

import com.gec.dao.JobDao;
import com.gec.dao.impl.JobDaoImpl;
import com.gec.domain.Job;
import com.gec.query.JobQueryObject;
import com.gec.service.JobService;

public class JobServiceImpl implements JobService {

	JobDao dao = new JobDaoImpl();
	@Override
	public List<Job> findJobsByPage(JobQueryObject job) {
		return dao.findJobsByPage(job);
	}

	@Override
	public int getTotalJobsCount(JobQueryObject job) {
		return dao.getTotalJobsCount(job);
	}

	@Override
	public void save(Job job) {
		dao.save(job);	
	}

	@Override
	public Job findJobById(Integer id) {
		return dao.findJobById(id);
	}

	@Override
	public void update(Job job) {
		dao.update(job);
	}

	@Override
	public void delete(Integer id) {
		dao.delete(id);
	}

	@Override
	public List<Job> findAllJobs() {
		return dao.findAllJobs();
	}

}
