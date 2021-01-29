package com.gec.domain;

import java.util.Date;

public class Type {
	private Integer id;
	private Integer user_id;
	private Integer state;
	private String name;
	private Date create_date;
	private Date modify_date;
	private String username;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getUser_id() {
		return user_id;
	}
	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getCreate_date() {
		return create_date;
	}
	public void setCreate_date(Date create_date) {
		this.create_date = create_date;
	}
	public Date getModify_date() {
		return modify_date;
	}
	public void setModify_date(Date modify_date) {
		this.modify_date = modify_date;
	}
	
	public Type(Integer id, Integer user_id, Integer state, String name, Date create_date, Date modify_date,
			String username) {
		super();
		this.id = id;
		this.user_id = user_id;
		this.state = state;
		this.name = name;
		this.create_date = create_date;
		this.modify_date = modify_date;
		this.username = username;
	}
	@Override
	public String toString() {
		return "Type [id=" + id + ", user_id=" + user_id + ", state=" + state + ", name=" + name + ", create_date="
				+ create_date + ", modify_date=" + modify_date + ", username=" + username + "]";
	}
	public Type() {
		super();
	}
	

}
