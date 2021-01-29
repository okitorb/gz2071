package com.gec.domain;

import java.util.Date;

public class Notice {
	private Integer id;
	private Integer user_id;
	private Integer type_id;
	private String name;
	private String content;
	private Date create_date;
	private Date modify_date;
	private String username;
	private String typename;
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
	public Integer getType_id() {
		return type_id;
	}
	public void setType_id(Integer type_id) {
		this.type_id = type_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
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
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getTypename() {
		return typename;
	}
	public void setTypename(String typename) {
		this.typename = typename;
	}
	@Override
	public String toString() {
		return "Notice [id=" + id + ", user_id=" + user_id + ", type_id=" + type_id + ", name=" + name + ", content="
				+ content + ", create_date=" + create_date + ", modify_date=" + modify_date + ", username=" + username
				+ ", typename=" + typename + "]";
	}
	public Notice(Integer id, Integer user_id, Integer type_id, String name, String content, Date create_date,
			Date modify_date, String username, String typename) {
		super();
		this.id = id;
		this.user_id = user_id;
		this.type_id = type_id;
		this.name = name;
		this.content = content;
		this.create_date = create_date;
		this.modify_date = modify_date;
		this.username = username;
		this.typename = typename;
	}
	public Notice() {
		super();
	}
	
}
