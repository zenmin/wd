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

	//����excel
	public static ByteArrayInputStream createExcel(List pro) throws IOException{
		List<Map> project = pro;
		XSSFWorkbook workBook = new XSSFWorkbook();//����excel��
		XSSFSheet sheet = workBook.createSheet("��Ʒ���ϵ���");//����һ��sheet
		XSSFRow head = sheet.createRow(0);//��������
		
		head.createCell(0).setCellValue("����");
		head.createCell(1).setCellValue("����");
		head.createCell(2).setCellValue("С��");
		head.createCell(3).setCellValue("���");
		head.createCell(4).setCellValue("����");
		head.createCell(5).setCellValue("�ɱ�");
		head.createCell(6).setCellValue("���Ƽ�");
		head.createCell(7).setCellValue("�ۼ�");
		head.createCell(8).setCellValue("��ɫ");
		head.createCell(9).setCellValue("����");
		head.createCell(10).setCellValue("���");
		head.createCell(11).setCellValue("ִ�б�׼");
		head.createCell(12).setCellValue("��ע");
		head.createCell(13).setCellValue("����");
		head.createCell(14).setCellValue("��");
		head.createCell(15).setCellValue("��");
		head.createCell(16).setCellValue("��");
		head.createCell(17).setCellValue("�Ƿ�����");
		head.createCell(18).setCellValue("˵����");
		head.createCell(19).setCellValue("��װ����");
		head.createCell(20).setCellValue("��װ���");
		head.createCell(21).setCellValue("��װ�ߴ�");
		head.createCell(22).setCellValue("��С������");
		head.createCell(23).setCellValue("���");
		head.createCell(24).setCellValue("������");
		head.createCell(25).setCellValue("��Ŀ");
		
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
