package com.wd.models;

/**
 * TbGg entity. @author MyEclipse Persistence Tools
 */

public class TbGg implements java.io.Serializable {

	// Fields

	private Integer id;
	private String username;
	private String content;
	private String date;

	// Constructors

	/** default constructor */
	public TbGg() {
	}

	/** minimal constructor */
	public TbGg(String username, String content) {
		this.username = username;
		this.content = content;
	}

	/** full constructor */
	public TbGg(String username, String content, String date) {
		this.username = username;
		this.content = content;
		this.date = date;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getDate() {
		return this.date;
	}

	public void setDate(String date) {
		this.date = date;
	}

}