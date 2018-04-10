package com.wd.models;


/**
 * TbStock entity. @author MyEclipse Persistence Tools
 */

public class TbStock implements java.io.Serializable {

	// Fields

	private Integer stockId;
	private String barNo;
	private String goods;
	private String barName;
	private String zone;
	private Integer stock;
	private String date;

	// Constructors

	/** default constructor */
	public TbStock() {
	}

	/** minimal constructor */
	public TbStock(String barNo, String goods, String barName, String zone,
			Integer stock) {
		this.barNo = barNo;
		this.goods = goods;
		this.barName = barName;
		this.zone = zone;
		this.stock = stock;
	}

	/** full constructor */
	public TbStock(String barNo, String goods, String barName, String zone,
			Integer stock, String date) {
		this.barNo = barNo;
		this.goods = goods;
		this.barName = barName;
		this.zone = zone;
		this.stock = stock;
		this.date = date;
	}

	// Property accessors

	public Integer getStockId() {
		return this.stockId;
	}

	public TbStock(Integer stockId) {
		super();
		this.stockId = stockId;
	}

	public void setStockId(Integer stockId) {
		this.stockId = stockId;
	}

	public String getBarNo() {
		return this.barNo;
	}

	public void setBarNo(String barNo) {
		this.barNo = barNo;
	}

	public String getGoods() {
		return this.goods;
	}

	public void setGoods(String goods) {
		this.goods = goods;
	}

	public String getBarName() {
		return this.barName;
	}

	public void setBarName(String barName) {
		this.barName = barName;
	}

	public String getZone() {
		return this.zone;
	}

	public void setZone(String zone) {
		this.zone = zone;
	}

	public Integer getStock() {
		return this.stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "TbStock [stockId=" + stockId + ", barNo=" + barNo + ", goods="
				+ goods + ", barName=" + barName + ", zone=" + zone
				+ ", stock=" + stock + ", date=" + date + "]";
	}

}