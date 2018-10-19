package com.ascend.demo.mgr.auth.service;

import java.util.List;

import com.ascend.demo.common.domain.RoleDO;

public interface RoleService {

	RoleDO getById(String id);
	
	int saveOne(RoleDO pojo);

	int update(RoleDO pojos);
	
	List<RoleDO> findAll();
	
	int deleteById(String id);
	
}
