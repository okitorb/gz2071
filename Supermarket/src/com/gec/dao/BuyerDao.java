package com.gec.dao;

import java.util.List;

import com.gec.domain.Goods;

public interface BuyerDao {
	
	//查询库存，如库存不足，创建进货单(List)，再进行商品补货
	List<Goods> viewAllInventory();
	
	//通过商品编号找库存
	int findInventoryByNumber(int num);
	
	//创建补货单
	void createNeededList(List<Goods> inventory);
	
	//创建进货单
	void createNowList(int num, int count);
	
	//进行商品补货
	void addGoods(List<Goods> inventory);
	
	//查询补货单
	void checkNeededList();
	
	//查询进货单
	void checkNowList();
	
	double findPriceByNumber(int num);
	
	double findVipPriceByNumber(int num);
}
