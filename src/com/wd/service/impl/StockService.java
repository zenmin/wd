package com.wd.service.impl;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.wd.dao.IStockDAO;
import com.wd.models.TbBar;
import com.wd.models.TbGoods;
import com.wd.models.TbSales;
import com.wd.models.TbStock;
import com.wd.service.IStockService;
import com.wd.utils.ExportSalesExcel;
import com.wd.utils.ExportStockExcel;
import com.wd.utils.ReadUpdateStocksExcel;

public class StockService implements IStockService{
	private IStockDAO dao;
	private ExportStockExcel exportSalesExcel;
	@Override
	public List<TbStock> findStock(String startTime, String endTime,
			String barNo, String goods, String zone, int nowPage) {
		// TODO Auto-generated method stub
		return dao.findStock(startTime, endTime, barNo, goods, zone, nowPage);
	}

	@Override
	public String findStockCount(String startTime, String endTime,
			String barNo, String goods, String zone, int nowPage) {
		// TODO Auto-generated method stub
		long i=  dao.findStockCount(startTime, endTime, barNo, goods, zone, nowPage);
		long j = i%10;
		long k = i/10;
		if(j==0) {
			
			return new DecimalFormat("#").format(k);
		}else {
			return new DecimalFormat("#").format(k+1);
		}
	}
	
	public IStockDAO getDao() {
		return dao;
	}

	public void setDao(IStockDAO dao) {
		this.dao = dao;
	}

	@Override
	public int updateStock(File file) {
		// TODO Auto-generated method stub
		ReadUpdateStocksExcel read = new ReadUpdateStocksExcel();
		List<TbStock> list  = new ArrayList<TbStock>();
		try {
			list = read.updateStock(file);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
		return dao.upateStock(list);
	}

	@Override
	public ByteArrayInputStream exportStock(String startTime, String endTime,
			String barNo, String goods, String zone) {
		// TODO Auto-generated method stub
		ByteArrayInputStream inputStock = null;
		List colList= new ArrayList();//用来封装Colmap的List
		
		List<TbStock> stockList =dao.findStock(startTime, endTime, barNo, goods, zone);
		System.out.println("Service:" + stockList);
		if(stockList.size() > 0){
			for(TbStock st:stockList){
				Map<String, Object> exportCol = new HashMap();
				exportCol.put("barNo", st.getBarNo());
				exportCol.put("goods", st.getGoods());
				exportCol.put("date", st.getDate());
				exportCol.put("barName", st.getBarName());
				exportCol.put("zone", st.getZone());
				exportCol.put("stock", st.getStock());
				colList.add(exportCol);
			}
//			System.out.println("colList:" + colList);
		}
		else{
			System.out.println("没有找到");
			return null;
		}
		//上面已经获取到用户所需导出的所有列
		//下面就调用ExportSalesExcel 类来创建一个Excel，然后响应到页面提供下载
		try {
			inputStock= exportSalesExcel.createExcel(colList);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("生成Excel时出现错误");
			e.printStackTrace();
			return null;
		}
		return inputStock;
	}

	public ExportStockExcel getExportSalesExcel() {
		return exportSalesExcel;
	}

	public void setExportSalesExcel(ExportStockExcel exportSalesExcel) {
		this.exportSalesExcel = exportSalesExcel;
	}


}
