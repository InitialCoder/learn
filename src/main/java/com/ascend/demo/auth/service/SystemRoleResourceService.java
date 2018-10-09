package com.ascend.demo.auth.service;

import java.util.List;

import com.ascend.demo.auth.domain.SystemRoleResourceDO;
import com.ascend.demo.auth.domain.dto.SystemRoleResourceDTO;

public interface SystemRoleResourceService {

	SystemRoleResourceDO getById(String id);
	
	int save(SystemRoleResourceDO pojo);
	
	int save(List<SystemRoleResourceDO> list);
	
	int update(SystemRoleResourceDO pojo);
	
	int deleteById(String id);
	
	List<SystemRoleResourceDO> findByWhere(SystemRoleResourceDTO dto);
	
	List<SystemRoleResourceDO> findByRoleId(String roleId);
}


