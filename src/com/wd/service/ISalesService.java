package com.wd.service;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.util.List;

import com.wd.models.TbSales;

public interface ISalesService {
	public List findSales(String startTime,String endTime,String barNo,String specialId,int nowPage,String barclass);
	public int updateSales(File file);
	public ByteArrayInputStream findSalesAndBarAndGoods(String startTime, String endTime, String barNo,
			String specialId,int[] checkCondition);
	public String getSalesCount(String startTime, String endTime, String barNo,String specialId,int nowPage);
}
