package com.wd.test;

import java.io.Closeable;
import java.io.FileInputStream;  
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;  
import org.junit.Test;

import com.wd.models.TbSales;
import com.wd.utils.ExcelUtil;
public class ReadWriteExcel {  
	@Test
	public void updateSales() throws IOException, ParseException{
		/*
		 * ��ȡ��ǰ������,����������
		 * �������ݿ�����,Ԥ�������,���̶���
		 * ��ȡ�������Զ��ϵ�����
		 * ����ÿ�м�¼,
		 */
		List<TbSales> list = new ArrayList<TbSales>();
		FileInputStream stream = new FileInputStream("C:/Users/fx/Desktop/wd/������ˮ1.xlsx");
		//��ȡ������,��һ��������,������(ע���1)
		XSSFWorkbook nowExcel=new XSSFWorkbook(stream);
		XSSFSheet nowSheet=nowExcel.getSheetAt(0);
		int rowcount = nowSheet.getLastRowNum()+1;

		//��ȡ�����е��к�
		XSSFRow nowTitle=nowSheet.getRow(0);
		int tb_sales_bar_no_index=ExcelUtil.getCol("������", nowTitle),
				tb_sales_special_index = ExcelUtil.getCol("Ʒ������", nowTitle),
				tb_sales_zone_index=ExcelUtil.getCol("������", nowTitle),
				tb_sales_stock_index=ExcelUtil.getCol("�����", nowTitle),
				tb_sales_num_index=ExcelUtil.getCol("�ܱ�����", nowTitle),
				tb_sales_soldnum_index=ExcelUtil.getCol("��������", nowTitle),
				tb_sales_soldmoney_index=ExcelUtil.getCol("���۶�(������δ�۾���)", nowTitle),
				tb_sales_people_index=ExcelUtil.getCol("��������", nowTitle),
				tb_sales_date_index=ExcelUtil.getCol("����", nowTitle);

		if(tb_sales_bar_no_index==0 || tb_sales_zone_index==0){
			System.out.println("���������Ƿ���� ������� ������");
			((Closeable) nowExcel).close();
		}
		
		System.out.println(rowcount);
		
		//  ѭ����ȡÿ������
		for(int i=1;i<rowcount;i++){
			//  ��ȡ�ж�Ӧ������
			XSSFRow nowRow=nowSheet.getRow(i);
			XSSFCell tb_sales_special_cell=nowRow.getCell(tb_sales_special_index);
			XSSFCell tb_sales_bar_no_cell=nowRow.getCell(tb_sales_bar_no_index);
			XSSFCell tb_sales_zone_cell=nowRow.getCell(tb_sales_zone_index);
			XSSFCell tb_sales_num_cell=nowRow.getCell(tb_sales_num_index);
			XSSFCell tb_sales_soldnum_cell=nowRow.getCell(tb_sales_soldnum_index);
			XSSFCell tb_sales_soldmoney_cell=nowRow.getCell(tb_sales_soldmoney_index);
			XSSFCell tb_sales_people_cell=nowRow.getCell(tb_sales_people_index);
			XSSFCell tb_sales_date_cell=nowRow.getCell(tb_sales_date_index);
			XSSFCell tb_sales_stock_cell=nowRow.getCell(tb_sales_stock_index);

			//  ʹ�ù��߰�cellǿתString
			//ר��
			String special = ExcelUtil.getStringValueFromCell(tb_sales_special_cell);
			// ����
			String barNo = ExcelUtil.getStringValueFromCell(tb_sales_bar_no_cell);
			// ���۳���
			String zone = ExcelUtil.getStringValueFromCell(tb_sales_zone_cell);
			// ������
			String salesNum = ExcelUtil.getStringValueFromCell(tb_sales_num_cell);
			// ����
			String soldNum = ExcelUtil.getStringValueFromCell(tb_sales_soldnum_cell);
			// ���۶�
			String soldMoney = ExcelUtil.getStringValueFromCell(tb_sales_soldmoney_cell);
			// ��������
			String salesPeople =ExcelUtil.getStringValueFromCell(tb_sales_people_cell);
			// ����
			String date = ExcelUtil.getStringValueFromCell(tb_sales_date_cell);
			// ���
			String salesStock = ExcelUtil.getStringValueFromCell(tb_sales_stock_cell);

						System.out.println(special
								+ " " +
								barNo 
								+ " " + 
								zone
								+ " " +
								salesNum
								+" "  + 
								soldNum  
								+ " " + 
								soldMoney 
								+ " " + 
								salesPeople 
								+ " " +
								date
								+ " " + 
								salesStock
								);
			TbSales sales = new TbSales();
			sales.setBarNo(barNo);
			sales.setDate(date);
			sales.setSalesNum(Integer.parseInt(salesNum));
			sales.setSalesSoldmoney(Double.valueOf(soldMoney));
			sales.setSalesSoldnum((Double.valueOf(soldNum)));
			sales.setSalesStock((Double.valueOf(salesStock)));
			sales.setZone(zone);
			sales.setSpecial(special);
			sales.setSalesPeople(Double.valueOf(salesPeople));
			list.add(sales);
		}

		System.out.println(list);
		stream.close();
	}
}