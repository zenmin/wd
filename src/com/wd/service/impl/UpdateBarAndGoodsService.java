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
	
	//��ȡExcel�е�����,����һ��List(Map{},...)����������
	public List getExcelValue(File excelFile) throws Exception{
		List all = new ArrayList();
    
		try {
			//��ȡ�Ѿ���װ��list �ļ�¼
			all = readBarAndGoods.readXls(excelFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
		return all;
	}
	
	//�����ݱ������µ����ݿ�
	public String ExcelSave(File excelFile){
		
//		�ж��ļ��Ƿ�Ϊ��
    	if(excelFile.equals(null) || excelFile == null){
    		return "��ѡ�ļ�Ϊ�գ������û�ˢ��ҳ������ѡ��";
    	}
		List<Map> listAll = new ArrayList<Map>();
		try {
			listAll = this.getExcelValue(excelFile);
			//ֱ�ӵ���DAO�㣬������list��ȥ
			updateBarAndGoodsDAO.updateOrInsert(listAll);
		} catch (Exception e) {
			e.printStackTrace();
			return "�����ˣ��ڶ�ȡExcelʱ,����ת�����󡣿���ԭ��:1.��ȷ�����ϴ������½����ű��2.�۸������ȱ���Ϊ���ֵĵ�Ԫ���ڱ�����д���֣�";
		}
		return "������Ʒ��Ϣ�ɹ�!";
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
