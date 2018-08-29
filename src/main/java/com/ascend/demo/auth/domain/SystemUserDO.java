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
	
	private Date createTime;
	
	private String createCode;
	
	private String modifyCode;
	
	private Date modifyTime;
	
	private OneToOneDO one;

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

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getCreateCode() {
		return createCode;
	}

	public void setCreateCode(String createCode) {
		this.createCode = createCode;
	}

	public String getModifyCode() {
		return modifyCode;
	}

	public void setModifyCode(String modifyCode) {
		this.modifyCode = modifyCode;
	}

	public Date getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

	public OneToOneDO getOne() {
		return one;
	}

	public void setOne(OneToOneDO one) {
		this.one = one;
	}
	
	@Override
	public String toString() {
		
		return this.one.toString()+"==-="+this.userAccount;
	}
	
}
