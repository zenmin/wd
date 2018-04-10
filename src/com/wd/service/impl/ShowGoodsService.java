package com.wd.service.impl;

import java.util.List;

import com.wd.dao.IShowGoodsDAO;
import com.wd.models.TbGoods;
import com.wd.service.IShowGoodsService;
import com.wd.utils.Page;

public class ShowGoodsService implements IShowGoodsService{
	
	private IShowGoodsDAO showGoodsDAO;

	//��������
	public List<TbGoods> findAll(Page page) {
		return showGoodsDAO.findAll(page);
	}
	//����ID����
	public TbGoods findById(Integer id) {
		return showGoodsDAO.findById(id);
	}
	//�������Ʋ�ѯ��Ŀ
	public List<TbGoods> findByName(String name, Page page){
		return showGoodsDAO.findByName(name, page);
	}

	//���
	public void addGoods(TbGoods tbGoods) {
		showGoodsDAO.addGoods(tbGoods);
	}

	//�޸�
	public int alterGoods(Integer id ,String name) {
		return showGoodsDAO.alterGoods(id, name);
	}

	//ɾ��
	public int deleteGoods(Integer id) {
		return showGoodsDAO.deleteGoods(id);
	}
	
	//��ȡ�ܹ�ҳ����Ϣ
	public Page totalPageSize(long classCurrentPage, String name){
		Page page = new Page();
		long pageSize = 0;
		//�ж�classCurrenPage�Ƿ���ֵ��
		if(classCurrentPage != 0 ){
			//����ֵ�����ʾ���ǵ�һ�λ�ȡҳ���ˣ�ֱ�Ӱ�ҳ�洫����ֵ ���ϼ���
			page.setCurrentPage(classCurrentPage);
		}else{
			//����ֵ����0�����ʾ��һ�β�ѯ��ҳ�������и�ֵΪ1
			page.setCurrentPage(1);
		}
		
		if(name == null){
			page.setTotalSize(showGoodsDAO.AlltotalSize());//��ȡ��������
		}else{
			page.setTotalSize(showGoodsDAO.totalSizeByName(name));//����name����ȡ��������
		}
		
		page.setEverPage(10);//����ÿҳ��ʾ������
		if(page.getTotalSize() % page.getEverPage() != 0){
			pageSize = (page.getTotalSize() / page.getEverPage()) +1; 
		}else{
			pageSize = (page.getTotalSize() / page.getEverPage());
		}
		page.setPageSize(pageSize);//��ȡ���е�ҳ��
		if(page.getPageSize() == 0)//�ж���ҳ���Ƿ����0,
			page.setPageSize(1);

		return page;
	}
	
	
	
	

	public IShowGoodsDAO getShowGoodsDAO() {
		return showGoodsDAO;
	}

	public void setShowGoodsDAO(IShowGoodsDAO showGoodsDAO) {
		this.showGoodsDAO = showGoodsDAO;
	}


	
}
