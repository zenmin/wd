package com.wd.service.impl;

import java.util.List;

import com.wd.dao.IUserDAO;
import com.wd.service.IUserService;

public class UserService implements IUserService{

	private IUserDAO dao;
	
	@Override
	public int alterUser(String username, String password, String password1) {
		// TODO Auto-generated method stub
		return dao.alterUser(username, password,password1);
	}
	
	public IUserDAO getDao() {
		return dao;
	}
	public void setDao(IUserDAO dao) {
		this.dao = dao;
	}

	@Override
	public List getAllUser(String finduser,int nowPage) {
		// TODO Auto-generated method stub
		return dao.getAllUser(finduser,nowPage);
	}

	@Override
	public int addUser(String username, String passowrd, String power) {
		// TODO Auto-generated method stub
		return dao.addUser(username, passowrd, power);
	}

	@Override
	public int delUser(Integer id) {
		// TODO Auto-generated method stub
		return dao.delUser(id);
	}

	@Override
	public int delData(String table) {
		// TODO Auto-generated method stub
		return dao.delData(table);
	}


	
}
