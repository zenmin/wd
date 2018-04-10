package com.wd.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.wd.dao.IUpdateBarAndGoodsDAO;
import com.wd.dao.baseDAO;
import com.wd.models.TbBar;
import com.wd.models.TbGoods;
import com.wd.models.TbStock;

public class UpdateBarAndGoodsDAO extends baseDAO implements IUpdateBarAndGoodsDAO {

	//���»��߲���ĳһ����¼
	public String updateOrInsert(List<Map> listAll) {
		//		Map jsonMgs = new HashMap<String, Object>();
		//		ѭ��������list�е�map��map�з�װ�� һ����¼���ֱ�Ϊ bar��goods �ļ�¼��
		Session session = getSession();
		for(Map map : listAll){
		
			Transaction tr = session.beginTransaction();
			//��Map��ȡ�� bar ��goods
			TbBar tbBar = (TbBar) map.get("tbBar");
			TbGoods tbGoods = (TbGoods) map.get("tbGoods");
			Integer goodsId = null;
			String goodsIdHql = "SELECT g.goodsId FROM TbGoods g WHERE g.goodsNo = '"+tbGoods.getGoodsNo()+"'";
			try {
				goodsId = findId(goodsIdHql);
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("û���ҵ�goodNoΪ"+tbGoods.getGoodsNo()+"�ļ�¼");
				e.printStackTrace();
				session.close();
			}
			//�ж�goodsID��Ϊ�գ���goods��������¼����
			if(goodsId != null){
				Integer barId = null;
				String barIdHql = "SELECT b.barId FROM TbBar b WHERE b.barNo = '"+tbBar.getBarNo()+"' and "
						+"b.tbGoods.goodsId = '"+goodsId+"'";
				try {
					barId = findId(barIdHql);

				} catch (Exception e) {
					// TODO: handle exception
					System.out.println("û���ҵ�barNoΪ"+tbBar.getBarNo()+"�ļ�¼");
					e.printStackTrace();
					session.close();
				}
				//�ж�bar������û��ͬʱ���� barNo��goodsNo����ͬʱ�����У���˵�����������ݣ�����ֻ�ܸ���bar���goods��
				//��û�У�������bar���goods��
				if(barId != null){
					tbBar.setBarId(barId);
				}
				tbGoods.setGoodsId(goodsId);
			}
			try {
				session.saveOrUpdate(tbGoods);
				tbBar.setTbGoods(tbGoods);
				session.saveOrUpdate(tbBar);
				session.clear();
			} catch (Exception e) {
				System.out.println("TbBar���»����ʧ��");
				e.printStackTrace();
			
			}
			tr.commit();
		
		}
		session.close();
		return "success";
	}
	//	ͨ��No ��ѯID
	public Integer findId(String Hql){
		Session session = getSession();
		Query query = session.createQuery(Hql);
		List list = query.list();
		if(list.size() == 0){
			session.close();
			return null;
		}
		session.close();
		return (Integer) list.get(0);
	}
	
	
}
