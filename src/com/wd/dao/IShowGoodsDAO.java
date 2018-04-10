package com.wd.dao;

import java.util.List;

import com.wd.models.TbGoods;
import com.wd.utils.Page;

public interface IShowGoodsDAO {

	//��ʾ������Ŀ
	public List<TbGoods> findAll(Page page);
	
	//����id��ѯ��Ŀ
	public TbGoods findById(Integer id);	
	//�������Ʋ�ѯ��Ŀ
	public List<TbGoods> findByName(String name, Page page);
	
	//������Ŀ
	public void addGoods(TbGoods tbGoods);
	
	//�޸���Ŀ
	public int alterGoods(Integer id, String name);
	
	//ɾ����Ŀ
	public int deleteGoods(Integer id);
	//��ȡ������
	public long AlltotalSize();

	//��ȡname ������
	public long totalSizeByName(String name);
	
}
