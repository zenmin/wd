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

public class ReadUpdateReturnsExcel {

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

				int 	tb_return_bar_name_index=ExcelUtil.getCol("��Ʒ����", nowTitle),	
						tb_return_bar_no_index=ExcelUtil.getCol("������", nowTitle),	
						tb_return_goods_index=ExcelUtil.getCol("����", nowTitle),
						tb_return_num_index=ExcelUtil.getCol("�˻���", nowTitle),
						tb_return_jecnum_index=ExcelUtil.getCol("������", nowTitle),
						tb_return_returnrate_index=ExcelUtil.getCol("ͬ�����ռ��", nowTitle),
						tb_return_date_index=ExcelUtil.getCol("����", nowTitle),
						tb_return_reason_index=ExcelUtil.getCol("�˻�ԭ��", nowTitle);


				// ѭ����Row;getLastRowNum():��ȡ������
				for (int rowNum = 1; rowNum <= xssfSheet.getLastRowNum(); rowNum++) {
					//��ȡÿһ��
					XSSFRow xssfRow = xssfSheet.getRow(rowNum);
					Map map = new HashMap<String, Object>();
					if (!(xssfRow.getCell(tb_return_bar_no_index)==null||
							xssfRow.getCell(tb_return_bar_no_index).equals("")||
							xssfRow.getCell(tb_return_bar_no_index).getCellType() ==XSSFCell.CELL_TYPE_BLANK)) {
						System.out.println("ѭ����ǰ������"+rowNum);

						
						XSSFCell tb_return_name_cell=xssfRow.getCell(tb_return_bar_name_index);
						XSSFCell tb_return_bar_no_cell=xssfRow.getCell(tb_return_bar_no_index);
						XSSFCell tb_return_goods_cell=xssfRow.getCell(tb_return_goods_index);
						XSSFCell tb_return_num_cell=xssfRow.getCell(tb_return_num_index);
						XSSFCell tb_return_jecnum_cell=xssfRow.getCell(tb_return_jecnum_index);
						XSSFCell tb_return_returnrate_cell=xssfRow.getCell(tb_return_returnrate_index);
						XSSFCell tb_return_date_cell=xssfRow.getCell(tb_return_date_index);
						XSSFCell tb_return_reason_cell=xssfRow.getCell(tb_return_reason_index);

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
				
						TbReturns r = new TbReturns();
						r.setSpecial(name);
						r.setBarNo(barNo);
						r.setDate(date);
						r.setGoodsNo(goods);
						if((returnRate.trim().equals(""))==false) {
							System.out.println("returnRate" + returnRate);
						r.setReturnrate(Double.valueOf(returnRate));
						}
						if((jecNum.trim().equals(""))==false) {
							System.out.println("jecNum" + jecNum);
						r.setReturnsRejectnum(Integer.valueOf((jecNum)));
						}
						if((returnNum.trim().equals(""))==false) {
							r.setReturnsReturnnum(Integer.parseInt(returnNum));
							}
						r.setReturnsReason(reason);
						list.add(r);
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
