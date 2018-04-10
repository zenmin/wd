package com.wd.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.wd.dao.IUpdateBarAndGoodsDAO;
import com.wd.models.TbBar;
import com.wd.models.TbGoods;
import com.wd.service.IUpdateBarAndGoodsService;
import com.wd.utils.ReadBarAndGoods;

public class UpdateBarAndGoodsService implements IUpdateBarAndGoodsService {
	private ReadBarAndGoods readBarAndGoods;
	
	private IUpdateBarAndGoodsDAO updateBarAndGoodsDAO;
	
	//获取Excel中的数据,返回一个List(Map{},...)的数据类型
	public List getExcelValue(File excelFile) throws Exception{
		List all = new ArrayList();
    
		try {
			//获取已经封装到list 的记录
			all = readBarAndGoods.readXls(excelFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
		return all;
	}
	
	//将数据保存或更新到数据库
	public String ExcelSave(File excelFile){
		
//		判断文件是否为空
    	if(excelFile.equals(null) || excelFile == null){
    		return "所选文件为空，请重置或刷新页面重新选择";
    	}
		List<Map> listAll = new ArrayList<Map>();
		try {
			listAll = this.getExcelValue(excelFile);
			//直接调用DAO层，并传送list过去
			updateBarAndGoodsDAO.updateOrInsert(listAll);
		} catch (Exception e) {
			e.printStackTrace();
			return "出错了！在读取Excel时,类型转换错误。可能原因:1.请确认你上传的是新建货号表格！2.价格数量等必须为数字的单元格内必须填写数字！";
		}
		return "更新商品信息成功!";
	}

	public IUpdateBarAndGoodsDAO getUpdateBarAndGoodsDAO() {
		return updateBarAndGoodsDAO;
	}

	public void setUpdateBarAndGoodsDAO(IUpdateBarAndGoodsDAO updateBarAndGoodsDAO) {
		this.updateBarAndGoodsDAO = updateBarAndGoodsDAO;
	}

	public ReadBarAndGoods getReadBarAndGoods() {
		return readBarAndGoods;
	}

	public void setReadBarAndGoods(ReadBarAndGoods readBarAndGoods) {
		this.readBarAndGoods = readBarAndGoods;
	}
	
	
	
}
