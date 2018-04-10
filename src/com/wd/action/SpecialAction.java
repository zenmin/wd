package com.wd.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.RequestAware;

import com.opensymphony.xwork2.ActionSupport;
import com.wd.models.TbSpecial;
import com.wd.service.ISpecialService;

@SuppressWarnings({ "unchecked", "serial" })
public class SpecialAction extends ActionSupport implements RequestAware{
	/**
	 * 定义一些全局变量以及接口
	 */
	private ISpecialService specialService;
	private Map request;
	private Integer id;
	private String special;
	private String specialName;
	private int nowPage;
	/*
	 * 下面的业务逻辑
	 */
	// 显示专场
	public String showSpecial() {

		//  显示专场
		if(nowPage<=0) {
			nowPage=1;
		}
		List special = specialService.getSpecialDAO(nowPage);
		//		System.out.println(special);
		request.put("special", special);		//  显示专场
		Long i = specialService.maxCount();		//  总页数
		request.put("MaxCount", i);

		return SUCCESS;
	}

	//  编辑页面
	public String editSpecial()
	{
		if(id!=null) {
			List list = specialService.getSpecialByid(id);
			//		System.out.println(list);
			request.put("editspecial", list);
			return SUCCESS;
		}
		return ERROR;
	}

	//  更新专场
	public String updateSpecial() {
		if(id!=null&&specialName.trim()!="") {
			//			System.out.println(special + "," +specialName );
			specialService.updateSpecial(id,"",specialName.trim());
			return SUCCESS;
		}
		return ERROR;
	}

	//  添加专场
	public String addSpecial() {
		TbSpecial ts = new TbSpecial();
		ts.setSpecial("");
		ts.setSpecialId(id);
		ts.setSpecialName(specialName.trim());
		specialService.addSpecial(ts);
		return SUCCESS;
	}

	//  删除专场
	public String deleteSpecial()
	{
		try {
			specialService.deleteSpecial(id);
			return SUCCESS;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			request.put("deleteSpecialError", "<script>alert('亲,不能删除这个专场哦,因为销售资料里面还有这个专场对应的商品哦\\n 你需要先删除销售资料下对应专场的商品哦！');</script>");
			return ERROR;
		}
	}

	//  查找专场
	public String findSpecial() {
		List list=  specialService.findSpecial(specialName.trim());
		if(list.size()>0) {
			request.put("special", list);

		}else {
			//如果没查询到内容  给出提示 返回
			request.put("finderror", "<script>alert('亲,没有查询到这个专场,请检查专场名是否正确！');window.history.back();</script>");
		}
		return SUCCESS;
	}


	/*
	 * Setter and getter
	 * (non-Javadoc)
	 * @see org.apache.struts2.interceptor.RequestAware#setRequest(java.util.Map)
	 */
	@Override
	public void setRequest(Map<String, Object> arg0) {
		// TODO Auto-generated method stub
		this.request = arg0;
	}

	public ISpecialService getSpecialService() {
		return specialService;
	}
	public void setSpecialService(ISpecialService specialService) {
		this.specialService = specialService;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSpecial() {
		return special;
	}

	public void setSpecial(String special) {
		this.special = special;
	}

	public String getSpecialName() {
		return specialName;
	}

	public void setSpecialName(String specialName) {
		this.specialName = specialName;
	}

	public int getNowPage() {
		return nowPage;
	}

	public void setNowPage(int nowPage) {
		this.nowPage = nowPage;
	}

}
