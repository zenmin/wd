package com.wd.models;

import java.util.HashSet;
import java.util.Set;

/**
 * TbClass entity. @author MyEclipse Persistence Tools
 */

public class TbClass implements java.io.Serializable {

	// Fields

	private Integer goodsClassId;
	private String goodsClassName;
	private Integer fatherId;
	private Integer classLevel;
	private Set tbGoodses = new HashSet(0);

	// Constructors

	/** default constructor */
	public TbClass() {
	}

	/** minimal constructor */
	public TbClass(String goodsClassName, Integer fatherId, Integer classLevel) {
		this.goodsClassName = goodsClassName;
		this.fatherId = fatherId;
		this.classLevel = classLevel;
	}

	/** full constructor */
	public TbClass(String goodsClassName, Integer fatherId, Integer classLevel,
			Set tbGoodses) {
		this.goodsClassName = goodsClassName;
		this.fatherId = fatherId;
		this.classLevel = classLevel;
		this.tbGoodses = tbGoodses;
	}

	// Property accessors

	public Integer getGoodsClassId() {
		return this.goodsClassId;
	}

	public void setGoodsClassId(Integer goodsClassId) {
		this.goodsClassId = goodsClassId;
	}

	public String getGoodsClassName() {
		return this.goodsClassName;
	}

	public void setGoodsClassName(String goodsClassName) {
		this.goodsClassName = goodsClassName;
	}

	public Integer getFatherId() {
		return this.fatherId;
	}

	public void setFatherId(Integer fatherId) {
		this.fatherId = fatherId;
	}

	public Integer getClassLevel() {
		return this.classLevel;
	}

	public void setClassLevel(Integer classLevel) {
		this.classLevel = classLevel;
	}

	public Set getTbGoodses() {
		return this.tbGoodses;
	}

	public void setTbGoodses(Set tbGoodses) {
		this.tbGoodses = tbGoodses;
	}

}