package com.wd.dao.impl;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.wd.dao.ISalesDAO;
import com.wd.dao.baseDAO;
import com.wd.models.TbBar;
import com.wd.models.TbSales;

public class SalesDAO extends baseDAO implements ISalesDAO{

	//  查询销售表
	@Override
	public List findSales(String startTime, String endTime, String barNo,String specialId,int nowPage,String barclass) {
		// TODO Auto-generated method stub
		List<TbSales> list = new ArrayList<TbSales>();
		List<Integer> class_  = new ArrayList<Integer>();
		List<String> barno = new ArrayList<String>() ;
		System.out.println("barclass:" + barclass);
		try {
			if(barclass.trim().equals("")==false) {
				class_ = getGoodsId(barclass);			// 查询出GoodsId
				
				for(int i=0;i<class_.size();i++) {			//  遍历goodsId
					barno.add(getBarNoByGoodsId(class_.get(i)));		// 根据goodsId查询出barNo
				}

//				System.out.println("barno:"+ barno);
				
				for (String bar : barno) {
					try {
						List<TbSales> sales1 = getSalesByClass(startTime, endTime, bar, specialId, nowPage, barclass);
						for (TbSales s : sales1) {
							TbSales sa = new TbSales();
							sa.setBarNo(s.getBarNo());
							sa.setDate(s.getDate());
							sa.setSalesId(s.getSalesId());
							sa.setSalesNum(s.getSalesNum());
							sa.setSalesPeople(s.getSalesPeople());
							sa.setSalesSoldmoney(s.getSalesSoldmoney());
							sa.setSalesSoldnum(s.getSalesSoldnum());
							sa.setSalesStock(s.getSalesStock());
							sa.setSpecial(s.getSpecial());
							sa.setZone(s.getZone());
							list.add(sa);
						}
					} catch (Exception e) {
						// TODO: handle exception
						continue;
					}
					
				}
				System.out.println(list);
				
//				System.out.println(barno);
				
			}else {
				list = getSalesByClass(startTime, endTime, barNo, specialId, nowPage, barclass);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return list;
	}

	/************* 根据分类查询*******************/

	//  1 先根据名称查出goodsId
	public List<Integer> getGoodsId(String name) {
		Session session = getSession();
		String sql = "SELECT goodsId FROM `tb_goods` WHERE class like '%" + name + "%'";

		Query q = session.createSQLQuery(sql);

		List<Integer> list = q.list();

		session.close();
		return list;
	}

	// 2 根据一个个id查询出一个个的条码封装List
	public String getBarNoByGoodsId(Integer id) {
		Session session = getSession();
		String sql = " select new TbBar(barNo) from TbBar WHERE goodsId = ?";
		Query q = session.createQuery(sql);
		q.setParameter(0, id);
		List<TbBar> list = q.list();
		String barno = "";
		for (TbBar tbBar : list) {
			barno = tbBar.getBarNo();
		}
		session.close();
		return barno;
	}

	// 3根据条码查出销售记录
	public List getSalesByClass(String startTime, String endTime, String barNo,String specialId,int nowPage,String barclass) {
		Session session = getSession();
		StringBuffer hql = new StringBuffer();
		hql.append("FROM TbSales WHERE 1=1 ");

		if(startTime.trim().equals("")==false||endTime.trim().equals("")==false) {
			hql.append(" AND date BETWEEN '" +startTime + "' AND '"  + endTime + "'");
		}
		if(barNo.trim().equals("")==false) {
			hql.append(" AND barNo='" + barNo + "'");
		}

		if(specialId.equals("all")!=true) {
			hql.append(" AND special='" + specialId+ "'");
		}
		//		System.out.println("HQL:" + hql);
		Query q = session.createQuery(hql.toString());
		int pageSize = 10;
		List list= q.setFirstResult((nowPage-1)*pageSize).setMaxResults(pageSize).list();
		System.out.println(list);			//  加这句  不然noSession  垃圾HIbernate
		session.clear();
		session.close();
		return list;
	}
	/*******************************/
	//  查询条数
	@Override
	public Long getSalesCount(String startTime, String endTime, String barNo,
			String specialId, int nowPage) {
		// TODO Auto-generated method stub
	
		Session session = getSession();
		StringBuffer hql = new StringBuffer();
		hql.append("select count(*) FROM TbSales WHERE 1=1 ");

		if(startTime.trim().equals("")==false||endTime.trim().equals("")==false) {
			hql.append(" AND date BETWEEN '" +startTime + "' AND '"  + endTime + "'");
		}
		if(barNo.trim().equals("")==false) {
			hql.append(" AND barNo='" + barNo + "'");
		}

		if(specialId.equals("all")!=true) {
			hql.append(" AND special='" + specialId+ "'");
		}
		//		System.out.println("HQL:" + hql);
		Query q = session.createQuery(hql.toString());
		List list= q.list();
		System.out.println(list);			//  加这句  不然noSession  垃圾HIbernate
		session.clear();
		session.close();
		return (Long) list.get(0);
	}

	//  更新销售表
	@Override
	public int updateSales(List<TbSales> list) {
		// TODO Auto-generated method stub
		Session session = getSession();
		Transaction tx = session.beginTransaction();

		if(list.size()==0) {
			return 0;
		}
		//		System.out.println("dao:" + list);
		for(TbSales l : list) {
			try {
				//  设置id  不然saveOrUpdate没jb用
				String id = checkUpdateOrInsert(l.getBarNo(),l.getDate(),l.getZone());
				if(!id.equals("")) {
					l.setSalesId(Long.valueOf(id));
				}

				session.saveOrUpdate(l);			//  存数据库

			} catch (Exception e) {
				//  若未成功update和save  肯定会抛出异常  直接返回0 rollback
				System.out.println("更新数据抛出异常！");
				e.printStackTrace();
				session.close();
				return 0;		
			} 
			session.clear();
		}

		tx.commit();
		session.close();
		return 1;
	}

	//  检查是更新还是新增
	@Override
	public String checkUpdateOrInsert(String barNo, String date,String zone) {
		// TODO Auto-generated method stub
		Session session = getSession();
		String hql  = "select new TbSales(salesId) from TbSales t WHERE t.barNo = ? AND  t.date =? AND t.zone = ?";
		Query q = session.createQuery(hql);
		q.setParameter(0, barNo);
		q.setParameter(1, date);
		q.setParameter(2, zone);
		List<TbSales> sid = q.list();
		StringBuffer id = new StringBuffer();
		for(TbSales s:sid) {
			id.append(s.getSalesId());
		}
		//		System.out.println("id.toString():" + id.toString());
		//  他妈的 你能不能记得关session 你这是在找死
		session.close();
		return id.toString();
	}

	//  查询销售和商品和类目表
	@Override
	public List findSalesAndBarAndGoods(String startTime,String endTime,String barNo,String specialId) {
		// TODO Auto-generated method stub
		Session session = getSession();
		StringBuffer hql = new StringBuffer();
		hql.append("FROM TbSales WHERE 1=1 ");

		if(startTime.trim().equals("")==false||endTime.trim().equals("")==false) {
			hql.append(" AND date BETWEEN '" +startTime + "' AND '"  + endTime + "'");
		}
		if(barNo.trim().equals("")==false) {
			hql.append(" AND barNo='" + barNo + "'");
		}
		if(specialId.equals("all")!=true) {
			hql.append(" AND special='" + specialId + "'");
		}
		Query q = session.createQuery(hql.toString());
		List list= q.list();

		//		System.out.println("findSalesAndBarAndGoods:" + list);			//  加这句  不然noSession  垃圾HIbernate
		session.close();
		return list;
	}

	

}
