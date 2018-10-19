package com.ascend.demo.mgr.auth.condition;

import java.util.Date;

public class UserCondition {

	private String userAccount;
	
	private int age;
	
	private String createCode;
	
	private String modifyCode;
	
	private Date modifyTime;

	public String getUserAccount() {
		return userAccount;
	}

	public void setUserAccount(String userAccount) {
		this.userAccount = userAccount;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
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
	
}
