package com.ascend.demo.common.domain;

import java.io.Serializable;

public class OneToOneDO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4774280864200926307L;

	private String id;
	
	private String userId;
	
	private String code;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	
	@Override
	public String toString() {
		return this.code;
	}
}
