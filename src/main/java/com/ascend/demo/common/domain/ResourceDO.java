package com.ascend.demo.common.domain;

import javax.persistence.Table;

/**
 * 资源管理实体类
 * @author wu
 *
 */
@Table(name="demo_resource")
public class ResourceDO extends BaseEntity{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2893717117311693828L;

	
	/**
	 * 父类id
	 */
	private String parentId;
	
	/**
	 * 类型：资源类型（1 ：目录  2：按钮  3：数据或者文件接口）
	 */
	private int type;
	
	private String resourceName;
	
	/**
	 * 授权，若多个，需要用,分开
	 */
	private String perms;
	
	private String url;
	
	private String remark;
	
	private String createCode;
	
	private String createTime;
	
	private String modifyCode;
	
	private String modifyTime;

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getResourceName() {
		return resourceName;
	}

	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
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

	public String getModifyCode() {
		return modifyCode;
	}

	public void setModifyCode(String modifyCode) {
		this.modifyCode = modifyCode;
	}

	public String getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(String modifyTime) {
		this.modifyTime = modifyTime;
	}

	public String getPerms() {
		return perms;
	}

	public void setPerms(String perms) {
		this.perms = perms;
	}

}
