package com.wd.test;

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
import org.junit.Test;

import com.wd.models.TbReturns;
import com.wd.models.TbSales;

/**
 * ������������
 * @author ����
 *
 */

public class ReadUpdateReturnsExcel {  
	@Test
	public void updateRetuens() throws ParseException, IOException{
		/*
		 * ��ȡ��ǰ������,����������
		 * �������ݿ�����,Ԥ�������,���̶���
		 * ��ȡ�������Զ��ϵ�����
		 * ����ÿ�м�¼,
		 */
		List<TbReturns> list = new ArrayList<TbReturns>();
		FileInputStream stream = null;
		try {
			stream = new FileInputStream(new File("C:/Users/fx/Desktop/wd/���ӱ�/������ˮ.xlsx"));
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
			
			int 	tb_return_bar_name_index=ExcelUtil.getCol("��Ʒ����", nowTitle),	
					tb_return_bar_no_index=ExcelUtil.getCol("������", nowTitle),	
					tb_return_goods_index=ExcelUtil.getCol("����", nowTitle),
					tb_return_num_index=ExcelUtil.getCol("�˻���", nowTitle),
					tb_return_jecnum_index=ExcelUtil.getCol("������", nowTitle),
					tb_return_returnrate_index=ExcelUtil.getCol("ͬ�����ռ��", nowTitle),
					tb_return_date_index=ExcelUtil.getCol("����", nowTitle),
					tb_return_reason_index=ExcelUtil.getCol("�˻�ԭ��", nowTitle);

			if(tb_return_bar_no_index==0 || tb_return_bar_no_index==0){
				System.out.println("���������Ƿ�����������������");
				((Closeable) nowExcel).close();
			}

			//  ѭ����ȡÿ������
			for(int i=1;i<rowcount;i++){
				//  ��ȡ�ж�Ӧ������
				XSSFRow nowRow=nowSheet.getRow(i);
				
				XSSFCell tb_return_name_cell=nowRow.getCell(tb_return_bar_name_index);
				XSSFCell tb_return_bar_no_cell=nowRow.getCell(tb_return_bar_no_index);
				XSSFCell tb_return_goods_cell=nowRow.getCell(tb_return_goods_index);
				XSSFCell tb_return_num_cell=nowRow.getCell(tb_return_num_index);
				XSSFCell tb_return_jecnum_cell=nowRow.getCell(tb_return_jecnum_index);
				XSSFCell tb_return_returnrate_cell=nowRow.getCell(tb_return_returnrate_index);
				XSSFCell tb_return_date_cell=nowRow.getCell(tb_return_date_index);
				XSSFCell tb_return_reason_cell=nowRow.getCell(tb_return_reason_index);

				//  ʹ�ù��߰�cellǿתString
				
				//  ����special����Ʒ����
				String name = ExcelUtil.getStringValueFromCell(tb_return_name_cell);
				// ����
				String barNo = ExcelUtil.getStringValueFromCell(tb_return_bar_no_cell);
				// ����
				String goods = ExcelUtil.getStringValueFromCell(tb_return_goods_cell);
				// �˻���
				String returnNum = ExcelUtil.getStringValueFromCell(tb_return_num_cell);
				// ������
				String jecNum = ExcelUtil.getStringValueFromCell(tb_return_jecnum_cell);
				// ������
				String returnRate = ExcelUtil.getStringValueFromCell(tb_return_returnrate_cell);
				// ����
				String date = ExcelUtil.getStringValueFromCell(tb_return_date_cell);
				// ԭ��
				String reason = ExcelUtil.getStringValueFromCell(tb_return_reason_cell);

				//  ѭ������������  ��װ��List��  
				TbReturns r = new TbReturns();
				r.setSpecial(name);
				r.setBarNo(barNo);
				r.setDate(date);
				r.setGoodsNo(goods);
				r.setReturnrate(Double.valueOf(returnRate));
				r.setReturnsRejectnum(Integer.parseInt(jecNum));
				r.setReturnsReturnnum(Integer.parseInt(returnNum));
				r.setReturnsReason(reason);
				list.add(r);
			}
			System.out.println("ReadWriteExcel" + list);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			stream.close();
		}
		
		
	}
}