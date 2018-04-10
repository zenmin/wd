package com.wd.service;

import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;

import com.wd.models.TbBar;
import com.wd.utils.Page;
import com.wd.utils.Project;

public interface IShowBarService {

	//通过 条件 查询产品信息（页数限制）
	public JSONArray findProject(String Contition, String ContitionValue, String owner, String _class,long arrivePage,String power);
	//通过 条件 查询产品信息（无页数限制）
	public JSONArray findProject(String Contition, String ContitionValue, String owner, String _class);
	//通过 条件 查询产品信息并导出（无页数限制）
	public JSONArray findProjectAndExport(String Contition, String ContitionValue,String owner, String _class);
		
	//获取所有小组
	public List findOwner();
	//获取所有类目
	public List findClass();
	
	//通过BarNo直接查询bar表
	public List findByBarNo(String barNo);
	
	//修改
	public void alter(Project p);
	//获取总共页数信息
	public Page totalPageSize(String Contition,String owner, String name);
	
	//通过BarNo直接查询bar表(修改专用)
	public List ByBarNo(String barNo);
	//通过barNo删除表
	public int deleteBar(String id);
	
	}
