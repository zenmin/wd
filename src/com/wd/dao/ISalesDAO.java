package com.wd.dao;

import java.math.BigInteger;
import java.util.List;

import com.wd.models.TbSales;

public interface ISalesDAO {
	public List findSales(String startTime,String endTime,String barNo,String specialId,int nowPage,String barclass);
	public List findSalesAndBarAndGoods(String startTime,String endTime,String barNo,String specialId);
	public int updateSales(List<TbSales> list);
	public String checkUpdateOrInsert(String barNo,String date,String zone);
	public Long getSalesCount(String startTime, String endTime, String barNo,String specialId,int nowPage);
}
