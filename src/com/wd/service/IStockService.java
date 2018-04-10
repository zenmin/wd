package com.wd.service;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.util.List;

import com.wd.models.TbStock;

public interface IStockService {
	public List findStock(String startTime,String endTime,String barNo,String goods,String zone,int nowPage);
	public String findStockCount(String startTime,String endTime,String barNo,String goods,String zone,int nowPage);
	public int updateStock(File file);
	public ByteArrayInputStream exportStock(String startTime,String endTime,String barNo,String goods,String zone);
	
}
