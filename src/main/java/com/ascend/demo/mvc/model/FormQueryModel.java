package com.ascend.demo.mvc.model;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class FormQueryModel implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7384449937899932283L;
	/**
	 * 问题单id
	 */
	private String id;
	/**
	 * 查询条件
	 */
	private String queryCondition;
	/**
	 * 客服中心列表专用id
	 */
	private String customdetailsid;
	/****
	 * 客服中心详情页查询ID
	 */
	private String customlistid;
	/**
	 * 数据取值方向 0向后，1向前
	 */
	private String prev;
	/**
	 * 当前单据对应上一页id 
	 */
	private String prevId;
	private String prevRn;
	private String nextId;
	private String nextRn;
	private String tagFlag;
	/**
	 * 查询数据排名
	 */
	private String rn;
	private String comefromback;
	
	
	public String getCustomdetailsid() {
		return customdetailsid;
	}
	public void setCustomdetailsid(String customdetailsid) {
		this.customdetailsid = customdetailsid;
	}
	public String getCustomlistid() {
		return customlistid;
	}
	public void setCustomlistid(String customlistid) {
		this.customlistid = customlistid;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getQueryCondition() {
		return queryCondition;
	}
	public void setQueryCondition(String queryCondition) {
		this.queryCondition = queryCondition;
	}
	public String getPrev() {
		return prev;
	}
	public void setPrev(String prev) {
		this.prev = prev;
	}
	public String getPrevId() {
		return prevId;
	}
	public void setPrevId(String prevId) {
		this.prevId = prevId;
	}
	public String getRn() {
		return rn;
	}
	public void setRn(String rn) {
		this.rn = rn;
	}
	public String getPrevRn() {
		return prevRn;
	}
	public String getNextId() {
		return nextId;
	}
	public String getNextRn() {
		return nextRn;
	}
	public void setPrevRn(String prevRn) {
		this.prevRn = prevRn;
	}
	public void setNextId(String nextId) {
		this.nextId = nextId;
	}
	public void setNextRn(String nextRn) {
		this.nextRn = nextRn;
	}
	public String getComefromback() {
		return comefromback;
	}
	public void setComefromback(String comefromback) {
		this.comefromback = comefromback;
	}
	
	public String getTagFlag() {
		return tagFlag;
	}
	public void setTagFlag(String tagFlag) {
		this.tagFlag = tagFlag;
	}
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}
}
