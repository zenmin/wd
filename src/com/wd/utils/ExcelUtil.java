package com.wd.utils;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;

import com.wd.models.TbSales;

public class ExcelUtil {
	
	public static int getCol(String string, XSSFRow nowTitle) {
		// TODO Auto-generated method stub
		int n =nowTitle.getPhysicalNumberOfCells();
		for(int i=0;i<n;i++){
			if(string.equals(nowTitle.getCell(i).getStringCellValue())){
				return i;
			}
		}
		return 0;
	}
	
	public static Object getCellValue(XSSFCell cell){
		if(cell.getCellType()==cell.CELL_TYPE_NUMERIC){
			return cell.getNumericCellValue();
		}else{
			return cell.getStringCellValue();
		}
		
	}
	/**
     * @param cell
     * @return
     */
    public static String getStringValueFromCell(Cell cell) {
        SimpleDateFormat sFormat = new SimpleDateFormat("yyyy-MM-dd");
        DecimalFormat decimalFormat = new DecimalFormat("#.#");
        String cellValue = "";
        if(cell == null) {
            return cellValue;
        }
        else if(cell.getCellType() == Cell.CELL_TYPE_STRING) {
            cellValue = cell.getStringCellValue();
        }

        else if(cell.getCellType() == XSSFCell.CELL_TYPE_NUMERIC) {
            if(HSSFDateUtil.isCellDateFormatted(cell)) {
                double d = cell.getNumericCellValue();
                Date date = HSSFDateUtil.getJavaDate(d);
                cellValue = sFormat.format(date);
            }
            else {                
                cellValue = decimalFormat.format((cell.getNumericCellValue()));
            }
        }
        else if(cell.getCellType() == Cell.CELL_TYPE_BLANK) {
            cellValue = "";
        }
        else if(cell.getCellType() == Cell.CELL_TYPE_BOOLEAN) {
            cellValue = String.valueOf(cell.getBooleanCellValue());
        }
        else if(cell.getCellType() == Cell.CELL_TYPE_ERROR) {
            cellValue = "";
        }
        else if(cell.getCellType() == Cell.CELL_TYPE_FORMULA) {
            cellValue = cell.getCellFormula().toString();
        }
        return cellValue;
    }
	public static int getcellValueIndex(String value, XSSFRow headRow){
		//循环遍历 headRow 行
		for(int i=0;i<headRow.getLastCellNum();i++){
			//判断那一列是value值，然后返回当前列
			if(value.equals(headRow.getCell(i).getStringCellValue().trim())){
				return i;
			}
		}
		return -1;
	}
		//数据类型装换
	   public static String getValue(XSSFCell xssfCell) {
		   if(xssfCell == null){
			   return null;
		   }else
	        if (xssfCell.getCellType() == xssfCell.CELL_TYPE_BOOLEAN) {
	            // 返回布尔类型的值
			    return String.valueOf(xssfCell.getBooleanCellValue());
			} else if (xssfCell.getCellType() == xssfCell.CELL_TYPE_NUMERIC) {
			    // 返回数值类型的值
			    return String.valueOf(xssfCell.getNumericCellValue());
			} else {
			    // 返回字符串类型的值
			        return String.valueOf(xssfCell.getStringCellValue());
			    }
		}
}
