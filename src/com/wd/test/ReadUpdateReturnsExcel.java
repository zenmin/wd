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
 * 更新销售数据
 * @author 曾敏
 *
 */

public class ReadUpdateReturnsExcel {  
	@Test
	public void updateRetuens() throws ParseException, IOException{
		/*
		 * 获取当前输入列,创建表格对象
		 * 创建数据库链接,预处理对象,过程对象
		 * 获取表格各个自动断的索引
		 * 遍历每行记录,
		 */
		List<TbReturns> list = new ArrayList<TbReturns>();
		FileInputStream stream = null;
		try {
			stream = new FileInputStream(new File("C:/Users/fx/Desktop/wd/链接表/客退流水.xlsx"));
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
			
			int 	tb_return_bar_name_index=ExcelUtil.getCol("商品名称", nowTitle),	
					tb_return_bar_no_index=ExcelUtil.getCol("条形码", nowTitle),	
					tb_return_goods_index=ExcelUtil.getCol("货号", nowTitle),
					tb_return_num_index=ExcelUtil.getCol("退货量", nowTitle),
					tb_return_jecnum_index=ExcelUtil.getCol("拒收量", nowTitle),
					tb_return_returnrate_index=ExcelUtil.getCol("同因拒退占比", nowTitle),
					tb_return_date_index=ExcelUtil.getCol("档期", nowTitle),
					tb_return_reason_index=ExcelUtil.getCol("退货原因", nowTitle);

			if(tb_return_bar_no_index==0 || tb_return_bar_no_index==0){
				System.out.println("请检查数据是否包含条形码和所在区");
				((Closeable) nowExcel).close();
			}

			//  循环获取每列数据
			for(int i=1;i<rowcount;i++){
				//  获取列对应的数据
				XSSFRow nowRow=nowSheet.getRow(i);
				
				XSSFCell tb_return_name_cell=nowRow.getCell(tb_return_bar_name_index);
				XSSFCell tb_return_bar_no_cell=nowRow.getCell(tb_return_bar_no_index);
				XSSFCell tb_return_goods_cell=nowRow.getCell(tb_return_goods_index);
				XSSFCell tb_return_num_cell=nowRow.getCell(tb_return_num_index);
				XSSFCell tb_return_jecnum_cell=nowRow.getCell(tb_return_jecnum_index);
				XSSFCell tb_return_returnrate_cell=nowRow.getCell(tb_return_returnrate_index);
				XSSFCell tb_return_date_cell=nowRow.getCell(tb_return_date_index);
				XSSFCell tb_return_reason_cell=nowRow.getCell(tb_return_reason_index);

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

				//  循环遍历行数据  封装到List中  
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