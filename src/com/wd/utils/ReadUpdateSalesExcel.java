package com.wd.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.wd.models.TbBar;
import com.wd.models.TbGoods;
import com.wd.models.TbReturns;
import com.wd.models.TbSales;

public class ReadUpdateSalesExcel {

	public List updateReturns(File excelFile) throws Exception {
		//        InputStream is = new FileInputStream("C:/Users/Administrator/Desktop/wd (1)/���ӱ�/�½�����.xlsx");

		//���������ļ�·���е��ļ���װ��������
		FileInputStream is = new FileInputStream(excelFile);
		//����һ������������
		XSSFWorkbook xssfWorkbook = new XSSFWorkbook(is);
		List list = new ArrayList();
		//�ӹ������л�ȡ������sheet
		int numSheets = xssfWorkbook.getNumberOfSheets();
		try {
			// ѭ��������Sheet
			for (int numSheet = 0; numSheet < numSheets; numSheet++) {
				XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(numSheet);
				if (xssfSheet == null) {
					continue;
				}

				XSSFRow nowTitle = xssfSheet.getRow(0);
				//Bar���� ��index

				int tb_sales_bar_no_index=ExcelUtil.getCol("������", nowTitle),
						tb_sales_special_index = ExcelUtil.getCol("ר��", nowTitle),
						tb_sales_zone_index=ExcelUtil.getCol("������", nowTitle),
						tb_sales_stock_index=ExcelUtil.getCol("�����", nowTitle),
						tb_sales_num_index=ExcelUtil.getCol("�ܱ�����", nowTitle),
						tb_sales_soldnum_index=ExcelUtil.getCol("����������δ�۾��ˣ�", nowTitle),
						tb_sales_soldmoney_index=ExcelUtil.getCol("�����۶δ������δ�۾��ˣ�", nowTitle),
						tb_sales_people_index=ExcelUtil.getCol("��������", nowTitle),
						tb_sales_date_index=ExcelUtil.getCol("����", nowTitle);

				// ѭ����Row;getLastRowNum():��ȡ������
				for (int rowNum = 1; rowNum <= xssfSheet.getLastRowNum(); rowNum++) {
					//��ȡÿһ��
					XSSFRow xssfRow = xssfSheet.getRow(rowNum);
					Map map = new HashMap<String, Object>();
					if (!(xssfRow.getCell(tb_sales_bar_no_index)==null||
							xssfRow.getCell(tb_sales_bar_no_index).equals("")||
							xssfRow.getCell(tb_sales_bar_no_index).getCellType() ==XSSFCell.CELL_TYPE_BLANK)) {
						System.out.println("ѭ����ǰ������"+rowNum);

						
						XSSFCell tb_sales_special_cell=xssfRow.getCell(tb_sales_special_index);
						XSSFCell tb_sales_bar_no_cell=xssfRow.getCell(tb_sales_bar_no_index);
						XSSFCell tb_sales_zone_cell=xssfRow.getCell(tb_sales_zone_index);
						XSSFCell tb_sales_num_cell=xssfRow.getCell(tb_sales_num_index);
						XSSFCell tb_sales_soldnum_cell=xssfRow.getCell(tb_sales_soldnum_index);
						XSSFCell tb_sales_soldmoney_cell=xssfRow.getCell(tb_sales_soldmoney_index);
						XSSFCell tb_sales_people_cell=xssfRow.getCell(tb_sales_people_index);
						XSSFCell tb_sales_date_cell=xssfRow.getCell(tb_sales_date_index);
						XSSFCell tb_sales_stock_cell=xssfRow.getCell(tb_sales_stock_index);

						//  ʹ�ù��߰�cellǿתString

						//  ����special����Ʒ����
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
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			is.close();
			return null;
		}
		is.close();
		return list;
	}

}
