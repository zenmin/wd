package com.wd.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.wd.dao.IShowGoodsDAO;
import com.wd.dao.baseDAO;
import com.wd.models.TbGoods;
import com.wd.utils.GoodsUtils;
import com.wd.utils.Page;

public class ShowGoodsDAO extends baseDAO implements IShowGoodsDAO {

	//��ѯ���м�¼
	public List<TbGoods> findAll(Page page) {
		Session session = getSession();
		String hql = "FROM TbGoods";
		Query query = session.createQuery(hql);
		query.setMaxResults(10);
		query.setFirstResult((int) ((int) (page.getCurrentPage()-1) * 10));
		List<TbGoods> tbGoods = query.list();
		if(tbGoods.size() != 0){
			session.close();
			return GoodsUtils.getSplit(tbGoods);
		}
		session.close();
		return null;
	}
	//����ID��ѯ
	public TbGoods findById(Integer id) {
		Session session = getSession();

		TbGoods tbGoods = (TbGoods) session.get(TbGoods.class, id);

		session.close();
		return tbGoods;
	}	
	//�������Ʋ�ѯ��Ŀ
	public List<TbGoods> findByName(String name, Page page){
		Session session = getSession();
		String hql = "FROM TbGoods g WHERE g.class_ like '%"+name+"%'";
		Query query = session.createQuery(hql);
		query.setFirstResult((int) ((int) (page.getCurrentPage()-1) * 10));
		query.setMaxResults(10);
		List<TbGoods> tbGoods = query.list();
		if(tbGoods.size() != 0){
			session.close();
			return tbGoods;
		}
		session.close();
		return null;
	}

	//���
	public void addGoods(TbGoods tbGoods) {
		Session session = getSession();
		Transaction tx = session.beginTransaction();
		try {
			session.save(tbGoods);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			session.close();
		}
		tx.commit();
		session.close();
	}

	//�޸�
	public int alterGoods(Integer id, String name) {
		Session session  = getSession();
		Transaction tx= session.beginTransaction();
		try {
			TbGoods tbGoods = (TbGoods) session.get(TbGoods.class, id);
			tbGoods.setClass_(name);
			session.save(tbGoods);
			tx.commit();
			return 1;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			
			return 0;
		}finally {
			session.close();
		}
	}
	//ɾ��
	public int deleteGoods(Integer id) {
		Session session  = getSession();
		Transaction tx= session.beginTransaction();
		try {
			TbGoods t = (TbGoods) session.get(TbGoods.class, id);
			System.out.println(t);
			session.delete(t);
			tx.commit();
			session.close();
			return 1;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			session.close();
			return 0;
		}

	}	//��ȡ������
	public long AlltotalSize(){
		Session session = getSession();
		String hql = "SELECT COUNT(*) FROM TbGoods";
		Query query = session.createQuery(hql);
		List totalSizes = query.list();
		System.out.println(totalSizes);
		session.close();
		return (Long) totalSizes.get(0);
	}

	//��ȡ��ѯname������
	public long totalSizeByName(String name){
		Session session = getSession();
		String hql = "SELECT COUNT(*) FROM TbGoods g WHERE g.class_ like '%"+name+"%' ";
		Query query = session.createQuery(hql);
		List totalSizes = query.list();
		session.close();
		return (Long) totalSizes.get(0);
	}
}
