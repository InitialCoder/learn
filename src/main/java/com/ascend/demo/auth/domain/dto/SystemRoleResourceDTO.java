package com.ascend.demo.auth.domain.dto;
 
public class SystemRoleResourceDTO {
	
	private String id;
	
	/**
	 * 角色id
	 */
	private String roleId;
	
	/**
	 * 资源id
	 */
	private String resourceId;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getResourceId() {
		return resourceId;
	}

	public void setResourceId(String resourceId) {
		this.resourceId = resourceId;
	}
	
}
