package com.wd.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;
import com.wd.models.IndexCount;
import com.wd.models.TbBwl;
import com.wd.models.TbGg;
import com.wd.models.TbUser;
import com.wd.service.IIndexService;

@SuppressWarnings("serial")
public class IndexAction extends ActionSupport implements RequestAware,SessionAware{
	private String username;
	private String password;
	private int bwid;
	private IIndexService iIndexService;
	private Map<String, Object> request;
	private Map<String ,Object> session;
	private String content;
	//	����½��Ϣ
	private Integer ggid;
	public String checkUser() {
		List<TbUser> list = iIndexService.checkUser(username, password);
		if(list.size()==1) {
			for(TbUser t : list) {
				session.put("user",t.getUserName());
				session.put("power",t.getPower());
			}
			return SUCCESS;
		}else {
			request.put("errorMsg", "*��½ʧ��,�����û���������!");
			return ERROR;
		}
	}

	//  ��ҳ����¼
	public String getBwl() throws IOException {
		HttpServletResponse response=ServletActionContext.getResponse();  
		response.setContentType("text/html;charset=utf-8");  
		response.setCharacterEncoding("UTF-8");  
		PrintWriter out = response.getWriter();  
		List<TbBwl> list = iIndexService.getBwl();

		JSONArray json = new JSONArray();
		//  ����list
		for(TbBwl t : list){
			//  ����jsonMap
			JSONObject jo = new JSONObject();
			jo.put("id", t.getId());
			jo.put("content",t.getContent());
			jo.put("date", t.getTime());
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

	// ɾ������¼
	public void deleteBwl() throws IOException {
		HttpServletResponse response=ServletActionContext.getResponse();  
		response.setContentType("text/html;charset=utf-8");  
		response.setCharacterEncoding("UTF-8");  
		PrintWriter out = response.getWriter();  
		int sec = iIndexService.deleteBwl(bwid);
		if(sec==1) {
			out.write("1");
		}else {
			out.write("0");
		}
	}
	
	//  ���ӱ���¼
	public void addBwl() throws IOException {
		HttpServletResponse response=ServletActionContext.getResponse();  
		response.setContentType("text/html;charset=utf-8");  
		response.setCharacterEncoding("UTF-8");  
		PrintWriter out = response.getWriter();  
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String dateString = formatter.format(currentTime);
		System.out.println(content+" " + dateString);
		int sec = iIndexService.addBwl(content, dateString);
		if(sec==1) {
			out.write("1");
		}else {
			out.write("0");
		}
	}
	
	//  ��ʾ����
	public void showCount() throws IOException {
		HttpServletResponse response=ServletActionContext.getResponse();  
		response.setContentType("text/html;charset=utf-8");  
		response.setCharacterEncoding("UTF-8");  
		PrintWriter out = response.getWriter();  
		JSONArray json = new JSONArray();
	
		IndexCount index = iIndexService.showCount();
			//  ����jsonMap
			JSONObject jo = new JSONObject();
			jo.put("barCount", index.getBar());
			jo.put("specialCount",index.getSpecial());
			jo.put("stockCount", index.getStock());
			jo.put("salesCount", index.getSales());
			//  ����Arrary
			json.add(jo);
		try {
//				System.out.println(json.toString());
			// д���ͻ���
			out.write(json.toString());
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("jsonת���쳣");
		}
		out.flush();  
		out.close();  
	}
	
	//  ��ʾ����
	public void showGg() throws IOException {
		HttpServletResponse response=ServletActionContext.getResponse();  
		response.setContentType("text/html;charset=utf-8");  
		response.setCharacterEncoding("UTF-8");  
		PrintWriter out = response.getWriter();  
		JSONArray json = new JSONArray();
	
		List<TbGg> index = iIndexService.getGg();
			//  ����jsonMap
		for(TbGg t: index) {
			JSONObject jo = new JSONObject();
			jo.put("ggid", t.getId());
			jo.put("ggusername", t.getUsername());
			jo.put("ggcontent",t.getContent());
			jo.put("ggdate", t.getDate());
			//  ����Arrary
			json.add(jo);
		}
		try {
			//System.out.println(json.toString());
			// д���ͻ���
			out.write(json.toString());
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("jsonת���쳣");
		}
		out.flush();  
		out.close(); 
	}
	
	//���ӹ��淽��
	public void addGg() throws IOException {
		HttpServletResponse response=ServletActionContext.getResponse();  
		response.setContentType("text/html;charset=utf-8");  
		response.setCharacterEncoding("UTF-8");  
		PrintWriter out = response.getWriter();  
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateString = formatter.format(currentTime);
		int sec = iIndexService.addGg(username,content,dateString);
		
		if(sec==1) {
			out.write("1");
		}else {
			out.write("0");
		}
	}
	
	//ɾ�����淽��
	public void delGg() throws IOException {
			HttpServletResponse response=ServletActionContext.getResponse();  
			response.setContentType("text/html;charset=utf-8");  
			response.setCharacterEncoding("UTF-8");  
			PrintWriter out = response.getWriter();  
			System.out.println(username);
			int sec = iIndexService.delGg(ggid,username);
			if(sec==1) {
				out.write("1");
			}else {
				out.write("0");
			}
		}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public IIndexService getiIndexService() {
		return iIndexService;
	}
	public void setiIndexService(IIndexService iIndexService) {
		this.iIndexService = iIndexService;
	}
	@Override
	public void setRequest(Map<String, Object> arg0) {
		// TODO Auto-generated method stub
		this.request = arg0;
	}

	@Override
	public void setSession(Map<String, Object> arg0) {
		// TODO Auto-generated method stub
		this.session = arg0;
	}
	public int getBwid() {
		return bwid;
	}
	public void setBwid(int bwid) {
		this.bwid = bwid;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getGgid() {
		return ggid;
	}

	public void setGgid(Integer ggid) {
		this.ggid = ggid;
	}

}
