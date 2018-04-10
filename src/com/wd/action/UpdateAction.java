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

	//  �������ϸ���
	public String updateSales() throws IOException {

		HttpServletResponse response=ServletActionContext.getResponse();  
		response.setContentType("text/html;charset=utf-8");  
		response.setCharacterEncoding("UTF-8");  
		PrintWriter out = response.getWriter();  
		//		System.out.println("�ļ���" + sales);
		//		System.out.println("����" + salesContentType);
		//		System.out.println("�ļ���" + salesFileName);

		if(sales!=null) {
			int sec = iSalesService.updateSales(sales);
			if(sec==1) {
				System.out.println("���³ɹ�");
				//					request.put("msg", "���³ɹ�,�������һ��������ˮ��");
				out.write("���³ɹ�,�������һ��������ˮ��");
				return null;
			}else{
				System.out.println("����ʧ��");
				//					request.put("errorMsg", "����ʧ�ܣ������Ƿ������¿���: <br>1:��ȷ�����ϴ�����������ˮExcel��� <br>"
				//							+ "2:�����Ƿ��� ������ ���۳��� ���� һģһ������");
				out.write( "����ʧ��!��ȷ�����ϴ�����������ˮExcel���");
				return null;
			}
		}

		/*  ����upload�ļ���
		File f = new File(getSavePath());
		if(!f.exists()) {										// �ж�·�������ļ��Ƿ����
			f.mkdirs();											// ���������  �ʹ��� ����������Ŀ¼
		}
		//�����ļ��ĵ�ַ
		FileOutputStream out = new FileOutputStream(getSavePath()+"\\"+salesFileName);
		//����ϴ��ļ���
		FileInputStream in = new FileInputStream(sales);
		//���ļ�д�������
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

	//  ���¿�����Ϣ
	public String updateReturns() throws IOException {
		HttpServletResponse response=ServletActionContext.getResponse();  
		response.setContentType("text/html;charset=utf-8");  
		response.setCharacterEncoding("UTF-8");  
		PrintWriter out = response.getWriter();  

//		System.out.println("�ļ���" + sales);
//		System.out.println("����" + salesContentType);
//		System.out.println("�ļ���" + salesFileName);
		if(sales!=null) {
			int sec = iReturnsService.updateReturns(sales);
			if(sec==1) {
				System.out.println("���³ɹ�");
				//				request.put("msg", "���³ɹ�,�������һ��������ˮ��");
				out.write("���¿������ݳɹ�!");
				return null;
			}
			if(sec==0) {
				System.out.println("����ʧ��");
				out.write( "����ʧ��!1:��ȷ�����ϴ����ǿ�����ˮExcel���2:��д���ֵı�������,���������д�۸�ĵ�Ԫ�� ֻ����д���֣�");
				return null;
			}

		}
		return null;
	}

//  ���¿����Ϣ
	public String updateStock() throws IOException {
		System.out.println(11111111);
		HttpServletResponse response=ServletActionContext.getResponse();  
		response.setContentType("text/html;charset=utf-8");  
		response.setCharacterEncoding("UTF-8");  
		PrintWriter out = response.getWriter();  
		
//		System.out.println("�ļ���" + sales);
//		System.out.println("����" + salesContentType);
//		System.out.println("�ļ���" + salesFileName);
	
		if(sales!=null) {
			int sec = iStockService.updateStock(sales);
			if(sec==1) {
				System.out.println("���³ɹ�");
				//				request.put("msg", "���³ɹ�,�������һ��������ˮ��");
				out.write("����ʵʱ���ɹ�!");
				return null;
			}
			if(sec==0) {
				System.out.println("����ʧ��");
				out.write( "����ʧ��! 1:��ȷ�����ϴ�����ʵʱ���Excel���2:��д���ֵı�������,���������д�۸�ĵ�Ԫ�� ֻ����д���֣�");
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
