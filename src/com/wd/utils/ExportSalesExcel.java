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

	//通过串来的参数，创建Excel表格，
	public static ByteArrayInputStream createExcel(List exportColList, int[] checkCondition) throws Exception{
		List<Map> colList = exportColList;
		
		XSSFWorkbook workBook = new XSSFWorkbook();//创建一个Excel工作薄
		XSSFSheet sheet = workBook.createSheet("销售导出");//创建一个 销售导出表
		
		XSSFRow headerRow = sheet.createRow(0);//创建首行，并赋值
//		headerRow.createCell(0).setCellValue("条码");
//		headerRow.createCell(1).setCellValue("日期");
//		headerRow.createCell(2).setCellValue("总销售量");
//		headerRow.createCell(3).setCellValue("专场名称");
//		headerRow.createCell(4).setCellValue("区域");
//		headerRow.createCell(5).setCellValue("货号");
//		headerRow.createCell(6).setCellValue("品名");
//		headerRow.createCell(7).setCellValue("小组");
//		headerRow.createCell(8).setCellValue("类目");
		
		
		for(int i=0; i < checkCondition.length; i++ ){
			 switch(checkCondition[i]){
			 case 0:
					headerRow.createCell(i).setCellValue("条码");
					sheet.setColumnWidth(i, 255*15);
				 break;
			 case 1:
					headerRow.createCell(i).setCellValue("日期");
					sheet.setColumnWidth(i, 255*13);
				 break;
			 case 2:
					headerRow.createCell(i).setCellValue("总销售量");
					sheet.setColumnWidth(i, 255*10);
				 break;
			 case 3:
					headerRow.createCell(i).setCellValue("专场名称");
					sheet.setColumnWidth(i, 255*35);
				 break;
			 case 4:
					headerRow.createCell(i).setCellValue("区域");
					sheet.setColumnWidth(i, 255*10);
				 break;
			 case 5:
					headerRow.createCell(i).setCellValue("货号");
					sheet.setColumnWidth(i, 255*10);
				 break;
			 case 6:
					headerRow.createCell(i).setCellValue("品名");
					sheet.setColumnWidth(i, 255*32);
				 break;
			 case 7:
					headerRow.createCell(i).setCellValue("小组");
					sheet.setColumnWidth(i, 255*8);
				 break;
			 case 8:
					headerRow.createCell(i).setCellValue("类目");
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
		ByteArrayOutputStream os = new ByteArrayOutputStream();//将Excel文件存在输出流中
		workBook.write(os);//将Excel写入输出流中
		byte[] fileContent = os.toByteArray();//将输出流转换成字节数组
		ByteArrayInputStream is = new ByteArrayInputStream(fileContent);//将流的字节数组装在输入流中
		return is;
	}
	
}
