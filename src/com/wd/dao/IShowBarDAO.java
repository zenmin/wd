package com.wd.dao;

import java.util.List;
import java.util.Map;

import com.wd.models.TbBar;
import com.wd.models.TbGoods;
import com.wd.utils.Project;

public interface IShowBarDAO {
	
	//查询全部（页数限制）
	public List findAll(long arrivePage);
	//查询全部（无页数限制）
	public List findAll();
	//根据条码(barNo)查询TbBar
	public List findByBarNo(String barNo);
	//根据条码(goodsid)查询TbBar
	public List findBarByGoodsId(Integer goodsId);

	//根据货号(goodsNo)查询goodsId
	public Integer findIdByGoodsNo(String goodsNo);

	//查询所有的小组
	public List findOwner(); 

	//根据小组查询（页数限制）
	public List findByOwner(String owner, long arrivePage);
	//根据小组查询
	public List findByOwner(String owner);
	

	//查询所有的类目
	public List findClass(); 

	//根据类目查询（页数限制）
	public List findByClass(String _class, long arrivePage);
	//根据类目查询
	public List findByClass(String _class);

	//根据简称模糊查询
	public List findBySimpName(String simple, long arrivePage);
	//根据简称模糊查询
	public List findBySimpName(String simple);
	
	//修改
	public void alter(Project p);
	
	
	//获取小组的条数
	public long totalSizeByOwner(String owner);
	
	//根据简称获取总共条数信息
	public long totalSizeBySimple(String name);
	
	//获取所有总共条数数信息
	public long AlltotalSize();

	//通过barNo删除表
	public int deleteBar(String id);
}
