package com.wd.models;

import java.util.HashSet;
import java.util.Set;

/**
 * TbSpecial entity. @author MyEclipse Persistence Tools
 */

public class TbSpecial implements java.io.Serializable {

	// Fields

	private Integer specialId;
	private String special;
	private String specialName;
	private Set tbSaleses = new HashSet(0);

	// Constructors

	/** default constructor */
	public TbSpecial() {
	}

	/** minimal constructor */
//	public TbSpecial(String special) {
//		this.special = special;
//	}

	/** full constructor */

	// Property accessors

	public void TbSpecial1(String specialName) {
		this.specialName = specialName;
	}

	public TbSpecial(String specialName) {
		super();
		this.specialName = specialName;
	}

	public Integer getSpecialId() {
		return this.specialId;
	}

	public void setSpecialId(Integer specialId) {
		this.specialId = specialId;
	}

	public String getSpecial() {
		return this.special;
	}

	public void setSpecial(String special) {
		this.special = special;
	}

	public String getSpecialName() {
		return this.specialName;
	}

	public void setSpecialName(String specialName) {
		this.specialName = specialName;
	}

	public Set getTbSaleses() {
		return this.tbSaleses;
	}

	public void setTbSaleses(Set tbSaleses) {
		this.tbSaleses = tbSaleses;
	}

	@Override
	public String toString() {
		return "TbSpecial [specialId=" + specialId + ", special=" + special
				+ ", specialName=" + specialName + "]";
	}

}