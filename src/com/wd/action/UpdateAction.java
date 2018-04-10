package com.wd.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.impl.Log4JLogger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.RequestAware;

import com.opensymphony.xwork2.ActionSupport;
import com.wd.service.IReturnsService;
import com.wd.service.ISalesService;
import com.wd.service.IStockService;

public class UpdateAction extends ActionSupport implements RequestAware{
	private File sales;
	private String salesContentType;
	private String salesFileName;
	private String savePath;
	private ISalesService iSalesService;
	private IReturnsService iReturnsService;
	private IStockService iStockService;
	private Map<String, Object> request;

	//  销售资料更新
	public String updateSales() throws IOException {

		HttpServletResponse response=ServletActionContext.getResponse();  
		response.setContentType("text/html;charset=utf-8");  
		response.setCharacterEncoding("UTF-8");  
		PrintWriter out = response.getWriter();  
		//		System.out.println("文件：" + sales);
		//		System.out.println("类型" + salesContentType);
		//		System.out.println("文件名" + salesFileName);

		if(sales!=null) {
			int sec = iSalesService.updateSales(sales);
			if(sec==1) {
				System.out.println("更新成功");
				//					request.put("msg", "更新成功,请更新下一个销售流水！");
				out.write("更新成功,请更新下一个销售流水！");
				return null;
			}else{
				System.out.println("更新失败");
				//					request.put("errorMsg", "更新失败！请检查是否有以下可能: <br>1:请确定你上传的是销售流水Excel表格 <br>"
				//							+ "2:请检查是否有 条形码 销售城市 档期 一模一样的列");
				out.write( "更新失败!请确定你上传的是销售流水Excel表格");
				return null;
			}
		}

		/*  创建upload文件夹
		File f = new File(getSavePath());
		if(!f.exists()) {										// 判断路径及其文件是否存在
			f.mkdirs();											// 如果不存在  就创建 包括创建子目录
		}
		//保存文件的地址
		FileOutputStream out = new FileOutputStream(getSavePath()+"\\"+salesFileName);
		//获得上传文件流
		FileInputStream in = new FileInputStream(sales);
		//将文件写入服务器
		byte[] buffer = new byte[1024];
		int len=0;
		while((len=in.read(buffer))>0){
			out.write(buffer,0,len);
		}
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		out.close();
		in.close();
		 */
		return null;
	}

	//  更新客退信息
	public String updateReturns() throws IOException {
		HttpServletResponse response=ServletActionContext.getResponse();  
		response.setContentType("text/html;charset=utf-8");  
		response.setCharacterEncoding("UTF-8");  
		PrintWriter out = response.getWriter();  

//		System.out.println("文件：" + sales);
//		System.out.println("类型" + salesContentType);
//		System.out.println("文件名" + salesFileName);
		if(sales!=null) {
			int sec = iReturnsService.updateReturns(sales);
			if(sec==1) {
				System.out.println("更新成功");
				//				request.put("msg", "更新成功,请更新下一个销售流水！");
				out.write("更新客退数据成功!");
				return null;
			}
			if(sec==0) {
				System.out.println("更新失败");
				out.write( "更新失败!1:请确定你上传的是客退流水Excel表格。2:填写数字的表格不能填汉字,比如叫你填写价格的单元格 只能填写数字！");
				return null;
			}

		}
		return null;
	}

//  更新库存信息
	public String updateStock() throws IOException {
		System.out.println(11111111);
		HttpServletResponse response=ServletActionContext.getResponse();  
		response.setContentType("text/html;charset=utf-8");  
		response.setCharacterEncoding("UTF-8");  
		PrintWriter out = response.getWriter();  
		
//		System.out.println("文件：" + sales);
//		System.out.println("类型" + salesContentType);
//		System.out.println("文件名" + salesFileName);
	
		if(sales!=null) {
			int sec = iStockService.updateStock(sales);
			if(sec==1) {
				System.out.println("更新成功");
				//				request.put("msg", "更新成功,请更新下一个销售流水！");
				out.write("更新实时库存成功!");
				return null;
			}
			if(sec==0) {
				System.out.println("更新失败");
				out.write( "更新失败! 1:请确定你上传的是实时库存Excel表格。2:填写数字的表格不能填汉字,比如叫你填写价格的单元格 只能填写数字！");
				return null;
			}
		}
		return null;
	}
	

	public File getSales() {
		return sales;
	}

	public void setSales(File sales) {
		this.sales = sales;
	}

	public String getSalesContentType() {
		return salesContentType;
	}

	public void setSalesContentType(String salesContentType) {
		this.salesContentType = salesContentType;
	}

	public String getSalesFileName() {
		return salesFileName;
	}

	public void setSalesFileName(String salesFileName) {
		this.salesFileName = salesFileName;
	}

	public void setSavePath(String savePath) {
		this.savePath = savePath;
	}

	public ISalesService getiSalesService() {
		return iSalesService;
	}

	public void setiSalesService(ISalesService iSalesService) {
		this.iSalesService = iSalesService;
	}

	@SuppressWarnings("deprecation")
	public String getSavePath() {
		return ServletActionContext.getRequest().getRealPath(savePath);
	}

	@Override
	public void setRequest(Map<String, Object> arg0) {
		// TODO Auto-generated method stub
		this.request = arg0;
	}

	public IReturnsService getiReturnsService() {
		return iReturnsService;
	}

	public void setiReturnsService(IReturnsService iReturnsService) {
		this.iReturnsService = iReturnsService;
	}

	public IStockService getiStockService() {
		return iStockService;
	}

	public void setiStockService(IStockService iStockService) {
		this.iStockService = iStockService;
	}

}
