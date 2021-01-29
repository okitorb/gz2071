package com.gec.query;

public class TypeQueryObject extends PageObject{
	private String name;//类型名称

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public TypeQueryObject(Integer pageSize, Integer pageIndex, String name) {
		super(pageSize, pageIndex);
		this.name = name;
	}
	public TypeQueryObject(Integer pageSize, Integer pageIndex) {
		super(pageSize, pageIndex);
	}
	public TypeQueryObject() {
		
	}
}
