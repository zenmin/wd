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

public class ExportSalesExcel {

	//ͨ�������Ĳ���������Excel���
	public static ByteArrayInputStream createExcel(List exportColList, int[] checkCondition) throws Exception{
		List<Map> colList = exportColList;
		
		XSSFWorkbook workBook = new XSSFWorkbook();//����һ��Excel������
		XSSFSheet sheet = workBook.createSheet("���۵���");//����һ�� ���۵�����
		
		XSSFRow headerRow = sheet.createRow(0);//�������У�����ֵ
//		headerRow.createCell(0).setCellValue("����");
//		headerRow.createCell(1).setCellValue("����");
//		headerRow.createCell(2).setCellValue("��������");
//		headerRow.createCell(3).setCellValue("ר������");
//		headerRow.createCell(4).setCellValue("����");
//		headerRow.createCell(5).setCellValue("����");
//		headerRow.createCell(6).setCellValue("Ʒ��");
//		headerRow.createCell(7).setCellValue("С��");
//		headerRow.createCell(8).setCellValue("��Ŀ");
		
		
		for(int i=0; i < checkCondition.length; i++ ){
			 switch(checkCondition[i]){
			 case 0:
					headerRow.createCell(i).setCellValue("����");
					sheet.setColumnWidth(i, 255*15);
				 break;
			 case 1:
					headerRow.createCell(i).setCellValue("����");
					sheet.setColumnWidth(i, 255*13);
				 break;
			 case 2:
					headerRow.createCell(i).setCellValue("��������");
					sheet.setColumnWidth(i, 255*10);
				 break;
			 case 3:
					headerRow.createCell(i).setCellValue("ר������");
					sheet.setColumnWidth(i, 255*35);
				 break;
			 case 4:
					headerRow.createCell(i).setCellValue("����");
					sheet.setColumnWidth(i, 255*10);
				 break;
			 case 5:
					headerRow.createCell(i).setCellValue("����");
					sheet.setColumnWidth(i, 255*10);
				 break;
			 case 6:
					headerRow.createCell(i).setCellValue("Ʒ��");
					sheet.setColumnWidth(i, 255*32);
				 break;
			 case 7:
					headerRow.createCell(i).setCellValue("С��");
					sheet.setColumnWidth(i, 255*8);
				 break;
			 case 8:
					headerRow.createCell(i).setCellValue("��Ŀ");
					sheet.setColumnWidth(i, 255*35);
				 break;
			default:
				break;
			 }
		 }
		
		
		for(int j=0; j<colList.size(); j++){
			Map exportCol = colList.get(j);
			XSSFRow row = sheet.createRow(j+1);
//			row.createCell(0).setCellValue((String) exportCol.get("barNo"));
//			row.createCell(1).setCellValue((String) exportCol.get("data"));
//			row.createCell(2).setCellValue((Double) exportCol.get("salesStock"));
//			row.createCell(3).setCellValue((String) exportCol.get("special"));
//			row.createCell(4).setCellValue((String) exportCol.get("znoe"));
//			row.createCell(5).setCellValue((String) exportCol.get("goodsNo"));
//			row.createCell(6).setCellValue((String) exportCol.get("barName"));
//			row.createCell(7).setCellValue((String) exportCol.get("owner"));
//			row.createCell(8).setCellValue((String) exportCol.get("class_"));
			
			for(int i=0; i < checkCondition.length; i++ ){
				 switch(checkCondition[i]){
				 case 0:
						row.createCell(i).setCellValue((String) exportCol.get("barNo"));
					 break;
				 case 1:
						row.createCell(i).setCellValue((String) exportCol.get("data"));
					 break;
				 case 2:
						row.createCell(i).setCellValue((Double) exportCol.get("salesStock"));
					 break;
				 case 3:
						row.createCell(i).setCellValue((String) exportCol.get("special"));
					 break;
				 case 4:
						row.createCell(i).setCellValue((String) exportCol.get("znoe"));
					 break;
				 case 5:
						row.createCell(i).setCellValue((String) exportCol.get("goodsNo"));
					 break;
				 case 6:
						row.createCell(i).setCellValue((String) exportCol.get("barName"));
					 break;
				 case 7:
						row.createCell(i).setCellValue((String) exportCol.get("owner"));
					 break;
				 case 8:
						row.createCell(i).setCellValue((String) exportCol.get("class_"));
					 break;
				default:
					break;
				 }
			 }
		}
		ByteArrayOutputStream os = new ByteArrayOutputStream();//��Excel�ļ������������
		workBook.write(os);//��Excelд���������
		byte[] fileContent = os.toByteArray();//�������ת�����ֽ�����
		ByteArrayInputStream is = new ByteArrayInputStream(fileContent);//�������ֽ�����װ����������
		return is;
	}
	
}
