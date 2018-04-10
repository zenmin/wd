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

import com.wd.models.TbReturns;
import com.wd.models.TbStock;

/**
 * ���¿������
 * @author ����
 *
 */

public class ReadUpdateStocksExcel {  
	public List updateStock(File file) throws ParseException, IOException{
		/*
		 * ��ȡ��ǰ������,����������
		 * �������ݿ�����,Ԥ�������,���̶���
		 * ��ȡ�������Զ��ϵ�����
		 * ����ÿ�м�¼,
		 */
		List<TbStock> list = new ArrayList<TbStock>();
		FileInputStream stream = null;
		try {
			stream = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//��ȡ������,��һ��������,������(ע���1)
		XSSFWorkbook nowExcel;
		try {
			nowExcel = new XSSFWorkbook(stream);

			XSSFSheet nowSheet=nowExcel.getSheetAt(0);
			int rowcount = nowSheet.getLastRowNum()+1;
			//��ȡ�����е��к�
			XSSFRow nowTitle=nowSheet.getRow(0);

			int 	tb_stock_bar_name_index=ExcelUtil.getCol("��Ʒ����", nowTitle),	
					tb_stock_bar_no_index=ExcelUtil.getCol("��Ʒ����", nowTitle),	
					tb_stock_goods_index=ExcelUtil.getCol("����", nowTitle),
					tb_stock_zone_index=ExcelUtil.getCol("����", nowTitle),
					tb_stock_num_index=ExcelUtil.getCol("�������", nowTitle),
					tb_stock_date_index=ExcelUtil.getCol("�ϼ�ʱ��", nowTitle);

			if((nowTitle.getCell(tb_stock_bar_no_index)==null||
					nowTitle.getCell(tb_stock_bar_no_index).equals("")||
					nowTitle.getCell(tb_stock_bar_no_index).getCellType() ==XSSFCell.CELL_TYPE_BLANK)){
				System.out.println("���������Ƿ����������ͻ���");
				return list;
			}

			//  ѭ����ȡÿ������
			for(int i=1;i<rowcount;i++){
				//  ��ȡ�ж�Ӧ������
				XSSFRow nowRow=nowSheet.getRow(i);

				XSSFCell tb_stock_name_cell=nowRow.getCell(tb_stock_bar_name_index);
				XSSFCell tb_stock_bar_no_cell=nowRow.getCell(tb_stock_bar_no_index);
				XSSFCell tb_stock_goods_cell=nowRow.getCell(tb_stock_goods_index);
				XSSFCell tb_stock_num_cell=nowRow.getCell(tb_stock_num_index);
				XSSFCell tb_stock_date_cell=nowRow.getCell(tb_stock_date_index);
				XSSFCell tb_stock_zone_cell=nowRow.getCell(tb_stock_zone_index);

				//  ʹ�ù��߰�cellǿתString

				//  ����special����Ʒ����
				String name = ExcelUtil.getStringValueFromCell(tb_stock_name_cell);
				// ����
				String barNo = ExcelUtil.getStringValueFromCell(tb_stock_bar_no_cell);
				// ����
				String goods = ExcelUtil.getStringValueFromCell(tb_stock_goods_cell);
				// �˻���
				String num = ExcelUtil.getStringValueFromCell(tb_stock_num_cell);
				// ������
				String zone = ExcelUtil.getStringValueFromCell(tb_stock_zone_cell);
				// ����
				String date = ExcelUtil.getStringValueFromCell(tb_stock_date_cell);

				if(barNo == "") {
					break;	
				}
				
				//  ѭ������������  ��װ��List��  
				TbStock r = new TbStock();
				r.setBarName(name);
				r.setBarNo(barNo);
				r.setDate(date);
				r.setGoods(goods);
				if((num.trim().equals(""))) {
					r.setStock(0);
				}else {
					r.setStock(Integer.parseInt(num));
				}

				r.setZone(zone);

				list.add(r);
			}
//			System.out.println("ReadWriteExcel" + list);
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