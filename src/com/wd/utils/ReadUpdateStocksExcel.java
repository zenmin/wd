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
 * 更新库存数据
 * @author 曾敏
 *
 */

public class ReadUpdateStocksExcel {  
	public List updateStock(File file) throws ParseException, IOException{
		/*
		 * 获取当前输入列,创建表格对象
		 * 创建数据库链接,预处理对象,过程对象
		 * 获取表格各个自动断的索引
		 * 遍历每行记录,
		 */
		List<TbStock> list = new ArrayList<TbStock>();
		FileInputStream stream = null;
		try {
			stream = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//获取工作簿,第一个工作表,总行数(注意加1)
		XSSFWorkbook nowExcel;
		try {
			nowExcel = new XSSFWorkbook(stream);

			XSSFSheet nowSheet=nowExcel.getSheetAt(0);
			int rowcount = nowSheet.getLastRowNum()+1;
			//获取标题行的列号
			XSSFRow nowTitle=nowSheet.getRow(0);

			int 	tb_stock_bar_name_index=ExcelUtil.getCol("商品名称", nowTitle),	
					tb_stock_bar_no_index=ExcelUtil.getCol("商品编码", nowTitle),	
					tb_stock_goods_index=ExcelUtil.getCol("货号", nowTitle),
					tb_stock_zone_index=ExcelUtil.getCol("区域", nowTitle),
					tb_stock_num_index=ExcelUtil.getCol("库存数量", nowTitle),
					tb_stock_date_index=ExcelUtil.getCol("上架时间", nowTitle);

			if((nowTitle.getCell(tb_stock_bar_no_index)==null||
					nowTitle.getCell(tb_stock_bar_no_index).equals("")||
					nowTitle.getCell(tb_stock_bar_no_index).getCellType() ==XSSFCell.CELL_TYPE_BLANK)){
				System.out.println("请检查数据是否包含条形码和货号");
				return list;
			}

			//  循环获取每列数据
			for(int i=1;i<rowcount;i++){
				//  获取列对应的数据
				XSSFRow nowRow=nowSheet.getRow(i);

				XSSFCell tb_stock_name_cell=nowRow.getCell(tb_stock_bar_name_index);
				XSSFCell tb_stock_bar_no_cell=nowRow.getCell(tb_stock_bar_no_index);
				XSSFCell tb_stock_goods_cell=nowRow.getCell(tb_stock_goods_index);
				XSSFCell tb_stock_num_cell=nowRow.getCell(tb_stock_num_index);
				XSSFCell tb_stock_date_cell=nowRow.getCell(tb_stock_date_index);
				XSSFCell tb_stock_zone_cell=nowRow.getCell(tb_stock_zone_index);

				//  使用工具吧cell强转String

				//  这里special是商品名称
				String name = ExcelUtil.getStringValueFromCell(tb_stock_name_cell);
				// 条码
				String barNo = ExcelUtil.getStringValueFromCell(tb_stock_bar_no_cell);
				// 货号
				String goods = ExcelUtil.getStringValueFromCell(tb_stock_goods_cell);
				// 退货量
				String num = ExcelUtil.getStringValueFromCell(tb_stock_num_cell);
				// 拒收量
				String zone = ExcelUtil.getStringValueFromCell(tb_stock_zone_cell);
				// 日期
				String date = ExcelUtil.getStringValueFromCell(tb_stock_date_cell);

				if(barNo == "") {
					break;	
				}
				
				//  循环遍历行数据  封装到List中  
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