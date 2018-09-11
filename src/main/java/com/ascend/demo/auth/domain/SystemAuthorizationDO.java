package com.ascend.demo.auth.domain;

public class SystemAuthorizationDO {

	private String id;
	
	private String userAccount;
	
	private String roleCode;
	
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

	public String getRoleCode() {
		return roleCode;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
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
		return "SystemAuthorizationDO [id=" + id + ", userAccount=" + userAccount + ", roleCode=" + roleCode + "]";
	}
	
	
}
