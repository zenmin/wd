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

				XSSFRow headRow = xssfSheet.getRow(0);
				//Bar���� ��index
				int barNo_cell_index = ExcelUtil.getcellValueIndex("������",headRow),
						barName_cell_index = ExcelUtil.getcellValueIndex("��Ʒ����", headRow),
						barColor_cell_index = ExcelUtil.getcellValueIndex("��ɫ", headRow),
						barMaterial_cell_index = ExcelUtil.getcellValueIndex("����", headRow),
						barSpecifications_cell_index = ExcelUtil.getcellValueIndex("���", headRow),
						barSimplename_cell_index = ExcelUtil.getcellValueIndex("���", headRow),
						barSaleprice_cell_index = ExcelUtil.getcellValueIndex("�ۼ�", headRow),
						barStandard_cell_index = ExcelUtil.getcellValueIndex("ִ�б�׼", headRow),
						barShowprice_cell_index = ExcelUtil.getcellValueIndex("���Ƽ�", headRow),
						remarks_cell_index = ExcelUtil.getcellValueIndex("��ע", headRow),
						scale_cell_index = ExcelUtil.getcellValueIndex("����", headRow),
						longs_cell_index = ExcelUtil.getcellValueIndex("��", headRow),
						widths_cell_index = ExcelUtil.getcellValueIndex("��", headRow),
						heigths_cell_index = ExcelUtil.getcellValueIndex("��", headRow),
						rapidWear_cell_index = ExcelUtil.getcellValueIndex("�Ƿ�����Ʒ", headRow),
						instructions_cell_index = ExcelUtil.getcellValueIndex("˵����", headRow),
						packNo_cell_index = ExcelUtil.getcellValueIndex("��װ����", headRow),
						packCondition_cell_index = ExcelUtil.getcellValueIndex("��װ���", headRow),
						packSize_cell_index = ExcelUtil.getcellValueIndex("��װ�ߴ�", headRow),
						isSize_cell_index = ExcelUtil.getcellValueIndex("����", headRow),
						tabs_cell_index = ExcelUtil.getcellValueIndex("���", headRow),
						terms_cell_index = ExcelUtil.getcellValueIndex("������", headRow),
						alias_cell_index = ExcelUtil.getcellValueIndex("����", headRow)
						;

				//Goods���е� index
				int goodsNo_cell_index = ExcelUtil.getcellValueIndex("ERP����", headRow),
						goodsPrice_cell_index = ExcelUtil.getcellValueIndex("�ɱ���", headRow),
						goodsOwner_cell_index = ExcelUtil.getcellValueIndex("�ɹ�Ա",headRow),
						class_cell_index =ExcelUtil.getcellValueIndex("���", headRow);


				// ѭ����Row;getLastRowNum():��ȡ������
				for (int rowNum = 1; rowNum <= xssfSheet.getLastRowNum(); rowNum++) {
					//��ȡÿһ��
					XSSFRow xssfRow = xssfSheet.getRow(rowNum);
					Map map = new HashMap<String, Object>();
					if (!(xssfRow.getCell(barNo_cell_index)==null||
							xssfRow.getCell(barNo_cell_index).equals("")||
							xssfRow.getCell(barNo_cell_index).getCellType() ==XSSFCell.CELL_TYPE_BLANK)) {
						System.out.println("ѭ����ǰ������"+rowNum);

						/**
						 * ��ÿ����Ԫ���л�ȡ Bar �����Ϣ
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
						
						//����
						tbBar.setBarNo(barNoValue);
						//                     	 Ʒ��
						tbBar.setBarName(barNameValue);
						//                     	 ����
						tbBar.setBarSimplename(barSimplenameValue);
						//                     	 ���ۼ�
						tbBar.setBarSaleprice(barSalepriceValue);
						
						tbBar.setBarShowprice(barShowpriceValue);
						//                     	 ��ɫ
						tbBar.setBarColor(barColorValue);
						tbBar.setBarMaterial(barMaterialValue);
						//                     	 ���
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
						 * ��ÿһ����Ԫ���л�ȡ goods��
						 */
						TbGoods tbGoods = new TbGoods();
						//����
						XSSFCell goodsNo = xssfRow.getCell(goodsNo_cell_index);
						//���
						XSSFCell class_ = xssfRow.getCell(class_cell_index);
						//�̶��ɱ���
						XSSFCell goodsPrice = xssfRow.getCell(goodsPrice_cell_index);
						//�ɹ�Ա��С�飩
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
