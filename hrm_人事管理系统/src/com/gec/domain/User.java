package com.gec.domain;

import java.util.Date;

public class User {
	private Integer id;
	private String loginname;
	private String password;
	private Integer status;
	private Date createdate;
	private String username;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getLoginname() {
		return loginname;
	}
	public void setLoginname(String loginname) {
		this.loginname = loginname;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Date getCreatedate() {
		return createdate;
	}
	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", loginname=" + loginname + ", password=" + password + ", status=" + status
				+ ", createdate=" + createdate + ", username=" + username + "]";
	}
	public User(Integer id, String loginname, String password, Integer status, Date createdate, String username) {
		super();
		this.id = id;
		this.loginname = loginname;
		this.password = password;
		this.status = status;
		this.createdate = createdate;
		this.username = username;
	}
	public User() {
		super();
	}
	
	
}
