package com.ascend.demo.mgr.auth.dao;

import java.util.List;

import com.ascend.demo.common.domain.ResourceDO;
import com.ascend.demo.mgr.auth.condition.ResourceCondition;

import tk.mybatis.mapper.common.Mapper;

public interface ResourceDao extends Mapper<ResourceDO>{

	
	List<ResourceDO> findByParentId(String parentId);
	
	List<ResourceDO> findByWhere(ResourceCondition dto);

	List<String> listUserPerm(String userAccount);

	ResourceDO getByUrl(String url);

	List<String> listRolePerm(String roleCode);
	
}


