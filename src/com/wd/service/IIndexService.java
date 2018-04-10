package com.wd.service;

import java.util.List;

import com.wd.models.IndexCount;
import com.wd.models.TbBwl;
import com.wd.models.TbGg;

public interface IIndexService {
	public List checkUser(String username,String password);
	public List<TbBwl> getBwl();
	public int deleteBwl(Integer id);
	public int addBwl(String content,String time);
	public IndexCount showCount();
	public List<TbGg> getGg();
	public int addGg(String username,String content,String date);
	public int delGg(Integer id,String username);
}
