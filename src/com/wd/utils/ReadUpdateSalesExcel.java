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

				int tb_sales_bar_no_index=ExcelUtil.getCol("条形码", nowTitle),
						tb_sales_special_index = ExcelUtil.getCol("专场", nowTitle),
						tb_sales_zone_index=ExcelUtil.getCol("所在区", nowTitle),
						tb_sales_stock_index=ExcelUtil.getCol("库存量", nowTitle),
						tb_sales_num_index=ExcelUtil.getCol("总备货量", nowTitle),
						tb_sales_soldnum_index=ExcelUtil.getCol("总销售量（未扣拒退）", nowTitle),
						tb_sales_soldmoney_index=ExcelUtil.getCol("总销售额（未扣满减未扣拒退）", nowTitle),
						tb_sales_people_index=ExcelUtil.getCol("购买人数", nowTitle),
						tb_sales_date_index=ExcelUtil.getCol("档期", nowTitle);

				// 循环行Row;getLastRowNum():获取总行数
				for (int rowNum = 1; rowNum <= xssfSheet.getLastRowNum(); rowNum++) {
					//获取每一行
					XSSFRow xssfRow = xssfSheet.getRow(rowNum);
					Map map = new HashMap<String, Object>();
					if (!(xssfRow.getCell(tb_sales_bar_no_index)==null||
							xssfRow.getCell(tb_sales_bar_no_index).equals("")||
							xssfRow.getCell(tb_sales_bar_no_index).getCellType() ==XSSFCell.CELL_TYPE_BLANK)) {
						System.out.println("循环当前行数："+rowNum);

						
						XSSFCell tb_sales_special_cell=xssfRow.getCell(tb_sales_special_index);
						XSSFCell tb_sales_bar_no_cell=xssfRow.getCell(tb_sales_bar_no_index);
						XSSFCell tb_sales_zone_cell=xssfRow.getCell(tb_sales_zone_index);
						XSSFCell tb_sales_num_cell=xssfRow.getCell(tb_sales_num_index);
						XSSFCell tb_sales_soldnum_cell=xssfRow.getCell(tb_sales_soldnum_index);
						XSSFCell tb_sales_soldmoney_cell=xssfRow.getCell(tb_sales_soldmoney_index);
						XSSFCell tb_sales_people_cell=xssfRow.getCell(tb_sales_people_index);
						XSSFCell tb_sales_date_cell=xssfRow.getCell(tb_sales_date_index);
						XSSFCell tb_sales_stock_cell=xssfRow.getCell(tb_sales_stock_index);

						//  使用工具吧cell强转String

						//  这里special是商品名称
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
