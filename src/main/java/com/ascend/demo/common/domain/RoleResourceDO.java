package com.ascend.demo.common.domain;

import javax.persistence.Table;

/**
 * 角色-资源中间表：权限控制表
 * @author wu
 *
 */
@Table(name="demo_role_resource")
public class RoleResourceDO extends BaseEntity{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2355590231475980173L;

	/**
	 * 角色id
	 */
	private String roleId;
	
	/**
	 * 资源id
	 */
	private String resourceId;

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
