package com.wd.action;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.RequestAware;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.wd.models.TbSales;
import com.wd.models.TbSpecial;
import com.wd.service.ISalesService;
import com.wd.service.ISpecialService;

/**
 * ���action�������ajax��ѯר������Ŀ
 * @author ����
 *
 */

public class SalesAction extends ActionSupport implements RequestAware{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ISpecialService specialService;
	private ISalesService saleService;
	private String barNo;
	private String specialId;
	private String startTime;
	private String endTime;
	private int nowPage;
	private int[] checkCondition;

	private InputStream downloadSalesFile;
	private String downloadName;
	private Map<String, Object> request;
	private String barclass;

	// ��ʾ�����б������ר��
	public String showSales() throws IOException {

		HttpServletResponse response=ServletActionContext.getResponse();  
		response.setContentType("text/html;charset=utf-8");  
		response.setCharacterEncoding("UTF-8");  
		PrintWriter out = response.getWriter();  
		Set<String> specialList = specialService.getSpecialTitle();

		//		System.out.println("specialList" + specialList);

		// ����json
		JSONArray json = new JSONArray();
		//  ����list
		for(String sp : specialList){
			//  ����jsonMap
			JSONObject jo = new JSONObject();
			jo.put("name", sp);
			//  ����Arrary
			json.add(jo);
		}
		try {
			//			System.out.println(json.toString());
			// д���ͻ���
			out.write(json.toString());
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("jsonת���쳣");
			return ERROR;
		}
		out.flush();  
		out.close();  
		return null;
	}

	//  ��ѯ��������
	public String findSales() throws IOException {
		HttpServletResponse response=ServletActionContext.getResponse();  
		response.setContentType("text/html;charset=utf-8");  
		response.setCharacterEncoding("UTF-8");  
		PrintWriter out = response.getWriter();  
		//JSON�ڴ��ݹ���������ͨ�ַ�����ʽ���ݵ�
		//  һ��Ҫָ��bean  ��ʹ��getter������ȡֵ
		@SuppressWarnings("unchecked")
		List<TbSales> SalesList = saleService.findSales(startTime, endTime, barNo, specialId,nowPage,barclass);
		String maxPage =saleService.getSalesCount(startTime, endTime, barNo, specialId, nowPage);
		//String special = "{\"specialList\":" + "\"" + specialList + "\"}";
		// ����json
		JSONArray json = new JSONArray();
		//  ����list
		for(TbSales sp : SalesList){
			//  ����jsonMap
			JSONObject jo = new JSONObject();
			//			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  
			jo.put("id", sp.getSalesId());
			jo.put("special", sp.getSpecial());
			jo.put("date", sp.getDate());
			jo.put("barNo", sp.getBarNo());
			jo.put("zone", sp.getZone());
			jo.put("salesStock", sp.getSalesStock());			//  ���
			jo.put("salesSoldnum", sp.getSalesSoldnum());			//  ����
			jo.put("salesMoney", sp.getSalesSoldmoney());		//  ���ۼ۸�
			jo.put("salesPeople", sp.getSalesPeople());			//  ��������
			jo.put("salesNum", sp.getSalesNum());				//  �ܱ�����
			jo.put("size", maxPage);					//  ��������
			//  ����Arrary����json
			json.add(jo);
		}
		try {
			// System.out.println(json.toString());
			// д���ͻ���
			out.write(json.toString());
		} catch (Exception e) {
			System.out.println("jsonת���쳣");
		}
		out.flush();  
		out.close();  
		return null;
	}

	//  ��ѯ��������Ʒ���ϣ�����Excel��ʽ����
	public String findAndExportSales() throws IOException {

		downloadSalesFile = saleService.findSalesAndBarAndGoods(startTime, endTime, barNo, specialId,checkCondition);

		System.out.println(downloadSalesFile);
		if(downloadSalesFile == null){
			request.put("msg","<script>alert('û���ҵ��ü�¼����鿴��д�������Ƿ����� �������������ۼ�¼�е���Ʒ��Ϣ������!')</script>");
			return ERROR;
		}
		SimpleDateFormat formatter = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
		Date date = new Date();
		String time = formatter.format(date).trim();
		String fileName = "������Ϣ����"+time+".xlsx";
		downloadName = new String(fileName.getBytes(), "ISO_8859_1"); 
		this.setDownloadName(downloadName);

		return SUCCESS;
	}



	public ISpecialService getSpecialService() {
		return specialService;
	}
	public void setSpecialService(ISpecialService specialService) {
		this.specialService = specialService;
	}
	public ISalesService getSaleService() {
		return saleService;
	}
	public void setSaleService(ISalesService saleService) {
		this.saleService = saleService;
	}

	public String getBarNo() {
		return barNo;
	}

	public void setBarNo(String barNo) {
		this.barNo = barNo;
	}

	public String getSpecialId() {
		return specialId;
	}

	public void setSpecialId(String specialId) {
		this.specialId = specialId;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public int getNowPage() {
		return nowPage;
	}

	public void setNowPage(int nowPage) {
		this.nowPage = nowPage;
	}

	public int[] getCheckCondition() {
		return checkCondition;
	}

	public void setCheckCondition(int[] checkCondition) {
		this.checkCondition = checkCondition;
	}

	public InputStream getDownloadSalesFile() {
		return downloadSalesFile;
	}

	public void setDownloadSalesFile(InputStream downloadSalesFile) {
		this.downloadSalesFile = downloadSalesFile;
	}

	public String getDownloadName() {
		return downloadName;
	}

	public void setDownloadName(String downloadName) {
		this.downloadName = downloadName;
	}

	@Override
	public void setRequest(Map<String, Object> arg0) {
		// TODO Auto-generated method stub
		this.request = arg0;
	}

	public String getBarclass() {
		return barclass;
	}

	public void setBarclass(String barclass) {
		this.barclass = barclass;
	}

}