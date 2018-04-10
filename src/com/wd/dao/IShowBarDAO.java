package com.wd.dao;

import java.util.List;
import java.util.Map;

import com.wd.models.TbBar;
import com.wd.models.TbGoods;
import com.wd.utils.Project;

public interface IShowBarDAO {
	
	//��ѯȫ����ҳ�����ƣ�
	public List findAll(long arrivePage);
	//��ѯȫ������ҳ�����ƣ�
	public List findAll();
	//��������(barNo)��ѯTbBar
	public List findByBarNo(String barNo);
	//��������(goodsid)��ѯTbBar
	public List findBarByGoodsId(Integer goodsId);

	//���ݻ���(goodsNo)��ѯgoodsId
	public Integer findIdByGoodsNo(String goodsNo);

	//��ѯ���е�С��
	public List findOwner(); 

	//����С���ѯ��ҳ�����ƣ�
	public List findByOwner(String owner, long arrivePage);
	//����С���ѯ
	public List findByOwner(String owner);
	

	//��ѯ���е���Ŀ
	public List findClass(); 

	//������Ŀ��ѯ��ҳ�����ƣ�
	public List findByClass(String _class, long arrivePage);
	//������Ŀ��ѯ
	public List findByClass(String _class);

	//���ݼ��ģ����ѯ
	public List findBySimpName(String simple, long arrivePage);
	//���ݼ��ģ����ѯ
	public List findBySimpName(String simple);
	
	//�޸�
	public void alter(Project p);
	
	
	//��ȡС�������
	public long totalSizeByOwner(String owner);
	
	//���ݼ�ƻ�ȡ�ܹ�������Ϣ
	public long totalSizeBySimple(String name);
	
	//��ȡ�����ܹ���������Ϣ
	public long AlltotalSize();

	//ͨ��barNoɾ����
	public int deleteBar(String id);
}
