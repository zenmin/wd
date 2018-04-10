package com.wd.models;

/**
 * TbBar entity. @author MyEclipse Persistence Tools
 */

public class TbBar implements java.io.Serializable {

	// Fields

	private Integer barId;
	private TbGoods tbGoods;
	private String barNo;
	private Double barSaleprice;
	private Double barShowprice;
	private String barName;
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

	// Constructors

	/** default constructor */
	public TbBar() {
	}

	/** minimal constructor */
	public TbBar(TbGoods tbGoods, String barNo) {
		this.tbGoods = tbGoods;
		this.barNo = barNo;
	}

	public TbBar(String barNo) {
		super();
		this.barNo = barNo;
	}

	/** full constructor */
	public TbBar(TbGoods tbGoods, String barNo, Double barSaleprice,
			Double barShowprice, String barName, String barSimplename,
			String barColor, String barMaterial, String barSpecifications,
			String barStandard, String remarks, String rapidWear,
			String instructions, String packNo, String packCondition,
			String packSize, String isSize, Double scale, Double longs,
			Double widths, Double heights, String tabs, String terms,
			String alias) {
		this.tbGoods = tbGoods;
		this.barNo = barNo;
		this.barSaleprice = barSaleprice;
		this.barShowprice = barShowprice;
		this.barName = barName;
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

	// Property accessors

	public Integer getBarId() {
		return this.barId;
	}

	public void setBarId(Integer barId) {
		this.barId = barId;
	}

	public TbGoods getTbGoods() {
		return this.tbGoods;
	}

	public void setTbGoods(TbGoods tbGoods) {
		this.tbGoods = tbGoods;
	}

	public String getBarNo() {
		return this.barNo;
	}

	public void setBarNo(String barNo) {
		this.barNo = barNo;
	}

	public Double getBarSaleprice() {
		return this.barSaleprice;
	}

	public void setBarSaleprice(Double barSaleprice) {
		this.barSaleprice = barSaleprice;
	}

	public Double getBarShowprice() {
		return this.barShowprice;
	}

	public void setBarShowprice(Double barShowprice) {
		this.barShowprice = barShowprice;
	}

	public String getBarName() {
		return this.barName;
	}

	public void setBarName(String barName) {
		this.barName = barName;
	}

	public String getBarSimplename() {
		return this.barSimplename;
	}

	public void setBarSimplename(String barSimplename) {
		this.barSimplename = barSimplename;
	}

	public String getBarColor() {
		return this.barColor;
	}

	public void setBarColor(String barColor) {
		this.barColor = barColor;
	}

	public String getBarMaterial() {
		return this.barMaterial;
	}

	public void setBarMaterial(String barMaterial) {
		this.barMaterial = barMaterial;
	}

	public String getBarSpecifications() {
		return this.barSpecifications;
	}

	public void setBarSpecifications(String barSpecifications) {
		this.barSpecifications = barSpecifications;
	}

	public String getBarStandard() {
		return this.barStandard;
	}

	public void setBarStandard(String barStandard) {
		this.barStandard = barStandard;
	}

	public String getRemarks() {
		return this.remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getRapidWear() {
		return this.rapidWear;
	}

	public void setRapidWear(String rapidWear) {
		this.rapidWear = rapidWear;
	}

	public String getInstructions() {
		return this.instructions;
	}

	public void setInstructions(String instructions) {
		this.instructions = instructions;
	}

	public String getPackNo() {
		return this.packNo;
	}

	public void setPackNo(String packNo) {
		this.packNo = packNo;
	}

	public String getPackCondition() {
		return this.packCondition;
	}

	public void setPackCondition(String packCondition) {
		this.packCondition = packCondition;
	}

	public String getPackSize() {
		return this.packSize;
	}

	public void setPackSize(String packSize) {
		this.packSize = packSize;
	}

	public String getIsSize() {
		return this.isSize;
	}

	public void setIsSize(String isSize) {
		this.isSize = isSize;
	}

	public Double getScale() {
		return this.scale;
	}

	public void setScale(Double scale) {
		this.scale = scale;
	}

	public Double getLongs() {
		return this.longs;
	}

	public void setLongs(Double longs) {
		this.longs = longs;
	}

	public Double getWidths() {
		return this.widths;
	}

	public void setWidths(Double widths) {
		this.widths = widths;
	}

	public Double getHeights() {
		return this.heights;
	}

	public void setHeights(Double heights) {
		this.heights = heights;
	}

	public String getTabs() {
		return this.tabs;
	}

	public void setTabs(String tabs) {
		this.tabs = tabs;
	}

	public String getTerms() {
		return this.terms;
	}

	public void setTerms(String terms) {
		this.terms = terms;
	}

	public String getAlias() {
		return this.alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	@Override
	public String toString() {
		return "TbBar [barId=" + barId + ", tbGoods=" + tbGoods + ", barNo="
				+ barNo + ", barSaleprice=" + barSaleprice + ", barShowprice="
				+ barShowprice + ", barName=" + barName + ", barSimplename="
				+ barSimplename + ", barColor=" + barColor + ", barMaterial="
				+ barMaterial + ", barSpecifications=" + barSpecifications
				+ ", barStandard=" + barStandard + ", remarks=" + remarks
				+ ", rapidWear=" + rapidWear + ", instructions=" + instructions
				+ ", packNo=" + packNo + ", packCondition=" + packCondition
				+ ", packSize=" + packSize + ", isSize=" + isSize + ", scale="
				+ scale + ", longs=" + longs + ", widths=" + widths
				+ ", heights=" + heights + ", tabs=" + tabs + ", terms="
				+ terms + ", alias=" + alias + "]";
	}

}