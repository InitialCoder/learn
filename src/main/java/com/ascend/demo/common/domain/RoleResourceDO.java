package com.ascend.demo.common.domain;

import com.baomidou.mybatisplus.annotation.TableName;

/**
 * 角色-资源中间表：权限控制表
 * @author wu
 *
 */
@TableName(value="demo_role_resource")
public class RoleResourceDO{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2355590231475980173L;

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
