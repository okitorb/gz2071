package com.gec.query;

public class EmpQueryObject extends PageObject{
	private String name;//姓名
	private Integer dept_id;//部门号
	private Integer job_id;//职位
	private String card_id;//身份证号
	private String phone;//手机号码
	private Integer sex;//代号 判断性别
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getDept_id() {
		return dept_id;
	}
	public void setDept_id(Integer dept_id) {
		this.dept_id = dept_id;
	}
	public Integer getJob_id() {
		return job_id;
	}
	public void setJob_id(Integer job_id) {
		this.job_id = job_id;
	}
	public String getCard_id() {
		return card_id;
	}
	public void setCard_id(String card_id) {
		this.card_id = card_id;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Integer getSex() {
		return sex;
	}
	public void setSex(Integer sex) {
		this.sex = sex;
	}
	public EmpQueryObject(Integer pageSize, Integer pageIndex, String name, Integer dept_id, Integer job_id,
			String card_id, String phone, Integer sex) {
		super(pageSize, pageIndex);
		this.name = name;
		this.dept_id = dept_id;
		this.job_id = job_id;
		this.card_id = card_id;
		this.phone = phone;
		this.sex = sex;
	}
	public EmpQueryObject(Integer pageSize, Integer pageIndex) {
		super(pageSize, pageIndex);
	}
	public EmpQueryObject() {
		
	}
	
}
