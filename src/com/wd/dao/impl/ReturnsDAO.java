package com.wd.dao.impl;

import java.io.ByteArrayInputStream;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.wd.dao.IReturnsDAO;
import com.wd.dao.baseDAO;
import com.wd.models.TbReturns;
import com.wd.models.TbSales;

public class ReturnsDAO extends baseDAO implements IReturnsDAO{

	@Override
	public List findRturns(String startTime, String endTime, String barNo,
			String area, int nowPage) {
		// TODO Auto-generated method stub
		Session session = getSession();
		StringBuffer hql = new StringBuffer();
		hql.append("from TbReturns t where 1=1");

		//  如果选择的不是全部地区
		if(startTime.trim().equals("")==false||endTime.trim().equals("")==false) {
			hql.append(" AND t.date BETWEEN '"+ startTime+"' AND '" + endTime+ "' ");
		}
		if(area.equals("all")==false) {
			hql.append(" AND t.returnsReason='" + area.trim() + "'");
		}
		if(barNo.equals("")==false) {
			hql.append(" AND barNo=" + barNo);
		}
		
		Query q = session.createQuery(hql.toString());

		List list= q.setFirstResult((nowPage-1)*10).setMaxResults(10).list();
		session.close();
		return list;
	}
	//  查条数
	@Override
	public Long findRturnsCount(String startTime, String endTime,
			String barNo, String area, int nowPage) {
		// TODO Auto-generated method stub
		Session session = getSession();
		StringBuffer hql = new StringBuffer();
		hql.append("select count(*) from TbReturns t where 1=1");

		//  如果选择的不是全部地区
		if(startTime.trim().equals("")==false||endTime.trim().equals("")==false) {
			hql.append(" AND t.date BETWEEN '"+ startTime+"' AND '" + endTime+ "' ");
		}
		if(area.equals("all")==false) {
			hql.append(" AND t.returnsReason='" + area.trim() + "'");
		}
		if(barNo.equals("")==false) {
			hql.append(" AND barNo=" + barNo);
		}
		
		Query q = session.createQuery(hql.toString());

		List list= q.list();
		session.close();
		return (Long) list.get(0);
	}
	//  更新客退资料
	@Override
	public int updateReturns(List<TbReturns> list) {
		// TODO Auto-generated method stub
		Session session = getSession();
		Transaction tx = session.beginTransaction();
		try {
			for(TbReturns l : list) {
//				String id = checkUpdateOrInsert(l.getBarNo(),l.getDate());
//				if(!id.equals("")) {
//					l.setReturnsId(Long.valueOf(id));
//				}
				session.save(l);
				session.clear();
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			System.out.println("更新失败！");
			tx.rollback();
			session.clear();
			session.close();
			return 0;
		}

		tx.commit();
		session.close();
		return 1;
	}

		//  查询有没有同条码同时间的记录  有就更新  没有就插入
	@Override
	public String checkUpdateOrInsert(String barNo, String date) {
		// TODO Auto-generated method stub
		Session session = getSession();
		String hql  = "select new TbReturns(returnsId) from TbReturns t WHERE t.barNo = ? AND t.date = ?";
		Query q = session.createQuery(hql);
		q.setParameter(0, barNo);
		q.setParameter(1, date);
		List<TbReturns> sid = q.list();
		StringBuffer id = new StringBuffer();
		for(TbReturns s:sid) {
			id.append(s.getReturnsId());
		}
		System.out.println("id.toString():" + id.toString());
		session.close();
		return id.toString();
	}

	@Override
	public List<TbReturns> exportReturns(String startTime, String endTime,
			String barNo, String area) {
		// TODO Auto-generated method stub
		Session session = getSession();
		StringBuffer hql = new StringBuffer();
		hql.append("from TbReturns t where 1=1");

		//  如果选择的不是全部地区
		if(startTime.trim().equals("")==false||endTime.trim().equals("")==false) {
			hql.append(" AND t.date BETWEEN '"+ startTime+"' AND '" + endTime+ "' ");
		}
		if(area.equals("all")==false) {
			hql.append(" AND t.area='" + area + "'");
		}
		if(barNo.equals("")==false) {
			hql.append(" AND barNo=" + barNo);
		}
		
		Query q = session.createQuery(hql.toString());

		List list= q.list();
		session.close();
		return list;
	}

	
}
