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

public class ReadBarAndGoods {

	public List readXls(File excelFile) throws Exception {
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

				XSSFRow headRow = xssfSheet.getRow(0);
				//Bar表列 的index
				int barNo_cell_index = ExcelUtil.getcellValueIndex("条形码",headRow),
						barName_cell_index = ExcelUtil.getcellValueIndex("商品名称", headRow),
						barColor_cell_index = ExcelUtil.getcellValueIndex("颜色", headRow),
						barMaterial_cell_index = ExcelUtil.getcellValueIndex("材质", headRow),
						barSpecifications_cell_index = ExcelUtil.getcellValueIndex("规格", headRow),
						barSimplename_cell_index = ExcelUtil.getcellValueIndex("简称", headRow),
						barSaleprice_cell_index = ExcelUtil.getcellValueIndex("售价", headRow),
						barStandard_cell_index = ExcelUtil.getcellValueIndex("执行标准", headRow),
						barShowprice_cell_index = ExcelUtil.getcellValueIndex("吊牌价", headRow),
						remarks_cell_index = ExcelUtil.getcellValueIndex("备注", headRow),
						scale_cell_index = ExcelUtil.getcellValueIndex("重量", headRow),
						longs_cell_index = ExcelUtil.getcellValueIndex("长", headRow),
						widths_cell_index = ExcelUtil.getcellValueIndex("宽", headRow),
						heigths_cell_index = ExcelUtil.getcellValueIndex("高", headRow),
						rapidWear_cell_index = ExcelUtil.getcellValueIndex("是否易损品", headRow),
						instructions_cell_index = ExcelUtil.getcellValueIndex("说明书", headRow),
						packNo_cell_index = ExcelUtil.getcellValueIndex("包装货号", headRow),
						packCondition_cell_index = ExcelUtil.getcellValueIndex("包装情况", headRow),
						packSize_cell_index = ExcelUtil.getcellValueIndex("包装尺寸", headRow),
						isSize_cell_index = ExcelUtil.getcellValueIndex("区分", headRow),
						tabs_cell_index = ExcelUtil.getcellValueIndex("标记", headRow),
						terms_cell_index = ExcelUtil.getcellValueIndex("保质期", headRow),
						alias_cell_index = ExcelUtil.getcellValueIndex("别名", headRow)
						;

				//Goods表列的 index
				int goodsNo_cell_index = ExcelUtil.getcellValueIndex("ERP货号", headRow),
						goodsPrice_cell_index = ExcelUtil.getcellValueIndex("成本价", headRow),
						goodsOwner_cell_index = ExcelUtil.getcellValueIndex("采购员",headRow),
						class_cell_index =ExcelUtil.getcellValueIndex("类别", headRow);


				// 循环行Row;getLastRowNum():获取总行数
				for (int rowNum = 1; rowNum <= xssfSheet.getLastRowNum(); rowNum++) {
					//获取每一行
					XSSFRow xssfRow = xssfSheet.getRow(rowNum);
					Map map = new HashMap<String, Object>();
					if (!(xssfRow.getCell(barNo_cell_index)==null||
							xssfRow.getCell(barNo_cell_index).equals("")||
							xssfRow.getCell(barNo_cell_index).getCellType() ==XSSFCell.CELL_TYPE_BLANK)) {
						System.out.println("循环当前行数："+rowNum);

						/**
						 * 从每个单元格中获取 Bar 表的信息
						 */
						TbBar tbBar = new TbBar();
						XSSFCell barNo = xssfRow.getCell(barNo_cell_index);
						XSSFCell barName = xssfRow.getCell(barName_cell_index);
						XSSFCell barColor = xssfRow.getCell(barColor_cell_index);
						XSSFCell barMaterial= xssfRow.getCell(barMaterial_cell_index);
						XSSFCell barSpecifications = xssfRow.getCell(barSpecifications_cell_index);
						XSSFCell barSimplename = xssfRow.getCell(barSimplename_cell_index);
						XSSFCell barSaleprice = xssfRow.getCell(barSaleprice_cell_index);
						XSSFCell barStandard = xssfRow.getCell(barStandard_cell_index);
						XSSFCell barShowprice = xssfRow.getCell(barShowprice_cell_index);
						XSSFCell remarks = xssfRow.getCell(remarks_cell_index);
						XSSFCell scale = xssfRow.getCell(scale_cell_index);
						XSSFCell longs = xssfRow.getCell(longs_cell_index);
						XSSFCell widths = xssfRow.getCell(widths_cell_index);
						XSSFCell heigths = xssfRow.getCell(heigths_cell_index);
						XSSFCell rapidWear = xssfRow.getCell(rapidWear_cell_index);
						XSSFCell instructions = xssfRow.getCell(instructions_cell_index);
						XSSFCell packNo = xssfRow.getCell(packNo_cell_index);
						XSSFCell packCondition = xssfRow.getCell(packCondition_cell_index);
						XSSFCell packSize = xssfRow.getCell(packSize_cell_index);
						XSSFCell isSize = xssfRow.getCell(isSize_cell_index);
						XSSFCell tabs = xssfRow.getCell(tabs_cell_index);
						XSSFCell terms = xssfRow.getCell(terms_cell_index);
						XSSFCell alias = xssfRow.getCell(alias_cell_index);
						
						String barNoValue = (String)ExcelUtil.getValue(barNo);
						String barNameValue = (String) ExcelUtil.getValue(barName);
						String barSimplenameValue = (String) ExcelUtil.getValue(barSimplename);
						Double barSalepriceValue = Double.valueOf( ExcelUtil.getValue(barSaleprice));
						Double barShowpriceValue = Double.valueOf( ExcelUtil.getValue(barShowprice)); 
						String barColorValue = (String) ExcelUtil.getValue(barColor);
						String barMaterialValue  = (String) ExcelUtil.getValue(barMaterial);
						String barSpecificationsValue = (String) ExcelUtil.getValue(barSpecifications);
						String batStandard = (String) ExcelUtil.getValue(barStandard);
						String remarksValue = (String) ExcelUtil.getValue(remarks);
						Double scaleValue = Double.valueOf( ExcelUtil.getValue(scale));
						Double longsValue =  Double.valueOf( ExcelUtil.getValue(longs));
						Double widthsValue = Double.valueOf( ExcelUtil.getValue(widths));
						Double heigthsValue = Double.valueOf( ExcelUtil.getValue(heigths));
						String rapidWearValue =(String) ExcelUtil.getValue(rapidWear) ;
						String instructionsValue = (String) ExcelUtil.getValue(instructions);
						String packNoValue = (String) ExcelUtil.getValue(packNo);
						String packConditionValue = (String) ExcelUtil.getValue(packCondition);
						String packSizeValue = (String) ExcelUtil.getValue(packSize);
						String isSizeValue = (String) ExcelUtil.getValue(isSize);
						String tabsValue = (String) ExcelUtil.getValue(tabs);
						String termsValue = (String) ExcelUtil.getValue(terms);
						String aliasValue = (String) ExcelUtil.getValue(alias);
						
						//条码
						tbBar.setBarNo(barNoValue);
						//                     	 品名
						tbBar.setBarName(barNameValue);
						//                     	 别名
						tbBar.setBarSimplename(barSimplenameValue);
						//                     	 零售价
						tbBar.setBarSaleprice(barSalepriceValue);
						
						tbBar.setBarShowprice(barShowpriceValue);
						//                     	 颜色
						tbBar.setBarColor(barColorValue);
						tbBar.setBarMaterial(barMaterialValue);
						//                     	 规格
						tbBar.setBarSpecifications(barSpecificationsValue);
						tbBar.setBarStandard(batStandard);
						tbBar.setRemarks(remarksValue);
						tbBar.setScale(scaleValue);
						tbBar.setLongs(longsValue);
						tbBar.setWidths(widthsValue);
						tbBar.setHeights(heigthsValue);
						tbBar.setRapidWear(rapidWearValue);
						tbBar.setInstructions(instructionsValue);
						tbBar.setPackNo(packNoValue);
						tbBar.setPackCondition(packConditionValue);
						tbBar.setPackSize(packSizeValue);
						tbBar.setIsSize(isSizeValue);
						tbBar.setTabs(tabsValue);
						tbBar.setTerms(termsValue);
						tbBar.setAlias(aliasValue);
						
						map.put("tbBar", tbBar);

						/**
						 * 从每一个单元格中获取 goods表
						 */
						TbGoods tbGoods = new TbGoods();
						//货号
						XSSFCell goodsNo = xssfRow.getCell(goodsNo_cell_index);
						//类别
						XSSFCell class_ = xssfRow.getCell(class_cell_index);
						//固定成本价
						XSSFCell goodsPrice = xssfRow.getCell(goodsPrice_cell_index);
						//采购员（小组）
						XSSFCell goodsOwner = xssfRow.getCell(goodsOwner_cell_index);

						tbGoods.setGoodsNo((String) ExcelUtil.getValue(goodsNo));
						tbGoods.setClass_((String) ExcelUtil.getValue(class_));
						tbGoods.setGoodsPrice(Double.valueOf( ExcelUtil.getValue(goodsPrice)));
						tbGoods.setGoodsOwner((String) ExcelUtil.getValue(goodsOwner));
						map.put("tbGoods", tbGoods);

						list.add(map);
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
