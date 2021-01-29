package com.gec.query;

public class DocQueryObject extends PageObject{
	private String title;//标题

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public DocQueryObject(Integer pageSize, Integer pageIndex, String title) {
		super(pageSize, pageIndex);
		this.title = title;
	}

	public DocQueryObject(Integer pageSize, Integer pageIndex) {
		super(pageSize, pageIndex);
	}
	public DocQueryObject() {
		
	}
}
