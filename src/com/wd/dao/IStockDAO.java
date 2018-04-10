package com.wd.dao;

import java.util.List;

import com.wd.models.TbStock;


public interface IStockDAO {
	public List<TbStock> findStock(String startTime,String endTime,String barNo,String goods,String zone,int nowPage);
	public Long findStockCount(String startTime,String endTime,String barNo,String goods,String zone,int nowPage);
	public int upateStock(List<TbStock> list);
	public String checkUpdateOrInsert(String barNo, String goods,String date);
	public List<TbStock> findStock(String startTime,String endTime,String barNo,String goods,String zone);
}
