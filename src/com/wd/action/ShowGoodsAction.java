package com.wd.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.RequestAware;
import org.hibernate.exception.ConstraintViolationException;
import org.omg.PortableInterceptor.SUCCESSFUL;

import com.wd.models.TbGoods;
import com.wd.models.TbGoods;
import com.wd.service.IShowGoodsService;
import com.wd.utils.Page;

public class ShowGoodsAction implements RequestAware{

	private Map<String, Object> request;

	private IShowGoodsService showGoodsService;

	private Integer id;
	private String className;
	private String addClassName;
	private String goodsNo;
	private String xz;
	private long classCurrentPage;//��ǰҳ��

	//��ѯ����
	public String findAll(){
		//��ȡҳ��
		Page page = showGoodsService.totalPageSize(classCurrentPage, className);
		if(page != null){
			request.put("page", page);
		}
		//��ȡ���м�¼
		List<TbGoods> tbGoods = showGoodsService.findAll(page);
		if(tbGoods!= null){
			request.put("classs", tbGoods);
			return "success";
		}
		return "error";
	}


	//����id��ѯ��Ŀ
	public String findById(){
		TbGoods tbGoods = showGoodsService.findById(id);
		request.put("tbGoods", tbGoods);
		return "success";
	}
	//�������Ʋ�ѯ��Ŀ
	public String findByName(){
		//��ȡҳ��
		Page page = showGoodsService.totalPageSize(classCurrentPage, className);
		if(page != null){
			request.put("page", page);
		}
		List<TbGoods> tbGoodss = showGoodsService.findByName(className, page);
		if(tbGoodss != null){
			request.put("classs", tbGoodss);
			return "success";
		}

		request.put("nullPoint", "û�в�ѯ���ü�¼�����������Ƿ���ȷ��");
		return "success";
	}

	//���
	public String addGoods() throws IOException{
		try {
			System.out.println(addClassName);
			System.out.println(goodsNo);
			TbGoods tbGoods = new TbGoods(); 
			tbGoods.setClass_(addClassName);
			tbGoods.setGoodsNo(goodsNo);
			tbGoods.setGoodsOwner(xz);
			showGoodsService.addGoods(tbGoods);
			return "success";
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return "error";
		}
	}

	//�޸�
	public String alterGoods(){
		int sec = showGoodsService.alterGoods(id,className);
		if(sec==1) {
			return "success";
		}
		if(sec==0) {
			return "error";
		}
		return "error";
	}

	//ɾ��
	public void deleteGoods() throws IOException{
		HttpServletResponse response=ServletActionContext.getResponse();  
		response.setContentType("text/html;charset=utf-8");  
		response.setCharacterEncoding("UTF-8");  
		PrintWriter out = response.getWriter();  
		try{
			if(showGoodsService.deleteGoods(id)>0) {
				out.write("1");
			};
		}catch(Exception e){
			out.write("0");
		}
	}

	public String getGoodsNo() {
		return goodsNo;
	}


	public void setGoodsNo(String goodsNo) {
		this.goodsNo = goodsNo;
	}



	public String getClassName() {
		return className;
	}


	public void setClassName(String className) {
		this.className = className;
	}


	public String getAddClassName() {
		return addClassName;
	}


	public void setAddClassName(String addClassName) {
		this.addClassName = addClassName;
	}


	public long getClassCurrentPage() {
		return classCurrentPage;
	}


	public void setClassCurrentPage(long classCurrentPage) {
		this.classCurrentPage = classCurrentPage;
	}


	public void setRequest(Map<String, Object> arg0) {
		this.request = arg0;
	}

	public IShowGoodsService getShowGoodsService() {
		return showGoodsService;
	}

	public void setShowGoodsService(IShowGoodsService showGoodsService) {
		this.showGoodsService = showGoodsService;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getGoodsName() {
		return className;
	}


	public void setGoodsName(String className) {
		this.className = className;
	}


	public String getXz() {
		return xz;
	}


	public void setXz(String xz) {
		this.xz = xz;
	}

}
