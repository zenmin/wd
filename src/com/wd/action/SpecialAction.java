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
	 * ����һЩȫ�ֱ����Լ��ӿ�
	 */
	private ISpecialService specialService;
	private Map request;
	private Integer id;
	private String special;
	private String specialName;
	private int nowPage;
	/*
	 * �����ҵ���߼�
	 */
	// ��ʾר��
	public String showSpecial() {

		//  ��ʾר��
		if(nowPage<=0) {
			nowPage=1;
		}
		List special = specialService.getSpecialDAO(nowPage);
		//		System.out.println(special);
		request.put("special", special);		//  ��ʾר��
		Long i = specialService.maxCount();		//  ��ҳ��
		request.put("MaxCount", i);

		return SUCCESS;
	}

	//  �༭ҳ��
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

	//  ����ר��
	public String updateSpecial() {
		if(id!=null&&specialName.trim()!="") {
			//			System.out.println(special + "," +specialName );
			specialService.updateSpecial(id,"",specialName.trim());
			return SUCCESS;
		}
		return ERROR;
	}

	//  ���ר��
	public String addSpecial() {
		TbSpecial ts = new TbSpecial();
		ts.setSpecial("");
		ts.setSpecialId(id);
		ts.setSpecialName(specialName.trim());
		specialService.addSpecial(ts);
		return SUCCESS;
	}

	//  ɾ��ר��
	public String deleteSpecial()
	{
		try {
			specialService.deleteSpecial(id);
			return SUCCESS;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			request.put("deleteSpecialError", "<script>alert('��,����ɾ�����ר��Ŷ,��Ϊ�����������滹�����ר����Ӧ����ƷŶ\\n ����Ҫ��ɾ�����������¶�Ӧר������ƷŶ��');</script>");
			return ERROR;
		}
	}

	//  ����ר��
	public String findSpecial() {
		List list=  specialService.findSpecial(specialName.trim());
		if(list.size()>0) {
			request.put("special", list);

		}else {
			//���û��ѯ������  ������ʾ ����
			request.put("finderror", "<script>alert('��,û�в�ѯ�����ר��,����ר�����Ƿ���ȷ��');window.history.back();</script>");
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
