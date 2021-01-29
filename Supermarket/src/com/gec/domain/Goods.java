package com.gec.domain;

import java.util.ArrayList;

public class Goods {
	private int c_number;//商品编号
	private String c_name;//商品名称
	private double c_price;//商品原价
	private double vip_price;//vip的商品价格
	private int inventory;//库存
	//连接表的操作
	private ArrayList<Sell_info> sell;
	
	public ArrayList<Sell_info> getSell() {
		return sell;
	}
	public void setSell(ArrayList<Sell_info> sell) {
		this.sell = sell;
	}
	
	//原表
	public int getC_number() {
		return c_number;
	}
	public void setC_number(int c_number) {
		this.c_number = c_number;
	}
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
	public int getInventory() {
		return inventory;
	}
	public void setInventory(int inventory) {
		this.inventory = inventory;
	}
	public Goods(int c_number, String c_name, double c_price, double vip_price, int inventory) {
		super();
		this.c_number = c_number;
		this.c_name = c_name;
		this.c_price = c_price;
		this.vip_price = vip_price;
		this.inventory = inventory;
	}
	public Goods() {
		super();
	}
	@Override
	public String toString() {
		return "Goods [c_number=" + c_number + ", c_name=" + c_name + ", c_price=" + c_price + ", vip_price="
				+ vip_price + ", inventory=" + inventory + "]";
	}

	
}
