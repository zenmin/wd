package com.wd.service;

import java.io.File;
import java.util.List;

public interface IUpdateBarAndGoodsService {
	
	//��ȡExcel�е�����,����һ��List(Map{},...)����������
	public List getExcelValue(File excelFile) throws Exception;
	
	//�����ݱ������µ����ݿ�
	public String ExcelSave(File excelFile);

}
