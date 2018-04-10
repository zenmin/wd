package com.wd.models;

import java.util.HashSet;
import java.util.Set;

/**
 * TbGoods entity. @author MyEclipse Persistence Tools
 */

public class TbGoods implements java.io.Serializable {

	// Fields

	private Integer goodsId;
	private TbClass tbClass;
	private String goodsNo;
	private Double goodsPrice;
	private String goodsOwner;
	private String classOne;
	private String classTwo;
	private String classThree;
	private String classFour;
	private String class_;
	private Set tbBars = new HashSet(0);

	// Constructors

	/** default constructor */
	public TbGoods() {
	}

	/** minimal constructor */
	public TbGoods(String goodsNo) {
		this.goodsNo = goodsNo;
	}

	/** full constructor */
	public TbGoods(TbClass tbClass, String goodsNo, Double goodsPrice,
			String goodsOwner, String classOne, String classTwo,
			String classThree, String classFour, String class_, Set tbBars) {
		this.tbClass = tbClass;
		this.goodsNo = goodsNo;
		this.goodsPrice = goodsPrice;
		this.goodsOwner = goodsOwner;
		this.classOne = classOne;
		this.classTwo = classTwo;
		this.classThree = classThree;
		this.classFour = classFour;
		this.class_ = class_;
		this.tbBars = tbBars;
	}

	// Property accessors

	public Integer getGoodsId() {
		return this.goodsId;
	}

	public void setGoodsId(Integer goodsId) {
		this.goodsId = goodsId;
	}

	public TbClass getTbClass() {
		return this.tbClass;
	}

	public void setTbClass(TbClass tbClass) {
		this.tbClass = tbClass;
	}

	public String getGoodsNo() {
		return this.goodsNo;
	}

	public void setGoodsNo(String goodsNo) {
		this.goodsNo = goodsNo;
	}

	public Double getGoodsPrice() {
		return this.goodsPrice;
	}

	public void setGoodsPrice(Double goodsPrice) {
		this.goodsPrice = goodsPrice;
	}

	public String getGoodsOwner() {
		return this.goodsOwner;
	}

	public void setGoodsOwner(String goodsOwner) {
		this.goodsOwner = goodsOwner;
	}

	public String getClassOne() {
		return this.classOne;
	}

	public void setClassOne(String classOne) {
		this.classOne = classOne;
	}

	public String getClassTwo() {
		return this.classTwo;
	}

	public void setClassTwo(String classTwo) {
		this.classTwo = classTwo;
	}

	public String getClassThree() {
		return this.classThree;
	}

	public void setClassThree(String classThree) {
		this.classThree = classThree;
	}

	public String getClassFour() {
		return this.classFour;
	}

	public void setClassFour(String classFour) {
		this.classFour = classFour;
	}

	public String getClass_() {
		return this.class_;
	}

	public void setClass_(String class_) {
		this.class_ = class_;
	}

	public Set getTbBars() {
		return this.tbBars;
	}

	public void setTbBars(Set tbBars) {
		this.tbBars = tbBars;
	}

}