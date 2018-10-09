package com.ascend.demo.auth.service;

import java.util.List;
import java.util.Set;

import com.ascend.demo.auth.domain.SystemResourceDO;
import com.ascend.demo.auth.domain.dto.SystemResourceDTO;

public interface SystemResourceService {

	SystemResourceDO getById(String id);
	
	int save(SystemResourceDO pojo);
	
	int update(SystemResourceDO pojo);
	
	List<SystemResourceDO> findByParentId(String parentId);
	
	int deleteById(String id);
	
	List<SystemResourceDO> findByWhere(SystemResourceDTO dto);
	
	Set<String> listUserPerm(String userAccount);
	
}


