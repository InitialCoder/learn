package com.ascend.demo.auth.service;

import java.util.List;

import com.ascend.demo.auth.domain.SystemRoleDO;

public interface SystemRoleService {

	SystemRoleDO getById(String id);
	
	int saveOne(SystemRoleDO pojo);

	int update(SystemRoleDO pojos);
	
	List<SystemRoleDO> findAll();
	
	int deleteById(String id);
	
}
