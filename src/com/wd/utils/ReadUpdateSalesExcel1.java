package com.wd.utils;

import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;  
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;  

import com.wd.models.TbSales;

/**
 * ������������
 * @author ����
 *
 */

public class ReadUpdateSalesExcel1 {  
	public List updateSales(File file) throws ParseException, IOException{
		/*
		 * ��ȡ��ǰ������,����������
		 * �������ݿ�����,Ԥ�������,���̶���
		 * ��ȡ�������Զ��ϵ�����
		 * ����ÿ�м�¼,
		 */
		List<TbSales> list = new ArrayList<TbSales>();
		FileInputStream stream = null;
			stream = new FileInputStream(file);
		//��ȡ������,��һ��������,������(ע���1)
		XSSFWorkbook nowExcel;
		try {
			nowExcel = new XSSFWorkbook(stream);

			XSSFSheet nowSheet=nowExcel.getSheetAt(0);
			int rowcount = nowSheet.getLastRowNum()+1;
			//��ȡ�����е��к�
			XSSFRow nowTitle=nowSheet.getRow(0);
			int tb_sales_bar_no_index=ExcelUtil.getCol("������", nowTitle),
					tb_sales_special_index = ExcelUtil.getCol("ר��", nowTitle),
					tb_sales_zone_index=ExcelUtil.getCol("������", nowTitle),
					tb_sales_stock_index=ExcelUtil.getCol("�����", nowTitle),
					tb_sales_num_index=ExcelUtil.getCol("�ܱ�����", nowTitle),
					tb_sales_soldnum_index=ExcelUtil.getCol("����������δ�۾��ˣ�", nowTitle),
					tb_sales_soldmoney_index=ExcelUtil.getCol("�����۶δ������δ�۾��ˣ�", nowTitle),
					tb_sales_people_index=ExcelUtil.getCol("��������", nowTitle),
					tb_sales_date_index=ExcelUtil.getCol("����", nowTitle);

			if(tb_sales_bar_no_index==0 || tb_sales_zone_index==0){
				System.out.println("���������Ƿ�����������������");
				return list;
			}

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

				if(barNo == "") {
					break;	
				}
				
				//  ѭ������������  ��װ��List��  
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
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		finally {
			stream.close();
		}
		
		return list;
	}
}