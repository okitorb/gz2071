package com.gec.dao;

import java.util.List;

import com.gec.domain.Job;
import com.gec.query.JobQueryObject;


public interface JobDao {
	
	//分页查询
	List<Job> findJobsByPage(JobQueryObject job);
			
	//部门总记录数
	int getTotalJobsCount(JobQueryObject job);

	void save(Job job);

	Job findJobById(Integer id);

	void update(Job job);

	void delete(Integer id);

	List<Job> findAllJobs();
	
	
	
}
