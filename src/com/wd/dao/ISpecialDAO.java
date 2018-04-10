package com.wd.dao;

import java.util.List;
import java.util.Set;

import com.wd.models.TbSpecial;

public interface ISpecialDAO {
	public List getSpecialDAO(int nowPage);
	public  List getSpecialByid(Integer id);
	public TbSpecial addSpecial(TbSpecial ts);
	public void updateSpecial(Integer id,String special,String specialName);
	public void deleteSpecial(Integer id);
	public List findSpecial(String specialName);
	public Long maxCount();
	public Set getSpecialTitle();
}
