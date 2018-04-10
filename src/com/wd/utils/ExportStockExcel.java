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

public class ExportStockExcel {

	//通过串来的参数，创建Excel表格，
	public static ByteArrayInputStream createExcel(List exportColList) throws Exception{

		List<Map> colList = exportColList;

		XSSFWorkbook workBook = new XSSFWorkbook();//创建一个Excel工作薄
		XSSFSheet sheet = workBook.createSheet("库存导出");//创建一个 销售导出表
	
		XSSFRow headerRow = sheet.createRow(0);//创建首行，并赋值
		headerRow.createCell(0).setCellValue("商品编码");
		headerRow.createCell(1).setCellValue("货号");
		headerRow.createCell(2).setCellValue("上架时间");
		headerRow.createCell(3).setCellValue("商品名称");
		headerRow.createCell(4).setCellValue("区域");
		headerRow.createCell(5).setCellValue("库存数量");
		
		sheet.setColumnWidth(0, 255*15);
		sheet.setColumnWidth(1, 255*15);
		sheet.setColumnWidth(2, 255*15);
		sheet.setColumnWidth(3, 255*38);
		sheet.setColumnWidth(4, 255*15);
		sheet.setColumnWidth(5, 255*15);
		
		for(int j=0; j<colList.size(); j++){
			Map exportCol = colList.get(j);
			XSSFRow row = sheet.createRow(j+1);
			row.createCell(0).setCellValue((String) exportCol.get("barNo"));
			row.createCell(1).setCellValue((String) exportCol.get("goods"));
			row.createCell(2).setCellValue((String) exportCol.get("date"));
			row.createCell(3).setCellValue((String) exportCol.get("barName"));
			row.createCell(4).setCellValue((String) exportCol.get("zone"));
			row.createCell(5).setCellValue((Integer) exportCol.get("stock"));
		
		}
		ByteArrayOutputStream os = new ByteArrayOutputStream();//将Excel文件存在输出流中
		workBook.write(os);//将Excel写入输出流中
		byte[] fileContent = os.toByteArray();//将输出流转换成字节数组
		ByteArrayInputStream is = new ByteArrayInputStream(fileContent);//将流的字节数组装在输入流中
		return is;
	}

}
