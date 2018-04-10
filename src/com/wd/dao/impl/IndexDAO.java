package com.wd.dao.impl;

import java.util.List;













import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.wd.dao.IIndexDAO;
import com.wd.dao.baseDAO;
import com.wd.models.TbBwl;
import com.wd.models.TbGg;
import com.wd.models.TbUser;

public class IndexDAO extends baseDAO implements IIndexDAO {

	@Override
	public List checkUser(String username, String password) {
		// TODO Auto-generated method stub
		Session session = getSession();
		String hql = "from TbUser where userName = ? and userPassword = ?";
		Query q = session.createQuery(hql);
		q.setParameter(0, username);
		q.setParameter(1, password);
		List<TbUser> list = q.list();
		//		System.out.println(list);
		session.close();
		return list;
	}

	@Override
	public List<TbBwl> getBwl() {
		// TODO Auto-generated method stub
		Session session = getSession();
		String hql = "from TbBwl";
		Query q = session.createQuery(hql);
		List<TbBwl> list = q.list();
		session.close();
		return list;
	}

	// 删除备忘录
	@Override
	public int deleteBwl(Integer id) {
		// TODO Auto-generated method stub
		Session session = getSession();
		Transaction tx = session.beginTransaction();
		try {
			TbBwl bwl = (TbBwl) session.get(TbBwl.class, id);
			session.delete(bwl);
			tx.commit();
			session.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			tx.rollback();
			session.close();
			return 0;
		}
		return 1;
	}

	@Override
	public int addBwl(String content, String time) {
		// TODO Auto-generated method stub
		Session session = getSession();
		Transaction tx = session.beginTransaction();
		try {
			TbBwl bwl = new TbBwl(content, time);
			bwl.setContent(content);
			bwl.setTime(time);
			session.save(bwl);
			tx.commit();
			session.close();
			return 1;
		}catch (Exception e){
			e.printStackTrace();
			tx.rollback();
			session.close();

			return 0;
		}
	}

	@Override
	public String barCount() {
		Session session = getSession();
		String hql = "select count(*) from TbBar";
		Query q = session.createQuery(hql);
		List i =  q.list();
		//		System.out.println("商品数量：" + i);
		String result ="0";
		try {
			result = i.get(0).toString();
		} catch (Exception e) {
			// TODO: handle exception
			session.close();
			return "0";
		}
		session.close();
		return result;
	}
	@Override
	public String specialCount() {
		Session session = getSession();
		String hql = "SELECT COUNT(*) FROM TbSpecial";
		Query q = session.createQuery(hql);
		List i = q.list();
		//		System.out.println("专场数量：" + i);
		String result ="0";
		try {
			result= i.get(0).toString();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			session.close();
			return "0";
		}
	
		session.close();
		return result;
	}
	@Override
	public String stockCount() {
		Session session = getSession();
		String hql = "SELECT SUM(stock) FROM TbStock";
		Query q = session.createQuery(hql);
		List i =q.list();
		//		System.out.println("库存总量：" + i);
	
		String result ;
		try {
			result	= i.get(0).toString();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			session.close();
			return "0";
		}
		session.close();
		return result;
	}
	@Override
	public String salesCount() {
		Session session = getSession();
		String hql = "SELECT SUM(salesSoldmoney) FROM TbSales";
		Query q = session.createQuery(hql);
		List i = q.list();
		//		System.out.println("销售额：" + i);
		String result ="0";
		try {
			result = i.get(0).toString();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			session.close();
			return "0";
		}
		
		session.close();
		return result;
	}

	@Override
	public List getGg() {
		// TODO Auto-generated method stub
		Session session = getSession();
		String hql ="from TbGg order by id desc";
		Query q = session.createQuery(hql);
		List<TbGg> list = q.list();
		session.close();
		return list;
	}

	@Override
	public int addGg(String username, String content, String date) {
		// TODO Auto-generated method stub
		Session session = getSession();
		Transaction tx = session.beginTransaction();
		try {
			TbGg g = new TbGg();
			g.setUsername(username);
			g.setDate(date);
			g.setContent(content);
			session.save(g);
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

	@Override
	public int delGg(Integer id,String username) {
		// TODO Auto-generated method stub
		Session session = getSession();

		if(checkPower(username).trim().equals("1"))
		{
			Transaction tx = session.beginTransaction();

			try {
				TbGg g = (TbGg) session.get(TbGg.class, id);
				session.delete(g);
				tx.commit();
				return 1;
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				tx.rollback();
				session.close();
				return 0;
			}
		}else {
			return 0;
		}
	}

	public String checkPower(String username) {
		System.out.println(username);
		Session session = getSession();
		String hql ="SELECT POWER FROM `tb_user` WHERE userName = ?";
		Query q= session.createSQLQuery(hql);
		q.setString(0, username);
		List p = q.list();
		System.out.println(String.valueOf(p.get(0)));
		session.close();
		return String.valueOf(p.get(0));
	}
}
