package com.gec.util;

public class PageModel {
	private Integer totalPageSum;//总页码
	private Integer totalRecordSum;//总记录数
	private Integer pageIndex;//当前页码
	private Integer nextPage;//下一页
	private Integer prePage;//上一页
	private Integer pageSize;//每页显示数量
	public Integer getTotalPageSum() {
		return totalPageSum;
	}
	public void setTotalPageSum(Integer totalPageSum) {
		this.totalPageSum = totalPageSum;
	}
	public Integer getTotalRecordSum() {
		return totalRecordSum;
	}
	public void setTotalRecordSum(Integer totalRecordSum) {
		this.totalRecordSum = totalRecordSum;
	}
	public Integer getPageIndex() {
		return pageIndex;
	}
	public void setPageIndex(Integer pageIndex) {
		this.pageIndex = pageIndex;
	}
	public Integer getNextPage() {
		return nextPage;
	}
	public void setNextPage(Integer nextPage) {
		this.nextPage = nextPage;
	}
	public Integer getPrePage() {
		return prePage;
	}
	public void setPrePage(Integer prePage) {
		this.prePage = prePage;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public PageModel() {
		super();
	}
	
	/**
	 构造器(){
		需要计算总页码：
	                           上一页：
	                           下一页：
	}
	 */
	public PageModel(int totalRecordSum, Integer pageIndex, Integer pageSize) {
		this.totalRecordSum = totalRecordSum;
		this.pageSize = pageSize;
		this.pageIndex = pageIndex;
		
		/*
		 *  10%5==0 ? 10/5 ：不满一页算一页
		 */
		this.totalPageSum = totalRecordSum % pageSize == 0 ? totalRecordSum/pageSize : totalRecordSum / pageSize +1;
		
		// 
		this.nextPage = pageIndex + 1 < totalPageSum ? pageIndex + 1: totalPageSum;// 如果当前页码+1小于总页码，就当前页码+1是下一页，否则就是总页码是下一页
		//下一页 
		this.prePage = pageIndex -1 > 1 ? pageIndex -1 : 1;
	}
}
