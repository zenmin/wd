package com.wd.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.wd.dao.IShowBarDAO;
import com.wd.dao.baseDAO;
import com.wd.models.TbBar;
import com.wd.models.TbGoods;
import com.wd.utils.Project;

public class ShowBarDAO extends baseDAO implements IShowBarDAO {

	//ͨ��goodsno��ѯid
	public Integer findIdByGoodsNo(String goodsNo) {
		String sql = "select goodsId from TbGoods g where g.goodsNo = ?";
		Session session = getSession();
		Query query = session.createQuery(sql);
		query.setParameter(0, goodsNo);
		List<Integer> goodsid= query.list();
		if(goodsid.size() != 0){
			Integer id = goodsid.get(0);
					return id;
		}
		session.close();
		return null;
	}

	
	//ͨ��BarNoֱ�Ӳ�ѯbar��
	public List findByBarNo(String barNo) {
		String sql = "from TbBar b where b.barNo = ?";
		Session session = getSession();
		Query query = session.createQuery(sql);
		query.setParameter(0, barNo);
		List<TbBar> bars = query.list();
		for(TbBar bar:bars){
			bar.setTbGoods(bar.getTbGoods());
			bars.set(0, bar);
		}
		System.out.println(bars);
		session.close();
		return bars;
	}

	//ͨ��goodsid��ѯBar��
	public List findBarByGoodsId(Integer goodsId) {
		
		String sql = "from TbBar as b where b.tbGoods.goodsId = ?";
		Session session = getSession();
		Query query = session.createQuery(sql);
		query.setParameter(0, goodsId);
		List bars = query.list();
		if(bars.size() != 0){
			return bars;
		}
		session.close();
		return null;
	}

	//ģ����ѯ
	public List findBySimpName(String simple, long arrivePage) {
		StringBuffer sql = new StringBuffer( "SELECT b FROM TbBar as b ");
		if(simple!=null&&simple.length()!=0)
		sql.append("where b.barSimplename like '%"+simple+"%'");
		String s = sql.toString();
		Session session = getSession();
		Query query = session.createQuery(s);
		query.setFirstResult((int) ((arrivePage-1) * 10));//��ҳ����һ����¼�������� Ŀ��ҳ(arrivePage)-1 �ٳ� ÿҳ��ʾ������ ��+1
		query.setMaxResults(10);//һ�β�ѯ��������¼(����ÿҳ����ʾ����������)
		List bars = query.list();
		if(bars.size() != 0){
			return bars;
		}
		session.close();
		return null;
	}
	//ģ����ѯ
	public List findBySimpName(String simple) {
		StringBuffer sql = new StringBuffer( "SELECT b FROM TbBar as b ");
		if(simple!=null&&simple.length()!=0)
		sql.append("where b.barSimplename like '%"+simple+"%'");
		String s = sql.toString();
		Session session = getSession();
		Query query = session.createQuery(s);
		List bars = query.list();
		if(bars.size() != 0){
			return bars;
		}
		session.close();
		return null;
	}
	//��ѯȫ��
	public List findAll(long arrivePage) {
		System.out.println("DAO arrivePage:" + arrivePage);
		Session session = getSession();
		String hql = "FROM TbBar";
		Query query = session.createQuery(hql);
		query.setFirstResult((int) ((arrivePage-1) * 10));//��ҳ����һ����¼�������� Ŀ��ҳ(arrivePage)-1 �ٳ� ÿҳ��ʾ������ ��+1
		query.setMaxResults(10);//һ�β�ѯ��������¼(����ÿҳ����ʾ����������)
		try {
			List bars = query.list();
			if(bars.size() != 0){
//				for(TbBar bar:bars){
//					TbGoods goods = (TbGoods) session.get(TbGoods.class, bar.getTbGoods().getGoodsId());
//					bar.setTbGoods(goods);
//				}
				return bars;
			}
		} catch (Exception e) {
			e.printStackTrace();
			session.close();
		}
		
		session.close();
		return null;
	}
	//��ѯȫ��(����ר��)
	public List findAll() {
		Session session = getSession();
		String hql = "FROM TbBar";
		Query query = session.createQuery(hql);
		try {
			List bars = query.list();
			if(bars.size() != 0){
//				for(TbBar bar:bars){
//					TbGoods goods = (TbGoods) session.get(TbGoods.class, bar.getTbGoods().getGoodsId());
//					bar.setTbGoods(goods);
//				}
				return bars;
			}
		} catch (Exception e) {
			e.printStackTrace();
			session.close();
		}
		
		session.close();
		return null;
	}

	//��ѯС��
	public List findOwner() {
		String sql = "select distinct goodsOwner from TbGoods";
		Session session = getSession();
		Query query = session.createQuery(sql);
		List bars = query.list();
		session.close();
		return bars;
	}

	//����С���ѯ ��ҳ�����ƣ�
	public List findByOwner(String owner, long arrivePage){
		Session session = getSession();
		String hql = "SELECT b FROM TbBar b inner JOIN b.tbGoods g WHERE g.goodsOwner = ?";
		Query query = session.createQuery(hql);
		query.setString(0, owner);
		query.setFirstResult((int) ((arrivePage-1) * 10));//��ҳ����һ����¼�������� Ŀ��ҳ(arrivePage)-1 �ٳ� ÿҳ��ʾ������ ��+1
		query.setMaxResults(10);//һ�β�ѯ��������¼(����ÿҳ����ʾ����������)
		List bars = query.list();
		if(bars.size() != 0){
			return bars;
		}
		session.close();
		return null;
	}
	//����С���ѯ
		public List findByOwner(String owner){
			Session session = getSession();
			String hql = "SELECT b FROM TbBar b inner JOIN b.tbGoods g WHERE g.goodsOwner = ?";
			Query query = session.createQuery(hql);
			query.setString(0, owner);
			List bars = query.list();
			if(bars.size() != 0){
				return bars;
			}
			session.close();
			return null;
		}
		

		//��ѯ���е���Ŀ
		public List findClass(){
			String sql = "select distinct class_ from TbGoods";
			Session session = getSession();
			Query query = session.createQuery(sql);
			List bars = query.list();
			session.close();
			return bars;
		}

		//������Ŀ��ѯ��ҳ�����ƣ�
		public List findByClass(String _class, long arrivePage) {
			Session session = getSession();
			String hql = "SELECT b FROM TbBar b inner JOIN b.tbGoods g WHERE g.class_ = ?";
			Query query = null;
			try {
				query = session.createQuery(hql);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				session.close();
			}
			
			query.setString(0, _class);
			query.setFirstResult((int) ((arrivePage-1) * 10));//��ҳ����һ����¼�������� Ŀ��ҳ(arrivePage)-1 �ٳ� ÿҳ��ʾ������ ��+1
			query.setMaxResults(10);//һ�β�ѯ��������¼(����ÿҳ����ʾ����������)
			List bars = query.list();
			if(bars.size() != 0){
				return bars;
			}
			session.close();
			return null;
		}
		//������Ŀ��ѯ
		public List findByClass(String _class){
			Session session = getSession();
			String hql = "SELECT b FROM TbBar b inner JOIN b.tbGoods g WHERE g._class = ?";
			Query query = session.createQuery(hql);
			query.setString(0, _class);
			List bars = query.list();
			if(bars.size() != 0){
				return bars;
			}
			session.close();
			return null;
		}
		
	//�޸�
	public void alter(Project p){
		Session session = getSession();
		TbBar tbBar = (TbBar) session.get(TbBar.class, p.getBarId());
		TbGoods tbGoods = (TbGoods) session.get(TbGoods.class, p.getGoodsId());
		
		tbBar.setBarColor(p.getBarColor());
		tbBar.setBarSimplename(p.getBarSimplename());
		tbBar.setBarSaleprice(p.getBarSaleprice());
		tbBar.setBarMaterial(p.getBarMaterial());
		tbBar.setBarShowprice(p.getBarShowprice());
		tbBar.setBarSpecifications(p.getBarSpecifications());
		tbBar.setBarStandard(p.getBarStandard());
		tbBar.setRemarks(p.getRemarks());
		tbBar.setRapidWear(p.getRapidWear());
		tbBar.setInstructions(p.getInstructions());
		tbBar.setPackNo(p.getPackNo());
		tbBar.setPackCondition(p.getPackCondition());
		tbBar.setPackSize(p.getPackSize());
		tbBar.setIsSize(p.getIsSize());
		tbBar.setScale(p.getScale());
		tbBar.setLongs(p.getLongs());
		tbBar.setWidths(p.getWidths());
		tbBar.setHeights(p.getHeights());
		tbBar.setTabs(p.getTabs());
		tbBar.setTerms(p.getTerms());
		tbBar.setAlias(p.getAlias());
		
		tbGoods.setGoodsOwner(p.getOwner());
		tbGoods.setGoodsPrice(p.getGoodsPrice());
		
		session.flush();
		session.close();
	}

	//��ȡ�����ܹ�������Ϣ
	public long AlltotalSize(){
		Session session = getSession();
		String hql = "SELECT COUNT(*) FROM TbBar b";
		Query query = session.createQuery(hql);
		List totalSizes = query.list();
		session.close();
		return (Long) totalSizes.get(0);
	}
	
	//��ȡС����ܹ�����
	public long totalSizeByOwner(String owner){
		Session session = getSession();
		String hql = "SELECT COUNT(b) FROM TbBar b inner JOIN b.tbGoods g WHERE g.goodsOwner = ?";
		Query query = session.createQuery(hql);
		query.setParameter(0,owner );
		List pageSizes = query.list();
		session.close();
		return (Long) pageSizes.get(0);
	}
	//���ݼ�ƻ�ȡ�ܹ�������Ϣ
	public long totalSizeBySimple(String name){
		Session session = getSession();
		String hql = "SELECT COUNT(*) FROM TbBar b WHERE b.barSimplename like '%"+name+"%'";
		Query query = session.createQuery(hql);
		List totalSizes = query.list();
		session.close();
		return (Long) totalSizes.get(0);
	}
	
	//ͨ��barNoɾ����
	public int deleteBar(String id){
		Session session = getSession();
		Transaction tx = session.beginTransaction();
		String hql = "DELETE FROM TbBar b WHERE b.barNo = ?";
		Query query = session.createQuery(hql);
		query.setParameter(0, id);
		int i = query.executeUpdate();
		tx.commit();
		session.close();
		return i;
	}


}
