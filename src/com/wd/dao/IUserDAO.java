package com.wd.dao;

import java.util.List;

public interface IUserDAO {
	public int alterUser(String username,String password,String password1);
	public List getAllUser(String finduser,int nowPage);
	public int addUser(String username,String passowrd,String power);
	public int delUser(Integer id);
	public int delData(String table);
}
