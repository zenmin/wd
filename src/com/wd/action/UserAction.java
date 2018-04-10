package com.wd.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.wd.models.TbSpecial;
import com.wd.models.TbUser;
import com.wd.service.IUserService;

public class UserAction extends ActionSupport implements SessionAware,RequestAware{
	private String username;
	private String password;
	private String password1;
	private String finduser;
	private String power;
	private Integer id;
	private IUserService iUserService;
	private Map session;
	private Map request;
	private int nowPage;
	private String table;
	public void alterPass() throws IOException {
		System.out.println(username);
		System.out.println(password);
		System.out.println(password1);
		HttpServletResponse response=ServletActionContext.getResponse();  
		response.setContentType("text/html;charset=utf-8");  
		response.setCharacterEncoding("UTF-8");  
		PrintWriter out = response.getWriter();  
		int sec =  iUserService.alterUser(username, password,password1);
		if(sec==1) {
			session.remove("user");
			session.remove("power");
			out.write("1");
		}
		if(sec==0) {
			out.write("0");
		}
		if(sec==2) {
			out.write("2");
		}
		out.flush();  
		out.close();  
	}
	
	//  注销
	public String removeUser() {
		session.remove("user");
		session.remove("power");
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();		//ServletActionContext.getRequest().GETsESSION();
		session.invalidate();
		return SUCCESS;
	}
	
	public void getAllUser() throws IOException {
		HttpServletResponse response=ServletActionContext.getResponse();  
		response.setContentType("text/html;charset=utf-8");  
		response.setCharacterEncoding("UTF-8");  
		PrintWriter out = response.getWriter();  
		List<TbUser> list = iUserService.getAllUser(finduser,nowPage);
		JSONArray json = new JSONArray();
		//  遍历list
		for(TbUser sp : list){
			//  创建jsonMap
			JSONObject jo = new JSONObject();
			jo.put("id", sp.getUserId());
			jo.put("name", sp.getUserName());
			jo.put("power", sp.getPower());
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
		}
		out.flush();  
		out.close();  
	}
	
	// 新增用户
	public void addUser() throws IOException {
		HttpServletResponse response=ServletActionContext.getResponse();  
		response.setContentType("text/html;charset=utf-8");  
		response.setCharacterEncoding("UTF-8");  
		PrintWriter out = response.getWriter();  
		int sec = iUserService.addUser(username, password, power);
		if(sec==1) {
			out.write("1");
		}else {
			out.write("0");
		}
	}
	
	public void delUser() throws IOException {
		HttpServletResponse response=ServletActionContext.getResponse();  
		response.setContentType("text/html;charset=utf-8");  
		response.setCharacterEncoding("UTF-8");  
		PrintWriter out = response.getWriter();  
		int sec = iUserService.delUser(id);
		if(sec==1) {
			out.write("1");
		}else {
			out.write("0");
		}
	}
	
	public void removeData() throws IOException {
		HttpServletResponse response=ServletActionContext.getResponse();  
		response.setContentType("text/html;charset=utf-8");  
		response.setCharacterEncoding("UTF-8");  
		PrintWriter out = response.getWriter();  
		int sec = iUserService.delData("tb_" + table);
		
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


	public IUserService getiUserService() {
		return iUserService;
	}


	public void setiUserService(IUserService iUserService) {
		this.iUserService = iUserService;
	}
	public String getPassword1() {
		return password1;
	}
	public void setPassword1(String password1) {
		this.password1 = password1;
	}

	public String getFinduser() {
		return finduser;
	}
	public void setFinduser(String finduser) {
		this.finduser = finduser;
	}
	@Override
	public void setSession(Map<String, Object> arg0) {
		// TODO Auto-generated method stub
		this.session = arg0;
	}
	public String getPower() {
		return power;
	}
	public void setPower(String power) {
		this.power = power;
	}
	public Map getRequest() {
		return request;
	}
	public void setRequest(Map request) {
		this.request = request;
	}
	public int getNowPage() {
		return nowPage;
	}
	public void setNowPage(int nowPage) {
		this.nowPage = nowPage;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTable() {
		return table;
	}
	public void setTable(String table) {
		this.table = table;
	}
}
