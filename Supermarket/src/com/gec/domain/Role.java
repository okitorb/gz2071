package com.gec.domain;

public class Role {
	private int id;//角色id
	private String r_name;//角色名字
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getR_name() {
		return r_name;
	}
	public void setR_name(String r_name) {
		this.r_name = r_name;
	}
	public Role(int id, String r_name) {
		super();
		this.id = id;
		this.r_name = r_name;
	}
	public Role() {
		super();
	}
	@Override
	public String toString() {
		return "Role [id=" + id + ", r_name=" + r_name + "]";
	}
	
	
}
