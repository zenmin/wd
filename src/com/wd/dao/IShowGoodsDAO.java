package com.wd.dao;

import java.util.List;

import com.wd.models.TbGoods;
import com.wd.utils.Page;

public interface IShowGoodsDAO {

	//显示所有类目
	public List<TbGoods> findAll(Page page);
	
	//根据id查询类目
	public TbGoods findById(Integer id);	
	//根据名称查询类目
	public List<TbGoods> findByName(String name, Page page);
	
	//新增类目
	public void addGoods(TbGoods tbGoods);
	
	//修改类目
	public int alterGoods(Integer id, String name);
	
	//删除类目
	public int deleteGoods(Integer id);
	//获取总条数
	public long AlltotalSize();

	//获取name 的条数
	public long totalSizeByName(String name);
	
}
