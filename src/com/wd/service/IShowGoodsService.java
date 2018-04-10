package com.wd.service;

import java.util.List;

import com.wd.models.TbGoods;
import com.wd.utils.Page;

public interface IShowGoodsService {
		//显示所有类目
		public List<TbGoods> findAll(Page page);

		//根据id查询类目
		public TbGoods findById(Integer id);
		//根据名称查询类目
		public List<TbGoods> findByName(String name, Page page);
		
		//新增类目
		public void addGoods(TbGoods tbGoods);
		
		//修改类目
		public int alterGoods(Integer id,String name);
		
		//删除类目
		public int deleteGoods(Integer id);
		
		//获取总共页数信息
		public Page totalPageSize(long classCurrentPage, String name);

		
}
