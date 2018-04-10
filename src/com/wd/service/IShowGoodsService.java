package com.wd.service;

import java.util.List;

import com.wd.models.TbGoods;
import com.wd.utils.Page;

public interface IShowGoodsService {
		//��ʾ������Ŀ
		public List<TbGoods> findAll(Page page);

		//����id��ѯ��Ŀ
		public TbGoods findById(Integer id);
		//�������Ʋ�ѯ��Ŀ
		public List<TbGoods> findByName(String name, Page page);
		
		//������Ŀ
		public void addGoods(TbGoods tbGoods);
		
		//�޸���Ŀ
		public int alterGoods(Integer id,String name);
		
		//ɾ����Ŀ
		public int deleteGoods(Integer id);
		
		//��ȡ�ܹ�ҳ����Ϣ
		public Page totalPageSize(long classCurrentPage, String name);

		
}
