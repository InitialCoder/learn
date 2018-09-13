package com.ascend.demo.auth.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class SystemUserDO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5111583159334490155L;

	private String id;
	
	private String userAccount;
	
	private String userName;
	
	private String password;
	
	private int age;
	
	private String sex;
	
	private String email;
	
	private Date createTime;
	
	private String createCode;
	
	private String modifyCode;
	
	private Date modifyTime;
	
	private OneToOneDO one;
	
	private List<SystemAuthorizationDO> auths; 

	public String getId() {
		return id;
	}

	public void setId(String id) {
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
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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
	
	public List<SystemAuthorizationDO> getAuths() {
		return auths;
	}

	public void setAuths(List<SystemAuthorizationDO> auths) {
		this.auths = auths;
	}

	@Override
	public String toString() {
		return "SystemUserDO [id=" + id + ", userAccount=" + userAccount +  ", one=" + one + ", auths=" + auths
				+ "]";
	}

	
}
