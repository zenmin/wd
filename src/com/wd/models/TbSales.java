package com.wd.models;


/**
 * TbSales entity. @author MyEclipse Persistence Tools
 */

public class TbSales implements java.io.Serializable {

	// Fields

	private Long salesId;
	private String special;
	private String date;
	private String barNo;
	private String zone;
	private Double salesStock;
	private Double salesSoldnum;
	private Double salesSoldmoney;
	private Double salesPeople;
	private Integer salesNum;

	// Constructors

	/** default constructor */
	public TbSales() {
	}

	/** full constructor */
	public TbSales(String special, String date, String barNo, String zone,
			Double salesStock, Double salesSoldnum, Double salesSoldmoney,
			Double salesPeople, Integer salesNum) {
		this.special = special;
		this.date = date;
		this.barNo = barNo;
		this.zone = zone;
		this.salesStock = salesStock;
		this.salesSoldnum = salesSoldnum;
		this.salesSoldmoney = salesSoldmoney;
		this.salesPeople = salesPeople;
		this.salesNum = salesNum;
	}

	// Property accessors

	public TbSales(Long salesId) {
		super();
		this.salesId = salesId;
	}

	public Long getSalesId() {
		return this.salesId;
	}

	public void setSalesId(Long salesId) {
		this.salesId = salesId;
	}

	public String getSpecial() {
		return this.special;
	}

	public void setSpecial(String special) {
		this.special = special;
	}

	public String getDate() {
		return this.date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getBarNo() {
		return this.barNo;
	}

	public void setBarNo(String barNo) {
		this.barNo = barNo;
	}

	public String getZone() {
		return this.zone;
	}

	public void setZone(String zone) {
		this.zone = zone;
	}

	public Double getSalesStock() {
		return this.salesStock;
	}

	public void setSalesStock(Double salesStock) {
		this.salesStock = salesStock;
	}

	public Double getSalesSoldnum() {
		return this.salesSoldnum;
	}

	public void setSalesSoldnum(Double salesSoldnum) {
		this.salesSoldnum = salesSoldnum;
	}

	public Double getSalesSoldmoney() {
		return this.salesSoldmoney;
	}

	public void setSalesSoldmoney(Double salesSoldmoney) {
		this.salesSoldmoney = salesSoldmoney;
	}

	public Double getSalesPeople() {
		return this.salesPeople;
	}

	public void setSalesPeople(Double salesPeople) {
		this.salesPeople = salesPeople;
	}

	public Integer getSalesNum() {
		return this.salesNum;
	}

	public void setSalesNum(Integer salesNum) {
		this.salesNum = salesNum;
	}

	@Override
	public String toString() {
		return "TbSales [salesId=" + salesId + ", special=" + special
				+ ", date=" + date + ", barNo=" + barNo + ", zone=" + zone
				+ ", salesStock=" + salesStock + ", salesSoldnum="
				+ salesSoldnum + ", salesSoldmoney=" + salesSoldmoney
				+ ", salesPeople=" + salesPeople + ", salesNum=" + salesNum
				+ "]";
	}

}