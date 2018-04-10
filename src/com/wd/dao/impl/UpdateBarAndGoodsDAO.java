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

	//更新或者插入某一条记录
	public String updateOrInsert(List<Map> listAll) {
		//		Map jsonMgs = new HashMap<String, Object>();
		//		循环遍历出list中的map（map中封装着 一条记录，分别为 bar和goods 的记录）
		Session session = getSession();
		for(Map map : listAll){
		
			Transaction tr = session.beginTransaction();
			//从Map中取出 bar 与goods
			TbBar tbBar = (TbBar) map.get("tbBar");
			TbGoods tbGoods = (TbGoods) map.get("tbGoods");
			Integer goodsId = null;
			String goodsIdHql = "SELECT g.goodsId FROM TbGoods g WHERE g.goodsNo = '"+tbGoods.getGoodsNo()+"'";
			try {
				goodsId = findId(goodsIdHql);
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("没有找到goodNo为"+tbGoods.getGoodsNo()+"的记录");
				e.printStackTrace();
				session.close();
			}
			//判断goodsID不为空，则goods的这条记录存在
			if(goodsId != null){
				Integer barId = null;
				String barIdHql = "SELECT b.barId FROM TbBar b WHERE b.barNo = '"+tbBar.getBarNo()+"' and "
						+"b.tbGoods.goodsId = '"+goodsId+"'";
				try {
					barId = findId(barIdHql);

				} catch (Exception e) {
					// TODO: handle exception
					System.out.println("没有找到barNo为"+tbBar.getBarNo()+"的记录");
					e.printStackTrace();
					session.close();
				}
				//判断bar表中有没有同时存在 barNo和goodsNo，若同时都在有，则说明有这条数据，所有只能更新bar表和goods表
				//若没有，则新增bar表和goods表
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
				System.out.println("TbBar更新或插入失败");
				e.printStackTrace();
			
			}
			tr.commit();
		
		}
		session.close();
		return "success";
	}
	//	通过No 查询ID
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
