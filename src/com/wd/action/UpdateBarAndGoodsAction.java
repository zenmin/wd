package com.wd.action;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.wd.service.IUpdateBarAndGoodsService;

public class UpdateBarAndGoodsAction implements ServletResponseAware{

	private HttpServletResponse response;

	private File excel;

	private IUpdateBarAndGoodsService updateBarAndGoodsService;


	public String updateBarAndGoods() throws IOException{
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();

		String mgs = null;

		try {
			mgs = updateBarAndGoodsService.ExcelSave(excel);
			out.write(mgs);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			out.write("¸üÐÂÊ§°Ü");
		}finally{
			out.flush();
			out.close();
		}
		return null;
	}

	public IUpdateBarAndGoodsService getUpdateBarAndGoodsService() {
		return updateBarAndGoodsService;
	}

	public void setUpdateBarAndGoodsService(
			IUpdateBarAndGoodsService updateBarAndGoodsService) {
		this.updateBarAndGoodsService = updateBarAndGoodsService;
	}

	public File getExcel() {
		return excel;
	}

	public void setExcel(File excel) {
		this.excel = excel;
	}

	@Override
	public void setServletResponse(HttpServletResponse arg0) {
		this.response = arg0;
	}

}
