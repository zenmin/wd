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
		 * 获取当前输入列,创建表格对象
		 * 创建数据库链接,预处理对象,过程对象
		 * 获取表格各个自动断的索引
		 * 遍历每行记录,
		 */
		List<TbSales> list = new ArrayList<TbSales>();
		FileInputStream stream = new FileInputStream("C:/Users/fx/Desktop/wd/销售流水1.xlsx");
		//获取工作簿,第一个工作表,总行数(注意加1)
		XSSFWorkbook nowExcel=new XSSFWorkbook(stream);
		XSSFSheet nowSheet=nowExcel.getSheetAt(0);
		int rowcount = nowSheet.getLastRowNum()+1;

		//获取标题行的列号
		XSSFRow nowTitle=nowSheet.getRow(0);
		int tb_sales_bar_no_index=ExcelUtil.getCol("条形码", nowTitle),
				tb_sales_special_index = ExcelUtil.getCol("品牌名称", nowTitle),
				tb_sales_zone_index=ExcelUtil.getCol("所在区", nowTitle),
				tb_sales_stock_index=ExcelUtil.getCol("库存量", nowTitle),
				tb_sales_num_index=ExcelUtil.getCol("总备货量", nowTitle),
				tb_sales_soldnum_index=ExcelUtil.getCol("总销售量", nowTitle),
				tb_sales_soldmoney_index=ExcelUtil.getCol("销售额(扣满减未扣拒退)", nowTitle),
				tb_sales_people_index=ExcelUtil.getCol("购买人数", nowTitle),
				tb_sales_date_index=ExcelUtil.getCol("档期", nowTitle);

		if(tb_sales_bar_no_index==0 || tb_sales_zone_index==0){
			System.out.println("请检查数据是否包含 条形码和 所在区");
			((Closeable) nowExcel).close();
		}
		
		System.out.println(rowcount);
		
		//  循环获取每列数据
		for(int i=1;i<rowcount;i++){
			//  获取列对应的数据
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

			//  使用工具吧cell强转String
			//专场
			String special = ExcelUtil.getStringValueFromCell(tb_sales_special_cell);
			// 条码
			String barNo = ExcelUtil.getStringValueFromCell(tb_sales_bar_no_cell);
			// 销售城市
			String zone = ExcelUtil.getStringValueFromCell(tb_sales_zone_cell);
			// 备货量
			String salesNum = ExcelUtil.getStringValueFromCell(tb_sales_num_cell);
			// 销量
			String soldNum = ExcelUtil.getStringValueFromCell(tb_sales_soldnum_cell);
			// 销售额
			String soldMoney = ExcelUtil.getStringValueFromCell(tb_sales_soldmoney_cell);
			// 购买人数
			String salesPeople =ExcelUtil.getStringValueFromCell(tb_sales_people_cell);
			// 日期
			String date = ExcelUtil.getStringValueFromCell(tb_sales_date_cell);
			// 库存
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