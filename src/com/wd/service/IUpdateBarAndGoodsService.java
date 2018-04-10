package com.wd.service;

import java.io.File;
import java.util.List;

public interface IUpdateBarAndGoodsService {
	
	//获取Excel中的数据,返回一个List(Map{},...)的数据类型
	public List getExcelValue(File excelFile) throws Exception;
	
	//将数据保存或更新到数据库
	public String ExcelSave(File excelFile);

}
