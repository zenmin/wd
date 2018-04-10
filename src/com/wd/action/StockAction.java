package com.wd.action;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.RequestAware;

import com.opensymphony.xwork2.ActionSupport;
import com.wd.models.TbReturns;
import com.wd.models.TbStock;
import com.wd.service.IStockService;

public class StockAction extends ActionSupport implements RequestAware{
	private String barNo;
	private String goods;
	private String zone;
	private String startTime;
	private String endTime;
	private int nowPage;
	private IStockService iStockService;
	private String downloadName;
	private InputStream downloadFile;
	private Map request;
	public String findStock() throws IOException {
		
		
		HttpServletResponse response=ServletActionContext.getResponse();  
		response.setContentType("text/html;charset=utf-8");  
		response.setCharacterEncoding("UTF-8");  
		PrintWriter out = response.getWriter();  
		List<TbStock>list = iStockService.findStock(startTime, endTime, barNo, goods, zone, nowPage);
		String maxPage = iStockService.findStockCount(startTime, endTime, barNo, goods, zone, nowPage);
//		System.out.println("ACTION:" + list);
		JSONArray json = new JSONArray();
		for(TbStock r:list)
		{
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("barNo", r.getBarNo());
			jsonObject.put("goodsNo", r.getGoods());
			jsonObject.put("barName", r.getBarName());
			jsonObject.put("zone", r.getZone());
			jsonObject.put("stock", r.getStock());
			jsonObject.put("date", r.getDate());
			jsonObject.put("size", maxPage);
			json.add(jsonObject);
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
	
//  查询销售与商品资料，并以Excel方式导出
	public String ExportStock() throws IOException {
		
		downloadFile = iStockService.exportStock(startTime, endTime, barNo, goods, zone);
		if(downloadFile == null){
			request.put("msg","<script>alert('没有找到该记录，请查看填写的条件是否有误!')</script>");
			return ERROR;
		}
		SimpleDateFormat formatter = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
		Date date = new Date();
		String time = formatter.format(date).trim();
		String fileName = "库存信息导出"+time+".xlsx";
		downloadName = new String(fileName.getBytes(), "ISO_8859_1"); 
		this.setDownloadName(downloadName);
		return SUCCESS;
	}
	
	public String getBarNo() {
		return barNo;
	}

	public void setBarNo(String barNo) {
		this.barNo = barNo;
	}

	public String getGoods() {
		return goods;
	}

	public void setGoods(String goods) {
		this.goods = goods;
	}

	public String getZone() {
		return zone;
	}

	public void setZone(String zone) {
		this.zone = zone;
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

	public IStockService getiStockService() {
		return iStockService;
	}

	public void setiStockService(IStockService iStockService) {
		this.iStockService = iStockService;
	}

	public String getDownloadName() {
		return downloadName;
	}

	public void setDownloadName(String downloadName) {
		this.downloadName = downloadName;
	}

	public InputStream getDownloadFile() {
		return downloadFile;
	}

	public void setDownloadFile(InputStream downloadFile) {
		this.downloadFile = downloadFile;
	}

	@Override
	public void setRequest(Map<String, Object> arg0) {
		// TODO Auto-generated method stub
		this.request = arg0;
	}
}
