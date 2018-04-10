package com.wd.action;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.Response;

import net.sf.json.JSONArray;

import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.wd.dao.impl.ShowBarDAO;
import com.wd.service.IShowBarService;
import com.wd.utils.ExportBar;
import com.wd.utils.Page;
import com.wd.utils.Project;

public class ShowBarAction extends ActionSupport implements RequestAware, ServletResponseAware,SessionAware{

	private Map<String, Object>request;
	private Map<String, Object>session;
	private HttpServletResponse response;

	private IShowBarService showBarService;
	
	private ExportBar exportBar;

	//	private Map<Integer, Object> project;

	private String contition;//选项信息
	private String contitionValue;//No信息
	private Map<Integer, String> mapOwners = new HashMap<Integer, String>();//即将传去前台的小组信息
	private List owners;//接受service来的小组信息
	private int ownerIndex;//接收页面传来的小组信息
	
	private Map<Integer, String> mapClass = new HashMap<Integer, String>();//即将传去前台的小组信息
	private List classS;//接受service来的小组信息
	private int classIndex;//接收页面传来的小组信息
	
	private String barNo;//接收页面的barNo

	private long currentPage; //当前页面
	private long arrivePage;//目标页
	private Page page;//页面类

	//修改所需要的列
	private Integer barId;
	private Integer goodsId;
	private String goodsNo;
	private String Owner;
	private Double goodsPrice;
	private Double barSaleprice;
	private Double barShowprice;
	private String barSimplename;
	private String barColor;
	private String barMaterial;
	private String barSpecifications;
	private String barStandard;
	
	private String remarks;
	private String rapidWear;
	private String instructions;
	private String packNo;
	private String packCondition;
	private String packSize;
	private String isSize;
	private Double scale;
	private Double longs;
	private Double widths;
	private Double heights;
	private String tabs;
	private String terms;
	private String alias;
	
	private InputStream downloadBarFile;
	private String downloadName;


	private static final long serialVersionUID = 1L;

	//初始化产品资料
	public String findBar(){
		//获取小组信息
		owners = showBarService.findOwner();
		mapOwners.put(0, "全部");//手动添加 全部 选项
		for(int i=0;owners.size()>i;i++){
			mapOwners.put(i+1, (String) owners.get(i));
		}
		request.put("mapOwners",mapOwners);
		//获取类目信息
		classS = showBarService.findClass();
//		mapClass.put(0, "类目");//手动添加 类目 选项
		for(int i=0;classS.size()>i;i++){
			mapClass.put(i, (String) classS.get(i));
		}
		request.put("mapClass",mapClass);
		return SUCCESS;
	}
//	//获取类目
//		public String findClass(){
//			classS = showBarService.findClass();
////			mapClass.put(0, "类目");//手动添加 类目 选项
//			for(int i=0;owners.size()>i;i++){
//				mapClass.put(i, (String) classS.get(i));
//			}
//			request.put("mapClass",mapClass);
//			return SUCCESS;
//		}

	//查询产品
	public String findProject() throws IOException{
		response.setContentType("text/html;charset=utf-8");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		String power = String.valueOf(session.get("power"));	//从session中获取权限
		
		JSONArray array = new JSONArray();//声明一个JSONArray （数据结构和List一样）
		try{
			array = showBarService.findProject(contition, contitionValue, mapOwners.get(ownerIndex), mapClass.get(classIndex), arrivePage,power);
			System.out.println(array);
			if(array.size() == 0){
				request.put("nullPoint", "没有查询到该记录，请检查条件是否正确！");
				return null;
			}
		}catch(Exception nullPoint){
			request.put("nullPoint", "没有查询到该记录，请检查条件是否正确！");
			return null;
		}
		//利用JSONArray 的fromObject方法来将Object对像转换为JSON数组
		out.write(array.toString());
		out.flush();
		out.close();

		return null;
	}
	//查询产品并导出
	public String findProjectAndExport() throws IOException{
		List array = new ArrayList();
		try{
			array = showBarService.findProjectAndExport(contition, contitionValue, mapOwners.get(ownerIndex), mapClass.get(classIndex));
			if(array.size() == 0){
				request.put("nullPoint", "没有查询到该记录，请检查条件是否正确！");
				return ERROR;
			}
			downloadBarFile=exportBar.createExcel(array);
			if(downloadBarFile == null){
				request.put("msg","<script>alert('没有找到该记录，请查看填写的条件是否有误。 即将导出的产品信息不存在!')</script>");
				return ERROR;
			}
			SimpleDateFormat formatter = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
			Date date = new Date();
			String time = formatter.format(date).trim();
			String fileName = "产品资料导出"+time+".xlsx";
			downloadName = new String(fileName.getBytes(), "ISO_8859_1"); 
			this.setDownloadName(downloadName);
		}catch(Exception nullPoint){
			request.put("nullPoint", "没有查询到该记录，请检查条件是否正确！");
			return ERROR;
		}
		

		return "success";
	}

	//通过barNo删除表
	public void deleteBar() throws IOException {
		response.setContentType("text/html;charset=utf-8");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		int sec = showBarService.deleteBar(barNo);
		if(sec>0) {
			out.write("1");
		}else {
			out.write("0");
		}
	}

	//	//页数查询
	//	public void PageSize(){
	//		//获取页面
	//		page = showBarService.totalPageSize(contition, mapOwners.get(ownerIndex));
	//		if(page != null){
	//			System.out.println(page);
	//			request.put("page", page);
	//		}
	//	}

	//通过BarNo查询
	public String findByBarNo(){
		List<Project> projects = showBarService.ByBarNo(barNo);
		for(Project project:projects){
			request.put("barId", project.getBarId());
			request.put("owner", project.getOwner());
			request.put("project", project);
			System.out.println(project);
		}
		return SUCCESS;
	}
	//通过barNo修改表
	public String alter(){
		Project project = new Project(goodsId, mapOwners.get(ownerIndex), goodsPrice, goodsNo, barId,
				barNo, barSaleprice, barShowprice, barSimplename, barColor,
				barMaterial, barSpecifications, barStandard, remarks, 
				rapidWear, instructions, packNo, packCondition, packSize,
				isSize, scale, longs, widths, heights, tabs, terms, alias);
				
		showBarService.alter(project);
		return SUCCESS;
	}
	


	public IShowBarService getShowBarService() {
		return showBarService;
	}

	public void setShowBarService(IShowBarService showBarService) {
		this.showBarService = showBarService;
	}


	public Map<String, Object> getRequest() {
		return request;
	}

	public void setRequest(Map<String, Object> request) {
		this.request = request;
	}
	public String getContitionValue() {
		return contitionValue;
	}
	public void setContitionValue(String contitionValue) {
		this.contitionValue = contitionValue;
	}

	public String getContition() {
		return contition;
	}

	public void setContition(String contition) {
		this.contition = contition;
	}

	public Map<Integer, String> getMapOwners() {
		return mapOwners;
	}

	public void setMapOwners(Map<Integer, String> mapOwners) {
		this.mapOwners = mapOwners;
	}

	public List getOwners() {
		return owners;
	}

	public void setOwners(List owners) {
		this.owners = owners;
	}


	public int getOwnerIndex() {
		return ownerIndex;
	}

	public void setOwnerIndex(int ownerIndex) {
		this.ownerIndex = ownerIndex;
	}

	@Override
	public void setServletResponse(HttpServletResponse arg0) {
		this.response = arg0;
	}

	public String getBarNo() {
		return barNo;
	}

	public void setBarNo(String barNo) {
		this.barNo = barNo;
	}

	public Integer getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(Integer goodsId) {
		this.goodsId = goodsId;
	}

	public Double getGoodsPrice() {
		return goodsPrice;
	}

	public void setGoodsPrice(Double goodsPrice) {
		this.goodsPrice = goodsPrice;
	}

	public Double getBarSaleprice() {
		return barSaleprice;
	}

	public void setBarSaleprice(Double barSaleprice) {
		this.barSaleprice = barSaleprice;
	}

	public Double getBarShowprice() {
		return barShowprice;
	}

	public void setBarShowprice(Double barShowprice) {
		this.barShowprice = barShowprice;
	}

	public String getBarSimplename() {
		return barSimplename;
	}

	public void setBarSimplename(String barSimplename) {
		this.barSimplename = barSimplename;
	}

	public String getBarColor() {
		return barColor;
	}

	public void setBarColor(String barColor) {
		this.barColor = barColor;
	}

	public String getBarMaterial() {
		return barMaterial;
	}

	public void setBarMaterial(String barMaterial) {
		this.barMaterial = barMaterial;
	}

	public String getBarSpecifications() {
		return barSpecifications;
	}

	public void setBarSpecifications(String barSpecifications) {
		this.barSpecifications = barSpecifications;
	}

	public String getBarStandard() {
		return barStandard;
	}

	public void setBarStandard(String barStandard) {
		this.barStandard = barStandard;
	}

	public void setOwner(String owner) {
		Owner = owner;
	}

	public String getOwner() {
		return Owner;
	}

	public String getGoodsNo() {
		return goodsNo;
	}

	public void setGoodsNo(String goodsNo) {
		this.goodsNo = goodsNo;
	}

	public Integer getBarId() {
		return barId;
	}

	public void setBarId(Integer barId) {
		this.barId = barId;
	}


	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

	public long getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(long currentPage) {
		this.currentPage = currentPage;
	}

	public long getArrivePage() {
		return arrivePage;
	}

	public void setArrivePage(long arrivePage) {
		this.arrivePage = arrivePage;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getRapidWear() {
		return rapidWear;
	}

	public void setRapidWear(String rapidWear) {
		this.rapidWear = rapidWear;
	}

	public String getInstructions() {
		return instructions;
	}

	public void setInstructions(String instructions) {
		this.instructions = instructions;
	}

	public String getPackNo() {
		return packNo;
	}

	public void setPackNo(String packNo) {
		this.packNo = packNo;
	}

	public String getPackCondition() {
		return packCondition;
	}

	public void setPackCondition(String packCondition) {
		this.packCondition = packCondition;
	}

	public String getPackSize() {
		return packSize;
	}

	public void setPackSize(String packSize) {
		this.packSize = packSize;
	}

	public String getIsSize() {
		return isSize;
	}

	public void setIsSize(String isSize) {
		this.isSize = isSize;
	}

	public Double getScale() {
		return scale;
	}

	public void setScale(Double scale) {
		this.scale = scale;
	}

	public Double getLongs() {
		return longs;
	}

	public void setLongs(Double longs) {
		this.longs = longs;
	}

	public Double getWidths() {
		return widths;
	}

	public void setWidths(Double widths) {
		this.widths = widths;
	}

	public Double getHeights() {
		return heights;
	}

	public void setHeights(Double heights) {
		this.heights = heights;
	}

	public String getTabs() {
		return tabs;
	}

	public void setTabs(String tabs) {
		this.tabs = tabs;
	}

	public String getTerms() {
		return terms;
	}

	public void setTerms(String terms) {
		this.terms = terms;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public InputStream getDownloadBarFile() {
		return downloadBarFile;
	}

	public void setDownloadBarFile(InputStream downloadBarFile) {
		this.downloadBarFile = downloadBarFile;
	}

	public String getDownloadName() {
		return downloadName;
	}

	public void setDownloadName(String downloadName) {
		this.downloadName = downloadName;
	}

	public List getClassS() {
		return classS;
	}

	public void setClassS(List classS) {
		this.classS = classS;
	}

	public int getClassIndex() {
		return classIndex;
	}

	public void setClassIndex(int classIndex) {
		this.classIndex = classIndex;
	}

	public Map<Integer, String> getMapClass() {
		return mapClass;
	}

	public void setMapClass(Map<Integer, String> mapClass) {
		this.mapClass = mapClass;
	}

	@Override
	public void setSession(Map<String, Object> arg0) {
		// TODO Auto-generated method stub
		this.session = arg0;
	}


	
}
