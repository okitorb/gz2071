package com.gec.query;

public class JobQueryObject extends PageObject{
	private String name;//部门名称

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public JobQueryObject(Integer pageSize, Integer pageIndex, String name) {
		super(pageSize, pageIndex);
		this.name = name;
	}

	public JobQueryObject(Integer pageSize, Integer pageIndex) {
		super(pageSize, pageIndex);
	}

	public JobQueryObject() {
	}
	
	
}
