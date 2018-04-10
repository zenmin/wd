package com.wd.service;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.util.List;

import com.wd.models.TbReturns;

public interface IReturnsService {
	public List<TbReturns> findRturns(String startTime,String endTime,String barNo,String area,int nowPage);
	public String findRturnsCount(String startTime,String endTime,String barNo,String area,int nowPage);
	public int updateReturns(File file);
	public ByteArrayInputStream exportReturns(String startTime,String endTime,String barNo,String area);
}
