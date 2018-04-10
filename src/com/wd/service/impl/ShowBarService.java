package com.wd.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;








import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.interceptor.RequestAware;

import com.wd.dao.IShowBarDAO;
import com.wd.models.TbBar;
import com.wd.models.TbGoods;
import com.wd.service.IShowBarService;
import com.wd.utils.GoodsUtils;
import com.wd.utils.Page;
import com.wd.utils.Project;

public class ShowBarService implements IShowBarService{
	
	
	private IShowBarDAO showBarDAO;

	
	//���� ������ѯ ��Ʒ��Ϣ ��ҳ�����ƣ�
	@SuppressWarnings("unchecked")
	public JSONArray findProject(String Contition, String ContitionValue,String owner, String _class, long arrivePage,String power) {
		Integer goodsId = null;
		List<TbBar> bars =null;
		JSONArray array = new JSONArray();//����һ��JSONArray �����ݽṹ��Listһ����
		Page page = new Page();
		page = totalPageSize(Contition, owner, ContitionValue);//���û�ȡҳ���ķ���
		
		/**
		 * �ж��û�ѡ���ʲô���� 
		 * 0:ȫ�� 1������  2������  3�����
		 */
		
		if(Contition.equals("0")){
			if(owner.equals("ȫ��")){
				//��ѯ���У����� �� Ŀ��ҳ
				bars = showBarDAO.findAll(arrivePage);
			}else {
				//����С���ѯ�������ֱ��� С�� �� Ŀ��ҳ
				bars = showBarDAO.findByOwner(owner,arrivePage);
			}
		}else  if(Contition.equals("1")){
			//���ǻ��ž���Ҫ���ݻ��Ų�ѯ��goodsId
			goodsId = showBarDAO.findIdByGoodsNo(ContitionValue);
			//��ͨ��goodsID��ȡbar��
			bars =  showBarDAO.findBarByGoodsId(goodsId);
		}else  if(Contition.equals("2")){
			//���������ֱ�Ӳ�ѯ��bar��Ϳ�����
			bars = showBarDAO.findByBarNo(ContitionValue);
		}else if(Contition.equals("3")){
			
			//���Ǽ�ƾ͸��� ģ����ѯ barSimple�в�ѯ
			bars =  showBarDAO.findBySimpName(ContitionValue,arrivePage);
		}else if(Contition.equals("4")){
			
			//������Ŀ��ѯ�� �����ֱ��� С�� �� Ŀ��ҳ
			bars = showBarDAO.findByClass(_class,arrivePage);
		}
		try {
			for(TbBar bar:bars){
				JSONObject jsonObject = new JSONObject();//����һ��JSONObject�����ݽṹ��Mapһ����
				
				jsonObject.put("barColor", bar.getBarColor());
				jsonObject.put("barMaterial", bar.getBarMaterial());
				jsonObject.put("barId",bar.getBarId());
				jsonObject.put("barNo",bar.getBarNo());
				jsonObject.put("barSaleprice",bar.getBarSaleprice());
				jsonObject.put("barShowprice",bar.getBarShowprice());
				jsonObject.put("barSimplename",bar.getBarSimplename());
				jsonObject.put("barSpecifications",bar.getBarSpecifications());
				jsonObject.put("barStandard",bar.getBarStandard());
				jsonObject.put("goodsId",bar.getTbGoods().getGoodsId());
				jsonObject.put("goodsPrice",bar.getTbGoods().getGoodsPrice());
				jsonObject.put("owner",bar.getTbGoods().getGoodsOwner());
				jsonObject.put("goodsNo",bar.getTbGoods().getGoodsNo());

				jsonObject.put("_class",this.getFourClass(bar.getTbGoods().getClass_()));
				
				jsonObject.put("remartks", bar.getRemarks());
				jsonObject.put("scale", bar.getScale());
				jsonObject.put("longs", bar.getLongs());
				jsonObject.put("widths", bar.getWidths());
				jsonObject.put("height", bar.getHeights());
				jsonObject.put("rapidWear", bar.getRapidWear());
				jsonObject.put("instructions", bar.getInstructions());
				jsonObject.put("packNo", bar.getPackNo());
				jsonObject.put("packCondition", bar.getPackCondition());
				jsonObject.put("packSize", bar.getPackSize());
				jsonObject.put("isSize", bar.getIsSize());
				jsonObject.put("tabs",bar.getTabs());
				jsonObject.put("terms", bar.getTerms());
				jsonObject.put("alias", bar.getAlias());
				
				jsonObject.put("pageSize",page.getPageSize());
				
				jsonObject.put("power", power);			//��Ȩ����֤
				array.add(jsonObject);
			}

			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
//		Integer i=0;
		
		return array;
	}
	
	//���� ������ѯ ��Ʒ��Ϣ
		@SuppressWarnings("unchecked")
		public JSONArray findProject(String Contition, String ContitionValue,String owner, String _class) {
			Integer goodsId = null;
			List<TbBar> bars =null;
			JSONArray array = new JSONArray();//����һ��JSONArray �����ݽṹ��Listһ����
			Page page = new Page();
			page = totalPageSize(Contition, owner, ContitionValue);//���û�ȡҳ���ķ���
			
			/**
			 * �ж��û�ѡ���ʲô���� 
			 * 0:ȫ�� 1������  2������  3�����
			 */
			
			if(Contition.equals("0")){
				if(owner.equals("ȫ��")){
					//��ѯ���У����� �� Ŀ��ҳ
					bars = showBarDAO.findAll();
				}else {
					//����С���ѯ�������ֱ��� С�� �� Ŀ��ҳ
					bars = showBarDAO.findByOwner(owner);
				}
			}else  if(Contition.equals("1")){
				//���ǻ��ž���Ҫ���ݻ��Ų�ѯ��goodsId
				goodsId = showBarDAO.findIdByGoodsNo(ContitionValue);
				//��ͨ��goodsID��ȡbar��
				bars =  showBarDAO.findBarByGoodsId(goodsId);
			}else  if(Contition.equals("2")){
				//���������ֱ�Ӳ�ѯ��bar��Ϳ�����
				bars = showBarDAO.findByBarNo(ContitionValue);
			}else if(Contition.equals("3")){
				
				//���Ǽ�ƾ͸��� ģ����ѯ barSimple�в�ѯ
				bars =  showBarDAO.findBySimpName(ContitionValue);
			}else if(Contition.equals("4")){
				
				//������Ŀ��ѯ�� �����ֱ��� С�� �� Ŀ��ҳ
				bars = showBarDAO.findByClass(_class);
			}
			try {
				for(TbBar bar:bars){
					JSONObject jsonObject = new JSONObject();//����һ��JSONObject�����ݽṹ��Mapһ����
					
					jsonObject.put("barColor", bar.getBarColor());
					jsonObject.put("barMaterial", bar.getBarMaterial());
					jsonObject.put("barId",bar.getBarId());
					jsonObject.put("barNo",bar.getBarNo());
					jsonObject.put("barSaleprice",bar.getBarSaleprice());
					jsonObject.put("barShowprice",bar.getBarShowprice());
					jsonObject.put("barSimplename",bar.getBarSimplename());
					jsonObject.put("barSpecifications",bar.getBarSpecifications());
					jsonObject.put("barStandard",bar.getBarStandard());
					jsonObject.put("goodsId",bar.getTbGoods().getGoodsId());
					jsonObject.put("goodsPrice",bar.getTbGoods().getGoodsPrice());
					jsonObject.put("owner",bar.getTbGoods().getGoodsOwner());
					jsonObject.put("goodsNo",bar.getTbGoods().getGoodsNo());
					
					jsonObject.put("remartks", bar.getRemarks());
					jsonObject.put("scale", bar.getScale());
					jsonObject.put("longs", bar.getLongs());
					jsonObject.put("widths", bar.getWidths());
					jsonObject.put("height", bar.getHeights());
					jsonObject.put("rapidWear", bar.getRapidWear());
					jsonObject.put("instructions", bar.getInstructions());
					jsonObject.put("packNo", bar.getPackNo());
					jsonObject.put("packCondition", bar.getPackCondition());
					jsonObject.put("packSize", bar.getPackSize());
					jsonObject.put("isSize", bar.getIsSize());
					jsonObject.put("tabs",bar.getTabs());
					jsonObject.put("terms", bar.getTerms());
					jsonObject.put("alias", bar.getAlias());
					
//					jsonObject.put("_class",bar.getTbGoods().getClass_());
					jsonObject.put("_class",getFourClass(bar.getTbGoods().getClass_()));
					
					jsonObject.put("pageSize",page.getPageSize());
					array.add(jsonObject);
				}

				
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			
//			Integer i=0;
			
			return array;
		}
		
		//���� ������ѯ ��Ʒ��Ϣ
		public JSONArray findProjectAndExport(String Contition, String ContitionValue,String owner, String _class) {
			Integer goodsId = null;
			List<TbBar> bars =null;
			JSONArray array = new JSONArray();//����һ��JSONArray �����ݽṹ��Listһ����
			Page page = new Page();
			page = totalPageSize(Contition, owner, ContitionValue);//���û�ȡҳ���ķ���
			
			/**
			 * �ж��û�ѡ���ʲô���� 
			 * 0:ȫ�� 1������  2������  3�����
			 */
			
			if(Contition.equals("0")){
				if(owner.equals("ȫ��")){
					//��ѯ���У����� �� Ŀ��ҳ
					bars = showBarDAO.findAll();
				}else {
					//����С���ѯ�������ֱ��� С�� �� Ŀ��ҳ
					bars = showBarDAO.findByOwner(owner);
				}
			}else  if(Contition.equals("1")){
				//���ǻ��ž���Ҫ���ݻ��Ų�ѯ��goodsId
				goodsId = showBarDAO.findIdByGoodsNo(ContitionValue);
				//��ͨ��goodsID��ȡbar��
				bars =  showBarDAO.findBarByGoodsId(goodsId);
			}else  if(Contition.equals("2")){
				//���������ֱ�Ӳ�ѯ��bar��Ϳ�����
				bars = showBarDAO.findByBarNo(ContitionValue);
			}else if(Contition.equals("3")){
				
				//���Ǽ�ƾ͸��� ģ����ѯ barSimple�в�ѯ
				bars =  showBarDAO.findBySimpName(ContitionValue);
			}else if(Contition.equals("4")){
				
				//������Ŀ��ѯ�� �����ֱ��� С�� �� Ŀ��ҳ
				bars = showBarDAO.findByClass(_class);
			}
			try {
				for(TbBar bar:bars){
					JSONObject jsonObject = new JSONObject();//����һ��JSONObject�����ݽṹ��Mapһ����
					
					jsonObject.put("barColor", bar.getBarColor());
					jsonObject.put("barMaterial", bar.getBarMaterial());
					jsonObject.put("barId",bar.getBarId());
					jsonObject.put("barNo",bar.getBarNo());
					jsonObject.put("barSaleprice",bar.getBarSaleprice());
					jsonObject.put("barShowprice",bar.getBarShowprice());
					jsonObject.put("barSimplename",bar.getBarSimplename());
					jsonObject.put("barSpecifications",bar.getBarSpecifications());
					jsonObject.put("barStandard",bar.getBarStandard());
					jsonObject.put("goodsId",bar.getTbGoods().getGoodsId());
					jsonObject.put("goodsPrice",bar.getTbGoods().getGoodsPrice());
					jsonObject.put("owner",bar.getTbGoods().getGoodsOwner());
					jsonObject.put("goodsNo",bar.getTbGoods().getGoodsNo());
					
					jsonObject.put("remartks", bar.getRemarks());
					jsonObject.put("scale", bar.getScale());
					jsonObject.put("longs", bar.getLongs());
					jsonObject.put("widths", bar.getWidths());
					jsonObject.put("height", bar.getHeights());
					jsonObject.put("rapidWear", bar.getRapidWear());
					jsonObject.put("instructions", bar.getInstructions());
					jsonObject.put("packNo", bar.getPackNo());
					jsonObject.put("packCondition", bar.getPackCondition());
					jsonObject.put("packSize", bar.getPackSize());
					jsonObject.put("isSize", bar.getIsSize());
					jsonObject.put("tabs",bar.getTabs());
					jsonObject.put("terms", bar.getTerms());
					jsonObject.put("alias", bar.getAlias());
					
					jsonObject.put("_class",bar.getTbGoods().getClass_());
					
					jsonObject.put("pageSize",page.getPageSize());
					array.add(jsonObject);
				}

				
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			
//			Integer i=0;
			
			return array;
		}
//		��Ŀת����ʾ ���ļ� ��Ŀ
	public String getFourClass(String class_){
		return GoodsUtils.getFourClass(class_);
	}
		

	//��ȡС��
	public List findOwner() {

		return showBarDAO.findOwner();

	}

	//��ȡС��
	public List findClass() {

		return showBarDAO.findClass();

	}

	//ͨ��BarNoֱ�Ӳ�ѯbar��
	public JSONArray findByBarNo(String barNo){
		List listProject = new ArrayList();
		List<TbBar> bars = showBarDAO.findByBarNo(barNo);
		JSONArray array = new JSONArray();
		for(TbBar bar:bars){
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("barColor", bar.getBarColor());
			jsonObject.put("barMaterial", bar.getBarMaterial());
			
			jsonObject.put("barId",bar.getBarId());
			jsonObject.put("barNo",bar.getBarNo());
			jsonObject.put("barSaleprice",bar.getBarSaleprice());
			jsonObject.put("barShowprice",bar.getBarShowprice());
			jsonObject.put("barSimplename",bar.getBarSimplename());
			jsonObject.put("barSpecifications",bar.getBarSpecifications());
			jsonObject.put("barStandard",bar.getBarStandard());
			jsonObject.put("goodsId",bar.getTbGoods().getGoodsId());
			jsonObject.put("goodsPrice",bar.getTbGoods().getGoodsPrice());
			jsonObject.put("goodsOwner",bar.getTbGoods().getGoodsOwner());
			jsonObject.put("goodsNo",bar.getTbGoods().getGoodsNo());

			jsonObject.put("remartks", bar.getRemarks());
			jsonObject.put("scale", bar.getScale());
			jsonObject.put("longs", bar.getLongs());
			jsonObject.put("widths", bar.getWidths());
			jsonObject.put("height", bar.getHeights());
			jsonObject.put("rapidWear", bar.getRapidWear());
			jsonObject.put("instructions", bar.getInstructions());
			jsonObject.put("packNo", bar.getPackNo());
			jsonObject.put("packCondition", bar.getPackCondition());
			jsonObject.put("packSize", bar.getPackSize());
			jsonObject.put("isSize", bar.getIsSize());
			jsonObject.put("tabs",bar.getTabs());
			jsonObject.put("terms", bar.getTerms());
			jsonObject.put("alias", bar.getAlias());
			
			jsonObject.put("_class",bar.getTbGoods().getClass_());
			
			array.add(jsonObject);
		}
		
		return array;
	}
	//�޸�
	public void alter(Project p) {
		showBarDAO.alter(p);
	}
	
//	//��ȡ�ܹ�ҳ����Ϣ
//	public long totalPageSize(String Contition,String owner){
//		Page page = new Page();
//		long totalSize = 0;
//		if(Contition.equals("0")){
//			if(owner.equals("ȫ��")){
//				totalSize = showBarDAO.AlltotalSize();//��ȡ��������
//			}else {
//				//����С���ѯ
//				totalSize = showBarDAO.totalSizeByOwner(owner);//��ȡ��������
//				
//			}
//		}
//		return totalSize;
//	}
//	
	
	//��ȡ�ܹ�ҳ����Ϣ
	public Page totalPageSize(String Contition,String owner,String name){
		Page page = new Page();
		long totalSize = 0;
		if(Contition.equals("0")){
			if(owner.equals("ȫ��")){//�ж�ͨ��ȫ����ѯ
				totalSize = showBarDAO.AlltotalSize();//��ȡ��������
				page = hendlePage(totalSize);
			}else {				//����С���ѯ
				totalSize = showBarDAO.totalSizeByOwner(owner);//��ȡ��������
				page = hendlePage(totalSize);
			}
		}else if(Contition.equals("3")){//�ж���ͨ����Ʋ�ѯ
			totalSize = showBarDAO.totalSizeBySimple(name);//��ȡ��������
			page = hendlePage(totalSize);
		}
		System.out.println("��������"+totalSize+"\n��ҳ����"+page.getPageSize());
		return page;
	}
	//����ҳ��
	public Page hendlePage(long totalSize){
		Page page = new Page();
		long pageSize;
		page.setTotalSize(totalSize);//����������
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
	//ͨ��BarNoֱ�Ӳ�ѯbar��(�޸�ר��)
	public List ByBarNo(String barNo){
		List listProject = new ArrayList();
		List<TbBar> bars = showBarDAO.findByBarNo(barNo);
		for(TbBar bar : bars){
			Project pro = new Project();
			pro.setBarId(bar.getBarId());
			pro.setGoodsId(bar.getTbGoods().getGoodsId());
			pro.setBarNo(bar.getBarNo());
			pro.setGoodsNo(bar.getTbGoods().getGoodsNo());
			pro.setOwner(bar.getTbGoods().getGoodsOwner());
			pro.setBarSimplename(bar.getBarSimplename());
			pro.setBarShowprice(bar.getBarShowprice());
			pro.setBarSaleprice(bar.getBarSaleprice());
			pro.setBarColor(bar.getBarColor());
			pro.setBarMaterial(bar.getBarMaterial());
			pro.setBarStandard(bar.getBarStandard());
			pro.setBarSpecifications(bar.getBarSpecifications());
			pro.setGoodsPrice(bar.getTbGoods().getGoodsPrice());
			
			pro.setRemarks(bar.getRemarks());
			pro.setRapidWear(bar.getRapidWear());
			pro.setInstructions(bar.getInstructions());
			pro.setPackNo(bar.getPackNo());
			pro.setPackCondition(bar.getPackCondition());
			pro.setPackSize(bar.getPackSize());
			pro.setIsSize(bar.getIsSize());
			pro.setScale(bar.getScale());
			pro.setLongs(bar.getLongs());
			pro.setWidths(bar.getWidths());
			pro.setHeights(bar.getHeights());
			pro.setAlias(bar.getAlias());
			pro.setTerms(bar.getTerms());
			pro.setTabs(bar.getTabs());
			listProject.add(pro);
		}
		return listProject;
	}
	public IShowBarDAO getShowBarDAO() {
		return showBarDAO;
	}
	
	public void setShowBarDAO(IShowBarDAO showBarDAO) {
		this.showBarDAO = showBarDAO;
	}


	@Override
	public int  deleteBar(String id) {
		return showBarDAO.deleteBar(id);
	}


}
