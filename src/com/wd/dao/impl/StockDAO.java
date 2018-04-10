package com.wd.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.wd.dao.IStockDAO;
import com.wd.dao.baseDAO;
import com.wd.models.TbReturns;
import com.wd.models.TbStock;

public class StockDAO extends baseDAO implements IStockDAO {

	@Override
	public List<TbStock> findStock(String startTime, String endTime,
			String barNo,String goods, String zone, int nowPage) {
		// TODO Auto-generated method stub
		Session session = getSession();
		StringBuffer hql = new StringBuffer();
		//  1=1  用来放where语句
		hql.append("from TbStock t  where 1=1 ");

		//  如果选择的不是全部地区
		if(zone.equals("all")==false) {
			hql.append(" AND zone = '" + zone + "'");
		}
		if(barNo.equals("")==false) {
			hql.append(" AND t.barNo='" + barNo +"'");
		}
		if(goods.equals("")==false) {
			hql.append(" AND t.goods='" + goods +"'");
		}

		if((startTime.equals("")||endTime.equals(""))==false) {
			hql.append(" AND t.date BETWEEN '" + startTime + "' AND '"+ endTime + "'");
		}
		//		System.out.println("HQL:" + hql);
		Query q = session.createQuery(hql.toString());

		List list= q.setFirstResult((nowPage-1)*10).setMaxResults(10).list();
		//		System.out.println(list);

		session.close();
		return list;
	}
	//查条数
	@Override
	public Long findStockCount(String startTime, String endTime,
			String barNo, String goods, String zone, int nowPage) {
		// TODO Auto-generated method stub
		Session session = getSession();
		StringBuffer hql = new StringBuffer();
		//  1=1  用来放where语句
		hql.append("select count(*) from TbStock t  where 1=1 ");

		//  如果选择的不是全部地区
		if(zone.equals("all")==false) {
			hql.append(" AND zone = '" + zone + "'");
		}
		if(barNo.equals("")==false) {
			hql.append(" AND t.barNo='" + barNo +"'");
		}
		if(goods.equals("")==false) {
			hql.append(" AND t.goods='" + goods +"'");
		}

		if((startTime.equals("")||endTime.equals(""))==false) {
			hql.append(" AND t.date BETWEEN '" + startTime + "' AND '"+ endTime + "'");
		}
		//		System.out.println("HQL:" + hql);
		Query q = session.createQuery(hql.toString());

		List list= q.list();
		//		System.out.println(list);

		session.close();
		return (Long) list.get(0);
	}
	//  更新库存
	@Override
	public int upateStock(List<TbStock> list) {
		// TODO Auto-generated method stub
		Session session = getSession();
		Transaction tx = session.beginTransaction();
		try {
			for(TbStock l : list) {
				String id = checkUpdateOrInsert(l.getBarNo(),l.getZone(),l.getDate());
				if(!id.equals("")) {
					l.setStockId(Integer.valueOf(id));
				}
				session.saveOrUpdate(l);
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
		session.clear();
		session.close();
		return 1;
	}

	@Override
	public String checkUpdateOrInsert(String barNo, String zone,String date) {
		// TODO Auto-generated method stub
		Session session = getSession();
		String hql  = "select new TbStock(stockId) from TbStock t WHERE t.barNo = ? AND t.zone = ? AND t.date = ?";
		Query q = session.createQuery(hql);
		q.setParameter(0, barNo);
		q.setParameter(1, zone);
		q.setParameter(2, date);
		List<TbStock> sid = q.list();
		StringBuffer id = new StringBuffer();
		for(TbStock s:sid) {
			id.append(s.getStockId());
		}
		System.out.println("id.toString():" + id.toString());
		session.close();
		return id.toString();
	}

	//  导出
	@Override
	public List<TbStock> findStock(String startTime, String endTime,
			String barNo, String goods, String zone) {
		// TODO Auto-generated method stub
		Session session = getSession();
		StringBuffer hql = new StringBuffer();
		//  1=1  用来放where语句
		hql.append("from TbStock t  where 1=1 ");

		//  如果选择的不是全部地区
		if(zone.equals("all")==false) {
			hql.append(" AND zone = '" + zone + "'");
		}
		if(barNo.equals("")==false) {
			hql.append(" AND t.barNo='" + barNo +"'");
		}
		if(goods.equals("")==false) {
			hql.append(" AND t.goods='" + goods +"'");
		}

		if((startTime.equals("")||endTime.equals(""))==false) {
			hql.append(" AND t.date BETWEEN '" + startTime + "' AND '"+ endTime + "'");
		}
		//		System.out.println("HQL:" + hql);
		Query q = session.createQuery(hql.toString());

		List list= q.list();
		//		System.out.println(list);

		session.close();
		return list;
	}


}
