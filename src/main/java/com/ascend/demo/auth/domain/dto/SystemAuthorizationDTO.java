package com.ascend.demo.auth.domain.dto;

public class SystemAuthorizationDTO {

	private String userAccount;
	
	private String role;

	public String getUserAccount() {
		return userAccount;
	}

	public void setUserAccount(String userAccount) {
		this.userAccount = userAccount;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
	
}
