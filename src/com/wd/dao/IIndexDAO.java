package com.wd.dao;

import java.util.List;

import com.wd.models.TbBwl;
import com.wd.models.TbGg;

public interface IIndexDAO {
	public List checkUser(String username,String password);
	public List<TbBwl> getBwl();
	public int deleteBwl(Integer id);
	public int addBwl(String content,String time);
	public String barCount();
	public String specialCount();
	public String stockCount();
	public String salesCount() ;
	public List<TbGg> getGg();
	public int addGg(String username,String content,String date);
	public int delGg(Integer id,String username);
}
