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
import com.wd.service.IReturnsService;

//  用来查询客退资料的action
public class ReturnsAction extends ActionSupport implements RequestAware{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String barNo;
	private String area;
	private String startTime;
	private String endTime;
	private int nowPage;
	private IReturnsService service;
	private String downloadName;
	private InputStream downloadFile;
	private Map request;
	
	// 查询客退
	public String findReturns() throws IOException {
		
		HttpServletResponse response=ServletActionContext.getResponse();  
		response.setContentType("text/html;charset=utf-8");  
		response.setCharacterEncoding("UTF-8");  
		PrintWriter out = response.getWriter();  
		List<TbReturns>list = service.findRturns(startTime, endTime, barNo, area, nowPage);
		String maxPage = service.findRturnsCount(startTime, endTime, barNo, area, nowPage);
//		System.out.println("ACTION:" + list);
		JSONArray json = new JSONArray();
		for(TbReturns r:list)
		{
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("barNo", r.getBarNo());
			jsonObject.put("goodsNo", r.getGoodsNo());
			jsonObject.put("returnsReason", r.getReturnsReason());
			jsonObject.put("returnsNum", r.getReturnsReturnnum());
			jsonObject.put("returnsRejectnum", r.getReturnsRejectnum());
			jsonObject.put("date", r.getDate());
			jsonObject.put("retuenrate", r.getReturnrate());
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

	// 客退导出
	public String ExportReturns() throws IOException {
		
		downloadFile = service.exportReturns(startTime, endTime, barNo, area);
		if(downloadFile == null){
			request.put("msg","<script>alert('没有找到该记录，请查看填写的条件是否有误!')</script>");
			return ERROR;
		}
		SimpleDateFormat formatter = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
		Date date = new Date();
		String time = formatter.format(date).trim();
		String fileName = "客退流水导出"+time+".xlsx";
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


	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public IReturnsService getService() {
		return service;
	}

	public void setService(IReturnsService service) {
		this.service = service;
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
