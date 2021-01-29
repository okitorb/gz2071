package com.gec.query;

public class NoticeQueryObject extends PageObject{
	private String name;//公告标题

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public NoticeQueryObject(Integer pageSize, Integer pageIndex, String name) {
		super(pageSize, pageIndex);
		this.name = name;
	}
	public NoticeQueryObject(Integer pageSize, Integer pageIndex) {
		super(pageSize, pageIndex);
	}
	public NoticeQueryObject() {
		
	}
}
