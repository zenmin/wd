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
 * 这个action用来配合ajax查询专场和类目
 * @author 曾敏
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

	// 显示下拉列表里面的专场
	public String showSales() throws IOException {

		HttpServletResponse response=ServletActionContext.getResponse();  
		response.setContentType("text/html;charset=utf-8");  
		response.setCharacterEncoding("UTF-8");  
		PrintWriter out = response.getWriter();  
		Set<String> specialList = specialService.getSpecialTitle();

		//		System.out.println("specialList" + specialList);

		// 创建json
		JSONArray json = new JSONArray();
		//  遍历list
		for(String sp : specialList){
			//  创建jsonMap
			JSONObject jo = new JSONObject();
			jo.put("name", sp);
			//  加入Arrary
			json.add(jo);
		}
		try {
			//			System.out.println(json.toString());
			// 写到客户端
			out.write(json.toString());
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("json转换异常");
			return ERROR;
		}
		out.flush();  
		out.close();  
		return null;
	}

	//  查询销售资料
	public String findSales() throws IOException {
		HttpServletResponse response=ServletActionContext.getResponse();  
		response.setContentType("text/html;charset=utf-8");  
		response.setCharacterEncoding("UTF-8");  
		PrintWriter out = response.getWriter();  
		//JSON在传递过程中是普通字符串形式传递的
		//  一定要指定bean  好使用getter方法获取值
		@SuppressWarnings("unchecked")
		List<TbSales> SalesList = saleService.findSales(startTime, endTime, barNo, specialId,nowPage,barclass);
		String maxPage =saleService.getSalesCount(startTime, endTime, barNo, specialId, nowPage);
		//String special = "{\"specialList\":" + "\"" + specialList + "\"}";
		// 创建json
		JSONArray json = new JSONArray();
		//  遍历list
		for(TbSales sp : SalesList){
			//  创建jsonMap
			JSONObject jo = new JSONObject();
			//			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  
			jo.put("id", sp.getSalesId());
			jo.put("special", sp.getSpecial());
			jo.put("date", sp.getDate());
			jo.put("barNo", sp.getBarNo());
			jo.put("zone", sp.getZone());
			jo.put("salesStock", sp.getSalesStock());			//  库存
			jo.put("salesSoldnum", sp.getSalesSoldnum());			//  数量
			jo.put("salesMoney", sp.getSalesSoldmoney());		//  销售价格
			jo.put("salesPeople", sp.getSalesPeople());			//  销售人数
			jo.put("salesNum", sp.getSalesNum());				//  总备货量
			jo.put("size", maxPage);					//  数据数量
			//  加入Arrary构成json
			json.add(jo);
		}
		try {
			// System.out.println(json.toString());
			// 写到客户端
			out.write(json.toString());
		} catch (Exception e) {
			System.out.println("json转换异常");
		}
		out.flush();  
		out.close();  
		return null;
	}

	//  查询销售与商品资料，并以Excel方式导出
	public String findAndExportSales() throws IOException {

		downloadSalesFile = saleService.findSalesAndBarAndGoods(startTime, endTime, barNo, specialId,checkCondition);

		System.out.println(downloadSalesFile);
		if(downloadSalesFile == null){
			request.put("msg","<script>alert('没有找到该记录，请查看填写的条件是否有误。 即将导出的销售记录中的商品信息不存在!')</script>");
			return ERROR;
		}
		SimpleDateFormat formatter = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
		Date date = new Date();
		String time = formatter.format(date).trim();
		String fileName = "销售信息导出"+time+".xlsx";
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