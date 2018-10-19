package com.ascend.demo.mgr.auth.service;

import java.util.List;

import com.ascend.demo.common.domain.RoleResourceDO;
import com.ascend.demo.mgr.auth.condition.RoleResourceCondition;

public interface RoleResourceService {

	RoleResourceDO getById(String id);
	
	int save(RoleResourceDO pojo);
	
	int save(List<RoleResourceDO> list);
	
	int update(RoleResourceDO pojo);
	
	int deleteById(String id);
	
	List<RoleResourceDO> findByWhere(RoleResourceCondition dto);
	
	List<RoleResourceDO> findByRoleId(String roleId);
}


