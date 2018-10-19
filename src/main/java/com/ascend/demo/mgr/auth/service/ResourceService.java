package com.ascend.demo.mgr.auth.service;

import java.util.List;
import java.util.Set;

import com.ascend.demo.common.domain.ResourceDO;
import com.ascend.demo.mgr.auth.condition.ResourceCondition;

/**
 * 系统资源表，用户每次访问系统时都需要认证用户是否有次资源权限（ShiroAccessControlFilter已实现该功能）
 * 为减少数据库交互量，该表要做缓存
 * @author wu
 *
 */
public interface ResourceService {

	ResourceDO getById(String id);
	
	int save(ResourceDO pojo);
	
	int update(ResourceDO pojo);
	
	List<ResourceDO> findByParentId(String parentId);
	
	int deleteById(String id);
	
	List<ResourceDO> findByWhere(ResourceCondition dto);
	
	Set<String> listUserPerm(String userAccount);
	Set<String> listRolePerm(String roleCode);

	/**
	 * 根据URL获取权限编码
	 * @param url
	 * @return
	 */
	String getPermByUrl(String url);
	
}


