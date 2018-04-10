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

	//通过串来的参数，创建Excel表格，
	public static ByteArrayInputStream createExcel(List exportColList) throws Exception{

		List<Map> colList = exportColList;

		XSSFWorkbook workBook = new XSSFWorkbook();//创建一个Excel工作薄
		XSSFSheet sheet = workBook.createSheet("库存导出");//创建一个 销售导出表
	
		XSSFRow headerRow = sheet.createRow(0);//创建首行，并赋值
		headerRow.createCell(0).setCellValue("条形码");
		headerRow.createCell(1).setCellValue("货号");
		headerRow.createCell(2).setCellValue("退货原因");
		headerRow.createCell(3).setCellValue("档期");
		headerRow.createCell(4).setCellValue("退货量");
		headerRow.createCell(5).setCellValue("拒收量");
		
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
		ByteArrayOutputStream os = new ByteArrayOutputStream();//将Excel文件存在输出流中
		workBook.write(os);//将Excel写入输出流中
		byte[] fileContent = os.toByteArray();//将输出流转换成字节数组
		ByteArrayInputStream is = new ByteArrayInputStream(fileContent);//将流的字节数组装在输入流中
		return is;
	}

}
