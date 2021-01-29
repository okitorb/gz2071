package com.gec.test;

import java.util.List;

import com.gec.dao.BuyerDao;
import com.gec.dao.impl.BuyerDaoImpl;
import com.gec.domain.Goods;

public class TestBuyer {
	public static void main(String[] args) {
		
		BuyerDao dao = new BuyerDaoImpl();
		
		//dao.viewInventory().forEach(i->System.out.println(i));
		List<Goods> inv = dao.viewAllInventory();
		//System.out.println(inventory);
		
		//商品补货
		//dao.addGoods(inv);
		//创建和查询补货单
		//dao.createNeededList(inv);
		//dao.checkNeededList();
		//创建和查询进货单
		//dao.checkNowList();
		//无需单独执行，在进货成功后产生
		//dao.createNowList(inv, num, count);
		
	}

}
