package com.wd.dao.impl;

import java.util.List;





import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.wd.dao.IUserDAO;
import com.wd.dao.baseDAO;
import com.wd.models.TbUser;

public class UserDAO extends baseDAO implements IUserDAO{

	@Override
	public int alterUser(String username,String password,String password1) {
		// TODO Auto-generated method stub
		Session session = getSession();
		if(checkUser(username, password1)==1)
		{

			String hql = "update TbUser t set t.userPassword = ? where t.userName = ?";
			Query q = session.createQuery(hql);
			q.setString(0, password);
			q.setString(1, username);
			int i = q.executeUpdate();
			session.close();
			return i;
		}
		if(checkUser(username, password1)==0)
		{
			session.close();
			return 2;
		}
		return 0;
	}
	//  ºÏ≤È‘≠√‹¬Î
	public int checkUser(String username,String password1) {
		Session session = getSession();
		String hql = "from TbUser t where t.userName = ? and t.userPassword = ? ";
		Query q = session.createQuery(hql);
		q.setParameter(0, username);
		q.setParameter(1, password1);
		List list = q.list();
		session.close();
		return list.size();
	}
	@Override
	public List getAllUser(String finduser,int nowPage) {
		// TODO Auto-generated method stub
		Session session = getSession();
		StringBuffer hql = new StringBuffer();
		hql.append("from TbUser ");
		if(finduser.trim().equals("")==false) {
			hql.append(" where userName like '%" + finduser + "%'");
		}
		Query q = session.createQuery(hql.toString());
		List list= q.setFirstResult((nowPage-1)*10).setMaxResults(10).list();
		session.close();
		return list;
	}
	@Override
	public int addUser(String username, String passowrd, String power) {
		// TODO Auto-generated method stub
		Session session = getSession();
		Transaction tx = session.beginTransaction();

		try {
			TbUser user = new TbUser();
			user.setUserName(username);
			user.setUserPassword(passowrd);
			user.setPower(Integer.parseInt(power));
			session.save(user);
			tx.commit();
			return 1;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			tx.rollback();
			return 0;
		}finally {
			session.close();
		}
	}
	@Override
	public int delUser(Integer id) {
		// TODO Auto-generated method stub
		Session session = getSession();
		Transaction tx = session.beginTransaction();
		try {
			TbUser user = (TbUser) session.get(TbUser.class, id);
			session.delete(user);
			tx.commit();
			return 1;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			tx.rollback();
			return 0;
		}finally {
			session.close();
		}
	}
	@Override
	public int delData(String table) {
		// TODO Auto-generated method stub
		Session session = getSession();
		String sql = "";
		Transaction tx = session.beginTransaction();
		if(table.equals("tb_goods")) {
			sql = "DELETE FROM `tb_goods`";
		}else {
			sql = "TRUNCATE TABLE " + table + ";";
		}
		
		Query q = session.createSQLQuery(sql);
		try {
			 q.executeUpdate();
			 tx.commit();
			 session.close();
			 return 1;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			tx.rollback();
			session.close();
			return 0;
		}
		
	}
}
