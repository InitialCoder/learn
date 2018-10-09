package com.ascend.demo.auth.domain.dto;

/**
 * 资源管理实
 * @author wu
 *
 */
public class SystemResourceDTO {
	
	/**
	 * 父类id
	 */
	private String parentId;
	
	/**
	 * 类型：资源类型（1 ：目录  2：按钮  3：数据或者文件接口）
	 */
	private int type;
	
	private String resourceName;
	
	private String url;
 
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
 
}
