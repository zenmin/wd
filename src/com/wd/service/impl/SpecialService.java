package com.wd.service.impl;

import java.util.List;
import java.util.Set;

import com.wd.dao.ISpecialDAO;
import com.wd.models.TbSpecial;
import com.wd.service.ISpecialService;

public class SpecialService implements ISpecialService{

	private ISpecialDAO dao;
	
	@Override
	public List getSpecialDAO(int nowPage) {
		// TODO Auto-generated method stub
		return dao.getSpecialDAO(nowPage);
	}
	

	@Override
	public List getSpecialByid(Integer id) {
		// TODO Auto-generated method stub
		return dao.getSpecialByid(id);
	}
	
	public ISpecialDAO getDao() {
		return dao;
	}

	public void setDao(ISpecialDAO dao) {
		this.dao = dao;
	}


	@Override
	public TbSpecial addSpecial(TbSpecial ts) {
		// TODO Auto-generated method stub
		return dao.addSpecial(ts);
	}


	@Override
	public void updateSpecial(Integer id,String special,String specialName) {
		// TODO Auto-generated method stub
		dao.updateSpecial(id,special,specialName);
	}


	@Override
	public void deleteSpecial(Integer id) {
		dao.deleteSpecial(id);
	}


	@Override
	public List findSpecial(String specialName) {
		// TODO Auto-generated method stub
		return dao.findSpecial(specialName);
	}


	@Override
	public Long maxCount() {
		// TODO Auto-generated method stub
		return dao.maxCount();
	}


	@Override
	public Set getSpecialTitle() {
		// TODO Auto-generated method stub
		
		return dao.getSpecialTitle();
	}
	
}
