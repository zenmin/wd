package com.wd.service.impl;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.wd.dao.IReturnsDAO;
import com.wd.models.TbReturns;
import com.wd.service.IReturnsService;
import com.wd.utils.ExportReturnExcel;
import com.wd.utils.ReadUpdateReturnsExcel;

public class ReturnsService implements IReturnsService{
	
	private IReturnsDAO dao;
	private ExportReturnExcel exportExcel;
	@Override
	public List findRturns(String startTime, String endTime, String barNo,
			String area, int nowPage) {
		// TODO Auto-generated method stub
		return dao.findRturns(startTime, endTime, barNo, area, nowPage);
	}
	// ��ѯ����
	@Override
	public String findRturnsCount(String startTime, String endTime,
			String barNo, String area, int nowPage) {
		// TODO Auto-generated method stub
		long i=  dao.findRturnsCount(startTime, endTime, barNo, area, nowPage);
		long j = i%10;
		long k = i/10;
		if(j==0) {
			
			return new DecimalFormat("#").format(k);
		}else {
			return new DecimalFormat("#").format(k+1);
		}
	}
	@Override
	public int updateReturns(File file) {
		// TODO Auto-generated method stub
		ReadUpdateReturnsExcel read = new ReadUpdateReturnsExcel();
		List<TbReturns> list  = new ArrayList<TbReturns>();
		try {
			list = read.updateReturns(file);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
		return dao.updateReturns(list);
	}
	
	@Override
	public ByteArrayInputStream exportReturns(String startTime,String endTime,String barNo,String area) {
		// TODO Auto-generated method stub
		ByteArrayInputStream inputStock = null;
		List colList= new ArrayList();//������װColmap��List
		
		List<TbReturns> rt =dao.exportReturns(startTime, endTime, barNo, area);
		if(rt.size() > 0){
			for(TbReturns r : rt){
				Map<String, Object> exportCol = new HashMap();
				exportCol.put("barNo", r.getBarNo());
				exportCol.put("goods", r.getGoodsNo());
				exportCol.put("reason", r.getReturnsReason());
				exportCol.put("date", r.getDate());
				exportCol.put("num", r.getReturnsReturnnum());
				exportCol.put("jecnum", r.getReturnsRejectnum());
				colList.add(exportCol);
			}
//			System.out.println("colList:" + colList);
		}
		else{
			System.out.println("û���ҵ�");
			return null;
		}
		//�����Ѿ���ȡ���û����赼����������
		//����͵���ExportSalesExcel ��������һ��Excel��Ȼ����Ӧ��ҳ���ṩ����
		try {
			inputStock= exportExcel.createExcel(colList);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("����Excelʱ���ִ���");
			e.printStackTrace();
			return null;
		}
		return inputStock;
	}
	
	public IReturnsDAO getDao() {
		return dao;
	}

	public void setDao(IReturnsDAO dao) {
		this.dao = dao;
	}

	public ExportReturnExcel getExportExcel() {
		return exportExcel;
	}

	public void setExportExcel(ExportReturnExcel exportExcel) {
		this.exportExcel = exportExcel;
	}

	
}
