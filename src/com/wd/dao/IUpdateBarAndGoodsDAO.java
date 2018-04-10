package com.wd.dao;

import java.util.List;
import java.util.Map;

public interface IUpdateBarAndGoodsDAO {

	//更新或者插入某一条记录
	public String updateOrInsert(List<Map> listAll);
	
	//获取某记录的ID
	public Integer findId(String Hql);
	

}
