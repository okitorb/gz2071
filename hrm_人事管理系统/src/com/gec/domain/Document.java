package com.gec.domain;

import java.util.Date;

public class Document {
	private Integer id;
	private Integer user_id;
	private String title;
	private String filename;
	private String filetype;
	private String remark;
	private byte[] filebytes;
	private Date create_date;
	private String username;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getUser_id() {
		return user_id;
	}
	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public String getFiletype() {
		return filetype;
	}
	public void setFiletype(String filetype) {
		this.filetype = filetype;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public byte[] getFilebytes() {
		return filebytes;
	}
	public void setFilebytes(byte[] filebytes) {
		this.filebytes = filebytes;
	}
	public Date getCreate_date() {
		return create_date;
	}
	public void setCreate_date(Date create_date) {
		this.create_date = create_date;
	}
	
	@Override
	public String toString() {
		return "Document [id=" + id + ", user_id=" + user_id + ", title=" + title + ", filename=" + filename
				+ ", filetype=" + filetype + ", remark=" + remark + ", filebytes=" + filebytes + ", create_date="
				+ create_date + ", username=" + username + "]";
	}
	
	public Document(Integer id, Integer user_id, String title, String filename, String filetype, String remark,
			byte[] filebytes, Date create_date, String username) {
		super();
		this.id = id;
		this.user_id = user_id;
		this.title = title;
		this.filename = filename;
		this.filetype = filetype;
		this.remark = remark;
		this.filebytes = filebytes;
		this.create_date = create_date;
		this.username = username;
	}
	public Document() {
		super();
	}
	
	

}
