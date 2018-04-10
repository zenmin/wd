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

	
	//根据 条件查询 产品信息 （页数限制）
	@SuppressWarnings("unchecked")
	public JSONArray findProject(String Contition, String ContitionValue,String owner, String _class, long arrivePage,String power) {
		Integer goodsId = null;
		List<TbBar> bars =null;
		JSONArray array = new JSONArray();//声明一个JSONArray （数据结构和List一样）
		Page page = new Page();
		page = totalPageSize(Contition, owner, ContitionValue);//调用获取页数的方法
		
		/**
		 * 判断用户选择的什么号码 
		 * 0:全部 1：货号  2：条码  3：简称
		 */
		
		if(Contition.equals("0")){
			if(owner.equals("全部")){
				//查询所有；参数 是 目标页
				bars = showBarDAO.findAll(arrivePage);
			}else {
				//根据小组查询；参数分别是 小组 和 目标页
				bars = showBarDAO.findByOwner(owner,arrivePage);
			}
		}else  if(Contition.equals("1")){
			//若是货号就需要根据货号查询出goodsId
			goodsId = showBarDAO.findIdByGoodsNo(ContitionValue);
			//在通过goodsID获取bar表
			bars =  showBarDAO.findBarByGoodsId(goodsId);
		}else  if(Contition.equals("2")){
			//若是条码就直接查询出bar表就可以了
			bars = showBarDAO.findByBarNo(ContitionValue);
		}else if(Contition.equals("3")){
			
			//若是简称就根据 模糊查询 barSimple列查询
			bars =  showBarDAO.findBySimpName(ContitionValue,arrivePage);
		}else if(Contition.equals("4")){
			
			//若是类目查询； 参数分别是 小组 和 目标页
			bars = showBarDAO.findByClass(_class,arrivePage);
		}
		try {
			for(TbBar bar:bars){
				JSONObject jsonObject = new JSONObject();//声明一个JSONObject（数据结构和Map一样）
				
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
				
				jsonObject.put("power", power);			//加权限验证
				array.add(jsonObject);
			}

			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
//		Integer i=0;
		
		return array;
	}
	
	//根据 条件查询 产品信息
		@SuppressWarnings("unchecked")
		public JSONArray findProject(String Contition, String ContitionValue,String owner, String _class) {
			Integer goodsId = null;
			List<TbBar> bars =null;
			JSONArray array = new JSONArray();//声明一个JSONArray （数据结构和List一样）
			Page page = new Page();
			page = totalPageSize(Contition, owner, ContitionValue);//调用获取页数的方法
			
			/**
			 * 判断用户选择的什么号码 
			 * 0:全部 1：货号  2：条码  3：简称
			 */
			
			if(Contition.equals("0")){
				if(owner.equals("全部")){
					//查询所有；参数 是 目标页
					bars = showBarDAO.findAll();
				}else {
					//根据小组查询；参数分别是 小组 和 目标页
					bars = showBarDAO.findByOwner(owner);
				}
			}else  if(Contition.equals("1")){
				//若是货号就需要根据货号查询出goodsId
				goodsId = showBarDAO.findIdByGoodsNo(ContitionValue);
				//在通过goodsID获取bar表
				bars =  showBarDAO.findBarByGoodsId(goodsId);
			}else  if(Contition.equals("2")){
				//若是条码就直接查询出bar表就可以了
				bars = showBarDAO.findByBarNo(ContitionValue);
			}else if(Contition.equals("3")){
				
				//若是简称就根据 模糊查询 barSimple列查询
				bars =  showBarDAO.findBySimpName(ContitionValue);
			}else if(Contition.equals("4")){
				
				//若是类目查询； 参数分别是 小组 和 目标页
				bars = showBarDAO.findByClass(_class);
			}
			try {
				for(TbBar bar:bars){
					JSONObject jsonObject = new JSONObject();//声明一个JSONObject（数据结构和Map一样）
					
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
		
		//根据 条件查询 产品信息
		public JSONArray findProjectAndExport(String Contition, String ContitionValue,String owner, String _class) {
			Integer goodsId = null;
			List<TbBar> bars =null;
			JSONArray array = new JSONArray();//声明一个JSONArray （数据结构和List一样）
			Page page = new Page();
			page = totalPageSize(Contition, owner, ContitionValue);//调用获取页数的方法
			
			/**
			 * 判断用户选择的什么号码 
			 * 0:全部 1：货号  2：条码  3：简称
			 */
			
			if(Contition.equals("0")){
				if(owner.equals("全部")){
					//查询所有；参数 是 目标页
					bars = showBarDAO.findAll();
				}else {
					//根据小组查询；参数分别是 小组 和 目标页
					bars = showBarDAO.findByOwner(owner);
				}
			}else  if(Contition.equals("1")){
				//若是货号就需要根据货号查询出goodsId
				goodsId = showBarDAO.findIdByGoodsNo(ContitionValue);
				//在通过goodsID获取bar表
				bars =  showBarDAO.findBarByGoodsId(goodsId);
			}else  if(Contition.equals("2")){
				//若是条码就直接查询出bar表就可以了
				bars = showBarDAO.findByBarNo(ContitionValue);
			}else if(Contition.equals("3")){
				
				//若是简称就根据 模糊查询 barSimple列查询
				bars =  showBarDAO.findBySimpName(ContitionValue);
			}else if(Contition.equals("4")){
				
				//若是类目查询； 参数分别是 小组 和 目标页
				bars = showBarDAO.findByClass(_class);
			}
			try {
				for(TbBar bar:bars){
					JSONObject jsonObject = new JSONObject();//声明一个JSONObject（数据结构和Map一样）
					
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
//		类目转换显示 第四级 类目
	public String getFourClass(String class_){
		return GoodsUtils.getFourClass(class_);
	}
		

	//获取小组
	public List findOwner() {

		return showBarDAO.findOwner();

	}

	//获取小组
	public List findClass() {

		return showBarDAO.findClass();

	}

	//通过BarNo直接查询bar表
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
	//修改
	public void alter(Project p) {
		showBarDAO.alter(p);
	}
	
//	//获取总共页数信息
//	public long totalPageSize(String Contition,String owner){
//		Page page = new Page();
//		long totalSize = 0;
//		if(Contition.equals("0")){
//			if(owner.equals("全部")){
//				totalSize = showBarDAO.AlltotalSize();//获取的总条数
//			}else {
//				//根据小组查询
//				totalSize = showBarDAO.totalSizeByOwner(owner);//获取的总条数
//				
//			}
//		}
//		return totalSize;
//	}
//	
	
	//获取总共页数信息
	public Page totalPageSize(String Contition,String owner,String name){
		Page page = new Page();
		long totalSize = 0;
		if(Contition.equals("0")){
			if(owner.equals("全部")){//判断通过全部查询
				totalSize = showBarDAO.AlltotalSize();//获取的总条数
				page = hendlePage(totalSize);
			}else {				//根据小组查询
				totalSize = showBarDAO.totalSizeByOwner(owner);//获取的总条数
				page = hendlePage(totalSize);
			}
		}else if(Contition.equals("3")){//判断是通过简称查询
			totalSize = showBarDAO.totalSizeBySimple(name);//获取的总条数
			page = hendlePage(totalSize);
		}
		System.out.println("总条数："+totalSize+"\n总页数："+page.getPageSize());
		return page;
	}
	//处理页数
	public Page hendlePage(long totalSize){
		Page page = new Page();
		long pageSize;
		page.setTotalSize(totalSize);//设置总条数
		page.setEverPage(10);//设置每页显示多少条
		if(page.getTotalSize() % page.getEverPage() != 0){
			pageSize = (page.getTotalSize() / page.getEverPage()) +1; 
		}else{
			pageSize = (page.getTotalSize() / page.getEverPage());
		}
		page.setPageSize(pageSize);//获取所有的页数
		if(page.getPageSize() == 0)//判断总页面是否等于0,
			page.setPageSize(1);
		return page;
	}
	//通过BarNo直接查询bar表(修改专用)
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
