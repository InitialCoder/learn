package com.ascend.demo.common.domain;

import javax.persistence.Table;

/**
 * 用户--角色中间表
 * @author wu
 *
 */
@Table(name="user_role")
public class UserRoleDO {

	private String id;
	
	private String userAccount;
	
	private String roleId;
	
	private String authArea;
	
	private String  authData;

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

	public String getAuthArea() {
		return authArea;
	}

	public void setAuthArea(String authArea) {
		this.authArea = authArea;
	}

	public String getAuthData() {
		return authData;
	}

	public void setAuthData(String authData) {
		this.authData = authData;
	}

	@Override
	public String toString() {
		return "SystemAuthorizationDO [id=" + id + ", userAccount=" + userAccount + ", roleCode=" + roleId + "]";
	}
	
	
}
