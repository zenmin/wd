package com.wd.dao.impl;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.wd.dao.ISpecialDAO;
import com.wd.dao.baseDAO;
import com.wd.models.TbSpecial;

public class SpecialDAO extends baseDAO implements ISpecialDAO{

	
	@Override
	public List getSpecialDAO(int nowPage) {
		// TODO Auto-generated method stub
		Session session = getSession();
		String hql = "from TbSpecial ts ";
		Query q = session.createQuery(hql);
		
		int pageSize = 10;			//  一页显示的数量
		List special  = q.setFirstResult((nowPage-1)*pageSize).setMaxResults(pageSize).list();
		session.close();
		return special;
	}

	@Override
	public List getSpecialByid(Integer id) {
		// TODO Auto-generated method stub
		Session session = getSession();
		String hql = "from TbSpecial ts where specialId = ?";
		Query q = session.createQuery(hql);
		q.setParameter(0, id);
		List special =  q.list();
		session.close();
		return special;
	}

	@Override
	public TbSpecial addSpecial(TbSpecial ts) {
		// TODO Auto-generated method stub
		Session session = getSession();
		Transaction tx = session.beginTransaction();
		session.save(ts);
		tx.commit();
		session.close();
		return ts;
		
	}

	@Override
	public void updateSpecial(Integer id,String special,String specialName) {
		// TODO Auto-generated method stub
		Session session = getSession();
		String hql = "update TbSpecial ts set ts.special=?,ts.specialName=? where ts.specialId=?";
		Query q = session.createQuery(hql);
		q.setParameter(0, special);
		q.setParameter(1, specialName);
		q.setParameter(2, id);
		q.executeUpdate();
		session.close();
	}

	@Override
	public void deleteSpecial(Integer id) {
		// TODO Auto-generated method stub
		Session session = getSession();
		Transaction tx = session.beginTransaction();
		TbSpecial t = (TbSpecial) session.get(TbSpecial.class, id);
		session.delete(t);
		tx.commit();
		session.close();
	}

	@Override
	public List findSpecial(String specialName) {
		// TODO Auto-generated method stub
		Session session = getSession();
		String hql = "from TbSpecial ts where specialName like '%"
				+ specialName +
				"%'";
		Query q = session.createQuery(hql);
		
		List list = q.list();
		session.close();
		return list;
	}

	@Override
	public Long maxCount() {
		// TODO Auto-generated method stub
		Session session = getSession();
		 Long count = (Long) session
	                .createQuery("select count(*) from TbSpecial")
	                .uniqueResult();
		 int page = 0;
		 int temp;
		 if(count%10==0) {
			 session.close();
			 return count/10;
		 }
		 session.close();
		 return count/10+1;
		 
	}

	//  销售资料获取档期名
	@Override
	public Set getSpecialTitle() {
		// TODO Auto-generated method stub
		Session session = getSession();
		String hql = "select new TbSpecial(specialName) from TbSpecial t order by t.specialId asc ";
		Query q = session.createQuery(hql);
		
		List<TbSpecial> special  = q.list();
		
		Set<String> set = new LinkedHashSet<String>();
		
		for(TbSpecial t : special) {
			set.add(t.getSpecialName());
		}
		
		session.close();
		return set;
		
	}
	
}
