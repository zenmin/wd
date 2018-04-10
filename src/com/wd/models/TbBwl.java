package com.wd.models;

/**
 * TbBwl entity. @author MyEclipse Persistence Tools
 */

public class TbBwl implements java.io.Serializable {

	// Fields

	private Integer id;
	private String content;
	private String time;

	// Constructors

	/** default constructor */
	public TbBwl() {
	}

	/** minimal constructor */
	public TbBwl(String content) {
		this.content = content;
	}

	/** full constructor */
	public TbBwl(String content, String time) {
		this.content = content;
		this.time = time;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getTime() {
		return this.time;
	}

	public void setTime(String time) {
		this.time = time;
	}

}