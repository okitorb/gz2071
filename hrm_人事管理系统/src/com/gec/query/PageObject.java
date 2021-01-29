package com.gec.query;

public class PageObject {
	private Integer pageSize  = 2;//显示数量
	private Integer pageIndex = 1;//页面索引
	//有一个计算起始行的方法  当前页码-1）*每页显示数量
    public int getStartRow(){
    	return (this.pageIndex-1)*this.pageSize;
    }
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public Integer getPageIndex() {
		return pageIndex;
	}
	public void setPageIndex(Integer pageIndex) {
		this.pageIndex = pageIndex;
	}
	public PageObject(Integer pageSize, Integer pageIndex) {
		super();
		this.pageSize = pageSize;
		this.pageIndex = pageIndex;
	}
	public PageObject() {
		super();
	}
}
