package com.ascend.demo.common.domain;

import javax.persistence.Table;

/**
 * 系统角色
 * @author wu
 *
 */
@Table(name="demo_role")
public class RoleDO extends BaseEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5670994868165585222L;
	
	/**
	 * 角色编码
	 */
	private String roleCode;
	/**
	 * 角色名称
	 */
	private String roleName;
	
	private String createCode;
	
	private String createTime;
	
	private String modifyTime;
	
	private String modifyCode;
	/**
	 * 启用状态：Y/N
	 */
	private String state;


	public String getRoleCode() {
		return roleCode;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getCreateCode() {
		return createCode;
	}

	public void setCreateCode(String createCode) {
		this.createCode = createCode;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(String modifyTime) {
		this.modifyTime = modifyTime;
	}

	public String getModifyCode() {
		return modifyCode;
	}

	public void setModifyCode(String modifyCode) {
		this.modifyCode = modifyCode;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	
	
}
