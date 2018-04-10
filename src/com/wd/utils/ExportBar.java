package com.wd.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExportBar {

	//创建excel
	public static ByteArrayInputStream createExcel(List pro) throws IOException{
		List<Map> project = pro;
		XSSFWorkbook workBook = new XSSFWorkbook();//创建excel薄
		XSSFSheet sheet = workBook.createSheet("产品资料导出");//创建一个sheet
		XSSFRow head = sheet.createRow(0);//创建首行
		
		head.createCell(0).setCellValue("条码");
		head.createCell(1).setCellValue("货号");
		head.createCell(2).setCellValue("小组");
		head.createCell(3).setCellValue("简称");
		head.createCell(4).setCellValue("别名");
		head.createCell(5).setCellValue("成本");
		head.createCell(6).setCellValue("吊牌价");
		head.createCell(7).setCellValue("售价");
		head.createCell(8).setCellValue("颜色");
		head.createCell(9).setCellValue("材质");
		head.createCell(10).setCellValue("规格");
		head.createCell(11).setCellValue("执行标准");
		head.createCell(12).setCellValue("备注");
		head.createCell(13).setCellValue("重量");
		head.createCell(14).setCellValue("长");
		head.createCell(15).setCellValue("宽");
		head.createCell(16).setCellValue("高");
		head.createCell(17).setCellValue("是否易损");
		head.createCell(18).setCellValue("说明书");
		head.createCell(19).setCellValue("包装货号");
		head.createCell(20).setCellValue("包装情况");
		head.createCell(21).setCellValue("包装尺寸");
		head.createCell(22).setCellValue("大小件区分");
		head.createCell(23).setCellValue("标记");
		head.createCell(24).setCellValue("保质期");
		head.createCell(25).setCellValue("类目");
		
		for(int i=0; i<project.size();i++){
			Map p =  project.get(i);
			XSSFRow row = sheet.createRow(i+1);
			row.createCell(0).setCellValue((String) p.get("barNo"));
			row.createCell(1).setCellValue((String) p.get("goodsNo"));
			row.createCell(2).setCellValue((String) p.get("owner"));
			row.createCell(3).setCellValue((String) p.get("barSimplename"));
			row.createCell(4).setCellValue((String) p.get("alias"));
			row.createCell(5).setCellValue(String.valueOf(p.get("goodsPrice")));
			row.createCell(6).setCellValue(String.valueOf(p.get("barShowprice")));
			row.createCell(7).setCellValue(String.valueOf(p.get("barSaleprice")));
			row.createCell(8).setCellValue((String) p.get("barColor"));
			row.createCell(9).setCellValue((String) p.get("barMaterial"));
			row.createCell(10).setCellValue((String) p.get("barSpecifications"));
			row.createCell(11).setCellValue((String) p.get("barStandard"));
			row.createCell(12).setCellValue((String) p.get("remartks"));
			row.createCell(13).setCellValue(String.valueOf(p.get("scale")));
			row.createCell(14).setCellValue(String.valueOf(p.get("longs")));
			row.createCell(15).setCellValue(String.valueOf(p.get("widths")));
			row.createCell(16).setCellValue(String.valueOf(p.get("height")));
			row.createCell(17).setCellValue((String) p.get("rapidWear"));
			row.createCell(18).setCellValue((String) p.get("instructions"));
			row.createCell(19).setCellValue((String) p.get("packNo"));
			row.createCell(20).setCellValue((String) p.get("packCondition"));
			row.createCell(21).setCellValue((String) p.get("packSize"));
			row.createCell(22).setCellValue((String) p.get("isSize"));
			row.createCell(23).setCellValue((String) p.get("tabs"));
			row.createCell(24).setCellValue((String) p.get("terms"));
			row.createCell(25).setCellValue((String) p.get("_class"));
		}
		ByteArrayOutputStream bais = new ByteArrayOutputStream();
		workBook.write(bais);
		byte[] fileContent = bais.toByteArray();
		ByteArrayInputStream is = new ByteArrayInputStream(fileContent);
		return is;
	}
}
