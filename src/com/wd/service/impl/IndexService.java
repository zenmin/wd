package com.wd.service.impl;

import java.util.List;

import com.wd.dao.IIndexDAO;
import com.wd.models.IndexCount;
import com.wd.models.TbBwl;
import com.wd.models.TbGg;
import com.wd.service.IIndexService;

public class IndexService implements IIndexService{

	private IIndexDAO dao;
	
	@Override
	public List checkUser(String username, String password) {
		// TODO Auto-generated method stub
		return dao.checkUser(username, password);
	}

	public IIndexDAO getDao() {
		return dao;
	}

	public void setDao(IIndexDAO dao) {
		this.dao = dao;
	}

	@Override
	public List<TbBwl> getBwl() {
		// TODO Auto-generated method stub
		return dao.getBwl();
	}

	@Override
	public int deleteBwl(Integer id) {
		// TODO Auto-generated method stub
		return dao.deleteBwl(id);
	}

	@Override
	public int addBwl(String content, String time) {
		// TODO Auto-generated method stub
		return dao.addBwl(content, time);
	}

	@Override
	public IndexCount showCount() {
		// TODO Auto-generated method stub
		// 销售额
		String salesCount = dao.salesCount();
		//  商品数量
		String barCount = dao.barCount();
		//  专场数量
		String specialCount=dao.specialCount();
		// 销售站数量
		String stockCount = dao.stockCount();
		
		
		IndexCount count = new IndexCount();
		count.setBar(barCount);
		count.setSales(salesCount);
		count.setSpecial(specialCount);
		count.setStock(stockCount);;
		
		return count;
	}

	@Override
	public List getGg() {
		// TODO Auto-generated method stub
		return dao.getGg();
	}

	@Override
	public int addGg(String username, String content, String date) {
		// TODO Auto-generated method stub
		return dao.addGg(username, content, date);
	}

	@Override
	public int delGg(Integer id,String username) {
		// TODO Auto-generated method stub
		return dao.delGg(id,username);
	}

}
