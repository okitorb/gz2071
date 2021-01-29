package com.gec.domain;

import java.util.Date;

public class Employee {
	private Integer id;
	private Integer dept_id;//部门号
	private Integer job_id;//职位
	private String name;
	private String card_id;//身份证号
	private String address;//地址
	private String post_code;//邮政编号
	private String tel;//固定电话
	private String phone;//手机号码
	private String qq_num;//qq号
	private String email;
	private Integer sex;//代号 判断性别
	private String party;//政党 政治面貌
	private Date birthday;
	private String race;//民族
	private String education;//学历
	private String speciality;//专业
	private String hobby;//爱好
	private String remark;//备注
	private Date create_date;//创建时间
	private String dept_name;
	private String job_name;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCard_id() {
		return card_id;
	}
	public void setCard_id(String card_id) {
		this.card_id = card_id;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPost_code() {
		return post_code;
	}
	public void setPost_code(String post_code) {
		this.post_code = post_code;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getQq_num() {
		return qq_num;
	}
	public void setQq_num(String qq_num) {
		this.qq_num = qq_num;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Integer getSex() {
		return sex;
	}
	public void setSex(Integer sex) {
		this.sex = sex;
	}
	public String getParty() {
		return party;
	}
	public void setParty(String party) {
		this.party = party;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public String getRace() {
		return race;
	}
	public void setRace(String race) {
		this.race = race;
	}
	public String getEducation() {
		return education;
	}
	public void setEducation(String education) {
		this.education = education;
	}
	public String getSpeciality() {
		return speciality;
	}
	public void setSpeciality(String speciality) {
		this.speciality = speciality;
	}
	public String getHobby() {
		return hobby;
	}
	public void setHobby(String hobby) {
		this.hobby = hobby;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Date getCreate_date() {
		return create_date;
	}
	public void setCreate_date(Date create_date) {
		this.create_date = create_date;
	}
	
	public String getDept_name() {
		return dept_name;
	}
	public void setDept_name(String dept_name) {
		this.dept_name = dept_name;
	}
	public String getJob_name() {
		return job_name;
	}
	public void setJob_name(String job_name) {
		this.job_name = job_name;
	}
	
	public Employee() {
		super();
	}
	@Override
	public String toString() {
		return "Employee [id=" + id + ", dept_id=" + dept_id + ", job_id=" + job_id + ", name=" + name + ", card_id="
				+ card_id + ", address=" + address + ", post_code=" + post_code + ", tel=" + tel + ", phone=" + phone
				+ ", qq_num=" + qq_num + ", email=" + email + ", sex=" + sex + ", party=" + party + ", birthday="
				+ birthday + ", race=" + race + ", education=" + education + ", speciality=" + speciality + ", hobby="
				+ hobby + ", remark=" + remark + ", create_date=" + create_date + ", dept_name=" + dept_name
				+ ", job_name=" + job_name + "]";
	}
	public Employee(Integer id, Integer dept_id, Integer job_id, String name, String card_id, String address,
			String post_code, String tel, String phone, String qq_num, String email, Integer sex, String party,
			Date birthday, String race, String education, String speciality, String hobby, String remark,
			Date create_date, String dept_name, String job_name) {
		super();
		this.id = id;
		this.dept_id = dept_id;
		this.job_id = job_id;
		this.name = name;
		this.card_id = card_id;
		this.address = address;
		this.post_code = post_code;
		this.tel = tel;
		this.phone = phone;
		this.qq_num = qq_num;
		this.email = email;
		this.sex = sex;
		this.party = party;
		this.birthday = birthday;
		this.race = race;
		this.education = education;
		this.speciality = speciality;
		this.hobby = hobby;
		this.remark = remark;
		this.create_date = create_date;
		this.dept_name = dept_name;
		this.job_name = job_name;
	}
	
	
}
