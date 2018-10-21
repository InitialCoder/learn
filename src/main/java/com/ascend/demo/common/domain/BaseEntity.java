package com.ascend.demo.common.domain;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public class BaseEntity implements Serializable{

	@Id 
	@GeneratedValue(generator="JDBC")
	private String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	
}
