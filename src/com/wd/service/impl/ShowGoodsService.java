package com.wd.service.impl;

import java.util.List;

import com.wd.dao.IShowGoodsDAO;
import com.wd.models.TbGoods;
import com.wd.service.IShowGoodsService;
import com.wd.utils.Page;

public class ShowGoodsService implements IShowGoodsService{
	
	private IShowGoodsDAO showGoodsDAO;

	//查找所有
	public List<TbGoods> findAll(Page page) {
		return showGoodsDAO.findAll(page);
	}
	//根据ID查找
	public TbGoods findById(Integer id) {
		return showGoodsDAO.findById(id);
	}
	//根据名称查询类目
	public List<TbGoods> findByName(String name, Page page){
		return showGoodsDAO.findByName(name, page);
	}

	//添加
	public void addGoods(TbGoods tbGoods) {
		showGoodsDAO.addGoods(tbGoods);
	}

	//修改
	public int alterGoods(Integer id ,String name) {
		return showGoodsDAO.alterGoods(id, name);
	}

	//删除
	public int deleteGoods(Integer id) {
		return showGoodsDAO.deleteGoods(id);
	}
	
	//获取总共页数信息
	public Page totalPageSize(long classCurrentPage, String name){
		Page page = new Page();
		long pageSize = 0;
		//判断classCurrenPage是否有值，
		if(classCurrentPage != 0 ){
			//若有值，则表示不是第一次获取页数了，直接把页面传来的值 赋上即可
			page.setCurrentPage(classCurrentPage);
		}else{
			//若无值等于0，则表示第一次查询总页数，所有赋值为1
			page.setCurrentPage(1);
		}
		
		if(name == null){
			page.setTotalSize(showGoodsDAO.AlltotalSize());//获取的总条数
		}else{
			page.setTotalSize(showGoodsDAO.totalSizeByName(name));//根据name来获取的总条数
		}
		
		page.setEverPage(10);//设置每页显示多少条
		if(page.getTotalSize() % page.getEverPage() != 0){
			pageSize = (page.getTotalSize() / page.getEverPage()) +1; 
		}else{
			pageSize = (page.getTotalSize() / page.getEverPage());
		}
		page.setPageSize(pageSize);//获取所有的页数
		if(page.getPageSize() == 0)//判断总页面是否等于0,
			page.setPageSize(1);

		return page;
	}
	
	
	
	

	public IShowGoodsDAO getShowGoodsDAO() {
		return showGoodsDAO;
	}

	public void setShowGoodsDAO(IShowGoodsDAO showGoodsDAO) {
		this.showGoodsDAO = showGoodsDAO;
	}


	
}
