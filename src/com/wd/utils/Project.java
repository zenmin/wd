package com.wd.utils;

public class Project {

	private Integer goodsId;
	private String Owner;
	private Double goodsPrice;
	private String goodsNo;
	private Integer barId;
	private String barNo;
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
	public Integer getBarId() {
		return barId;
	}
	public void setBarId(Integer barId) {
		this.barId = barId;
	}
	public Integer getGoodsId() {
		return goodsId;
	}
	public void setGoodsId(Integer goodsId) {
		this.goodsId = goodsId;
	}
	
	public String getGoodsNo() {
		return goodsNo;
	}
	public void setGoodsNo(String goodsNo) {
		this.goodsNo = goodsNo;
	}
	public String getOwner() {
		return Owner;
	}
	public void setOwner(String owner) {
		Owner = owner;
	}
	public Double getGoodsPrice() {
		return goodsPrice;
	}
	public void setGoodsPrice(Double goodsPrice) {
		this.goodsPrice = goodsPrice;
	}
	public String getBarNo() {
		return barNo;
	}
	public void setBarNo(String barNo) {
		this.barNo = barNo;
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
	
	public Project() {
		super();
	}
	public Project(Integer goodsId, String owner, Double goodsPrice,
			String goodsNo, Integer barId, String barNo, Double barSaleprice,
			Double barShowprice, String barSimplename, String barColor,
			String barMaterial, String barSpecifications, String barStandard,
			String remarks, String rapidWear, String instructions,
			String packNo, String packCondition, String packSize,
			String isSize, Double scale, Double longs, Double widths,
			Double heights, String tabs, String terms, String alias) {
		super();
		this.goodsId = goodsId;
		Owner = owner;
		this.goodsPrice = goodsPrice;
		this.goodsNo = goodsNo;
		this.barId = barId;
		this.barNo = barNo;
		this.barSaleprice = barSaleprice;
		this.barShowprice = barShowprice;
		this.barSimplename = barSimplename;
		this.barColor = barColor;
		this.barMaterial = barMaterial;
		this.barSpecifications = barSpecifications;
		this.barStandard = barStandard;
		this.remarks = remarks;
		this.rapidWear = rapidWear;
		this.instructions = instructions;
		this.packNo = packNo;
		this.packCondition = packCondition;
		this.packSize = packSize;
		this.isSize = isSize;
		this.scale = scale;
		this.longs = longs;
		this.widths = widths;
		this.heights = heights;
		this.tabs = tabs;
		this.terms = terms;
		this.alias = alias;
	}


}
