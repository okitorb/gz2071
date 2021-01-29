package com.gec.query;

public class UserQueryObject extends PageObject{
	private String username;//用户名
	private String loginname;//登录名
	private Integer status;//状态
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getLoginname() {
		return loginname;
	}
	public void setLoginname(String loginname) {
		this.loginname = loginname;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public UserQueryObject(Integer pageSize, Integer pageIndex, String username, String loginname, Integer status) {
		super(pageSize, pageIndex);
		this.username = username;
		this.loginname = loginname;
		this.status = status;
	}
	public UserQueryObject(Integer pageSize, Integer pageIndex) {
		super(pageSize, pageIndex);
	}
	public UserQueryObject() {
	}
}
