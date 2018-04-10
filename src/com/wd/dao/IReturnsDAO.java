package com.wd.dao;

import java.io.ByteArrayInputStream;
import java.util.List;

import com.wd.models.TbReturns;

public interface IReturnsDAO {
	public List<TbReturns> findRturns(String startTime,String endTime,String barNo,String area,int nowPage);
	public Long findRturnsCount(String startTime,String endTime,String barNo,String area,int nowPage);
	public int updateReturns(List<TbReturns> list);
	public String checkUpdateOrInsert(String barNo, String date);
	public List<TbReturns> exportReturns(String startTime,String endTime,String barNo,String area);
}
