package com.wd.test;


import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.wd.models.TbBar;
import com.wd.models.TbSales;

public class GoodsTest{
	private Session session;
	private SessionFactory sessionFactory;
	private Transaction transaction;
	
	@Test
	public void test1() {
			//  获取SessionFactory
			BeanFactory context = new ClassPathXmlApplicationContext("applicationContext.xml");
			sessionFactory = context.getBean(SessionFactory.class);
			
			session = sessionFactory.openSession();
			//  开启事务
			transaction = session.beginTransaction();
			
			String sql = " select new TbBar(barNo) from TbBar WHERE goodsId = 20667";
			
			Query q = session.createQuery(sql);
			
			List<TbBar> list = q.list();
			for (TbBar tbBar : list) {
				System.out.println(tbBar.getBarNo());
			}

			session.close();
		
	}
	
	@Test
	public void getGoodsId() {
			BeanFactory context = new ClassPathXmlApplicationContext("applicationContext.xml");
			sessionFactory = context.getBean(SessionFactory.class);
			
			session = sessionFactory.openSession();
			//  开启事务
			transaction = session.beginTransaction();
			
			String sql = "SELECT goodsId FROM `tb_goods` WHERE class like '%瑜伽用品%'";
			
			Query q = session.createSQLQuery(sql);
			
			List<Integer> list = q.list();
			for (Integer tbBar : list) {
				System.out.println(tbBar);
			}
			session.close();
	}
	
	@Test
	public void getSalesByClass() {
		BeanFactory context = new ClassPathXmlApplicationContext("applicationContext.xml");
		sessionFactory = context.getBean(SessionFactory.class);
		
		session = sessionFactory.openSession();
		//  开启事务
		transaction = session.beginTransaction();
		
		String sql = "from TbSales where barNo = 6917042200260";
		
		Query q = session.createQuery(sql);
		
		List<TbSales> list = q.list();
		for (TbSales s : list) {
			System.out.println(s);
		}
		session.close();
	}
	
	@Test
	public void getBarNoByGoodsId() {
		BeanFactory context = new ClassPathXmlApplicationContext("applicationContext.xml");
		sessionFactory = context.getBean(SessionFactory.class);
		
		session = sessionFactory.openSession();
		String sql = " select new TbBar(barNo) from TbBar WHERE goodsId = ?";
		Query q = session.createQuery(sql);
		q.setParameter(0, "20668");
		List<TbBar> list = q.list();
		String barno = "";
		for (TbBar tbBar : list) {
			barno = tbBar.getBarNo();
		}
		session.close();
		
		System.out.println(barno);
		
	}

}


	
