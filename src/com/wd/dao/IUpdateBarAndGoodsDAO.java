package com.wd.dao;

import java.util.List;
import java.util.Map;

public interface IUpdateBarAndGoodsDAO {

	//���»��߲���ĳһ����¼
	public String updateOrInsert(List<Map> listAll);
	
	//��ȡĳ��¼��ID
	public Integer findId(String Hql);
	

}
