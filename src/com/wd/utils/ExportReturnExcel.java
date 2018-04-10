package com.wd.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExportReturnExcel {

	//ͨ�������Ĳ���������Excel���
	public static ByteArrayInputStream createExcel(List exportColList) throws Exception{

		List<Map> colList = exportColList;

		XSSFWorkbook workBook = new XSSFWorkbook();//����һ��Excel������
		XSSFSheet sheet = workBook.createSheet("��浼��");//����һ�� ���۵�����
	
		XSSFRow headerRow = sheet.createRow(0);//�������У�����ֵ
		headerRow.createCell(0).setCellValue("������");
		headerRow.createCell(1).setCellValue("����");
		headerRow.createCell(2).setCellValue("�˻�ԭ��");
		headerRow.createCell(3).setCellValue("����");
		headerRow.createCell(4).setCellValue("�˻���");
		headerRow.createCell(5).setCellValue("������");
		
		sheet.setColumnWidth(0, 255*15);
		sheet.setColumnWidth(1, 255*15);
		sheet.setColumnWidth(2, 255*15);
		sheet.setColumnWidth(3, 255*15);
		sheet.setColumnWidth(4, 255*15);
		sheet.setColumnWidth(5, 255*15);
		
		for(int j=0; j<colList.size(); j++){
			Map exportCol = colList.get(j);
			XSSFRow row = sheet.createRow(j+1);
			row.createCell(0).setCellValue((String) exportCol.get("barNo"));
			row.createCell(1).setCellValue((String) exportCol.get("goods"));
			row.createCell(2).setCellValue((String) exportCol.get("reason"));
			row.createCell(3).setCellValue((String) exportCol.get("date"));
			row.createCell(4).setCellValue((Integer) exportCol.get("num"));
			row.createCell(5).setCellValue((Integer) exportCol.get("jecnum"));
		
		}
		ByteArrayOutputStream os = new ByteArrayOutputStream();//��Excel�ļ������������
		workBook.write(os);//��Excelд���������
		byte[] fileContent = os.toByteArray();//�������ת�����ֽ�����
		ByteArrayInputStream is = new ByteArrayInputStream(fileContent);//�������ֽ�����װ����������
		return is;
	}

}
