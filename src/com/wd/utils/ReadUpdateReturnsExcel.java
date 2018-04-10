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
		//        InputStream is = new FileInputStream("C:/Users/Administrator/Desktop/wd (1)/链接表/新建货号.xlsx");

		//将传来的文件路径中的文件封装到输入流
		FileInputStream is = new FileInputStream(excelFile);
		//创建一个工作薄对象
		XSSFWorkbook xssfWorkbook = new XSSFWorkbook(is);
		List list = new ArrayList();
		//从工作薄中获取工作栏sheet
		int numSheets = xssfWorkbook.getNumberOfSheets();
		try {
			// 循环工作表Sheet
			for (int numSheet = 0; numSheet < numSheets; numSheet++) {
				XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(numSheet);
				if (xssfSheet == null) {
					continue;
				}

				XSSFRow nowTitle = xssfSheet.getRow(0);
				//Bar表列 的index

				int 	tb_return_bar_name_index=ExcelUtil.getCol("商品名称", nowTitle),	
						tb_return_bar_no_index=ExcelUtil.getCol("条形码", nowTitle),	
						tb_return_goods_index=ExcelUtil.getCol("货号", nowTitle),
						tb_return_num_index=ExcelUtil.getCol("退货量", nowTitle),
						tb_return_jecnum_index=ExcelUtil.getCol("拒收量", nowTitle),
						tb_return_returnrate_index=ExcelUtil.getCol("同因拒退占比", nowTitle),
						tb_return_date_index=ExcelUtil.getCol("档期", nowTitle),
						tb_return_reason_index=ExcelUtil.getCol("退货原因", nowTitle);


				// 循环行Row;getLastRowNum():获取总行数
				for (int rowNum = 1; rowNum <= xssfSheet.getLastRowNum(); rowNum++) {
					//获取每一行
					XSSFRow xssfRow = xssfSheet.getRow(rowNum);
					Map map = new HashMap<String, Object>();
					if (!(xssfRow.getCell(tb_return_bar_no_index)==null||
							xssfRow.getCell(tb_return_bar_no_index).equals("")||
							xssfRow.getCell(tb_return_bar_no_index).getCellType() ==XSSFCell.CELL_TYPE_BLANK)) {
						System.out.println("循环当前行数："+rowNum);

						
						XSSFCell tb_return_name_cell=xssfRow.getCell(tb_return_bar_name_index);
						XSSFCell tb_return_bar_no_cell=xssfRow.getCell(tb_return_bar_no_index);
						XSSFCell tb_return_goods_cell=xssfRow.getCell(tb_return_goods_index);
						XSSFCell tb_return_num_cell=xssfRow.getCell(tb_return_num_index);
						XSSFCell tb_return_jecnum_cell=xssfRow.getCell(tb_return_jecnum_index);
						XSSFCell tb_return_returnrate_cell=xssfRow.getCell(tb_return_returnrate_index);
						XSSFCell tb_return_date_cell=xssfRow.getCell(tb_return_date_index);
						XSSFCell tb_return_reason_cell=xssfRow.getCell(tb_return_reason_index);

						//  使用工具吧cell强转String

						//  这里special是商品名称
						String name = ExcelUtil.getStringValueFromCell(tb_return_name_cell);
						// 条码
						String barNo = ExcelUtil.getStringValueFromCell(tb_return_bar_no_cell);
						// 货号
						String goods = ExcelUtil.getStringValueFromCell(tb_return_goods_cell);
						// 退货量
						String returnNum = ExcelUtil.getStringValueFromCell(tb_return_num_cell);
						// 拒收量
						String jecNum = ExcelUtil.getStringValueFromCell(tb_return_jecnum_cell);
						// 据退率
						String returnRate = ExcelUtil.getStringValueFromCell(tb_return_returnrate_cell);
						// 日期
						String date = ExcelUtil.getStringValueFromCell(tb_return_date_cell);
						// 原因
					
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
