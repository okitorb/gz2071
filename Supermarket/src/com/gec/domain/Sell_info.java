package com.gec.domain;

public class Sell_info {
	private int s_c_number;//销售单上商品编号
	private int s_quantity;//商品数量
	private String s_time;//销售时间
	private String s_e_number;//收款时收银员的编号
	private String s_vip_number;//结账时vip的编号 可以没有
	
	public int getS_c_number() {
		return s_c_number;
	}
	public void setS_c_number(int s_c_number) {
		this.s_c_number = s_c_number;
	}
	public int getS_quantity() {
		return s_quantity;
	}
	public void setS_quantity(int s_quantity) {
		this.s_quantity = s_quantity;
	}
	public String getS_time() {
		return s_time;
	}
	public void setS_time(String s_time) {
		this.s_time = s_time;
	}
	public String getS_e_number() {
		return s_e_number;
	}
	public void setS_e_number(String s_e_number) {
		this.s_e_number = s_e_number;
	}
	public String getS_vip_number() {
		return s_vip_number;
	}
	public void setS_vip_number(String s_vip_number) {
		this.s_vip_number = s_vip_number;
	}
	public Sell_info(int s_c_number, int s_quantity, String s_time, String s_e_number, String s_vip_number) {
		super();
		this.s_c_number = s_c_number;
		this.s_quantity = s_quantity;
		this.s_time = s_time;
		this.s_e_number = s_e_number;
		this.s_vip_number = s_vip_number;
	}
	public Sell_info() {
		super();
	}
	/*
	@Override
	public String toString() {
		return "Sell_info [s_c_number=" + s_c_number + ", s_quantity=" + s_quantity + ", s_time=" + s_time
				+ ", s_e_number=" + s_e_number + ", s_vip_number=" + s_vip_number + "]";
	}
	*/
	
	//连接表的操作 注意另一个表也要进行修改
	private String c_name;//商品名称
	private double c_price;//商品原价
	private double vip_price;//vip的商品价格

	public String getC_name() {
		return c_name;
	}
	public void setC_name(String c_name) {
		this.c_name = c_name;
	}
	public double getC_price() {
		return c_price;
	}
	public void setC_price(double c_price) {
		this.c_price = c_price;
	}
	public double getVip_price() {
		return vip_price;
	}
	public void setVip_price(double vip_price) {
		this.vip_price = vip_price;
	}
	@Override
	public String toString() {
		return "Sell_info [s_c_number=" + s_c_number + ", s_quantity=" + s_quantity + ", s_time=" + s_time
				+ ", s_e_number=" + s_e_number + ", s_vip_number=" + s_vip_number + ", c_name=" + c_name + ", c_price="
				+ c_price + ", vip_price=" + vip_price + "]";
	}
	
}
