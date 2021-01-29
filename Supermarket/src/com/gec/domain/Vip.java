package com.gec.domain;

public class Vip {
	private String v_number;//vip的编号
	private String v_name;//vip名字
	private int v_score;//vip的积分
	private String v_phone;//vip的手机
	private String v_date;//vip开通的日期
	
	public String getV_number() {
		return v_number;
	}
	public void setV_number(String v_number) {
		this.v_number = v_number;
	}
	public String getV_name() {
		return v_name;
	}
	public void setV_name(String v_name) {
		this.v_name = v_name;
	}
	public int getV_score() {
		return v_score;
	}
	public void setV_score(int v_score) {
		this.v_score = v_score;
	}
	public String getV_phone() {
		return v_phone;
	}
	public void setV_phone(String v_phone) {
		this.v_phone = v_phone;
	}
	public String getV_date() {
		return v_date;
	}
	public void setV_date(String v_date) {
		this.v_date = v_date;
	}
	public Vip(String v_number, String v_name, int v_score, String v_phone, String v_date) {
		super();
		this.v_number = v_number;
		this.v_name = v_name;
		this.v_score = v_score;
		this.v_phone = v_phone;
		this.v_date = v_date;
	}
	public Vip() {
		super();
	}
	@Override
	public String toString() {
		return "Vip [v_number=" + v_number + ", v_name=" + v_name + ", v_score=" + v_score + ", v_phone=" + v_phone
				+ ", v_date=" + v_date + "]";
	}
	
	
}
