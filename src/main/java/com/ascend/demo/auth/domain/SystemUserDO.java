package com.ascend.demo.auth.domain;

import java.io.Serializable;
import java.util.Date;

public class SystemUserDO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5111583159334490155L;

	private long id;
	
	private String userAccount;
	
	private String userName;
	
	private int age;
	
	private String sex;
	
	private String email;
	
	private Date create_time;
	
	private String create_code;
	
	private String modify_code;
	
	private Date modify_time;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUserAccount() {
		return userAccount;
	}

	public void setUserAccount(String userAccount) {
		this.userAccount = userAccount;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCreate_code() {
		return create_code;
	}

	public void setCreate_code(String create_code) {
		this.create_code = create_code;
	}

	public String getModify_code() {
		return modify_code;
	}

	public void setModify_code(String modify_code) {
		this.modify_code = modify_code;
	}

	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}

	public Date getModify_time() {
		return modify_time;
	}

	public void setModify_time(Date modify_time) {
		this.modify_time = modify_time;
	}
	
	
}
