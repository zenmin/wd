package com.wd.models;

/**
 * TbReturns entity. @author MyEclipse Persistence Tools
 */

public class TbReturns implements java.io.Serializable {

	// Fields

	private Long returnsId;
	private String special;
	private String date;
	private String barNo;
	private String goodsNo;
	private String returnsReason;
	private Integer returnsReturnnum;
	private Integer returnsRejectnum;
	private Double returnrate;

	// Constructors

	/** default constructor */
	public TbReturns() {
	}

	/** minimal constructor */
	public TbReturns(String barNo) {
		this.barNo = barNo;
	}

	/** full constructor */
	public TbReturns(String special, String date, String barNo, String goodsNo,
			String returnsReason, Integer returnsReturnnum,
			Integer returnsRejectnum, Double returnrate) {
		this.special = special;
		this.date = date;
		this.barNo = barNo;
		this.goodsNo = goodsNo;
		this.returnsReason = returnsReason;
		this.returnsReturnnum = returnsReturnnum;
		this.returnsRejectnum = returnsRejectnum;
		this.returnrate = returnrate;
	}

	// Property accessors

	public Long getReturnsId() {
		return this.returnsId;
	}

	public void setReturnsId(Long returnsId) {
		this.returnsId = returnsId;
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

	public String getGoodsNo() {
		return this.goodsNo;
	}

	public void setGoodsNo(String goodsNo) {
		this.goodsNo = goodsNo;
	}

	public String getReturnsReason() {
		return this.returnsReason;
	}

	public void setReturnsReason(String returnsReason) {
		this.returnsReason = returnsReason;
	}

	public Integer getReturnsReturnnum() {
		return this.returnsReturnnum;
	}

	public void setReturnsReturnnum(Integer returnsReturnnum) {
		this.returnsReturnnum = returnsReturnnum;
	}

	public Integer getReturnsRejectnum() {
		return this.returnsRejectnum;
	}

	public void setReturnsRejectnum(Integer returnsRejectnum) {
		this.returnsRejectnum = returnsRejectnum;
	}

	public Double getReturnrate() {
		return this.returnrate;
	}

	public void setReturnrate(Double returnrate) {
		this.returnrate = returnrate;
	}

}