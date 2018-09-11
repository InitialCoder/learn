package com.ascend.demo.auth.service;

import java.util.List;

import com.ascend.demo.auth.domain.SystemAuthorizationDO;
import com.ascend.demo.auth.domain.dto.SystemAuthorizationDTO;

public interface SystemAuthorizationService {

	void saveOne(SystemAuthorizationDO pojo);
	
	List<SystemAuthorizationDO> getByUserAccont(String userAccount);
	
	SystemAuthorizationDO getById(String id);
	
	void deleteById(String id);
	
	List<SystemAuthorizationDO> findAll();
	
	List<SystemAuthorizationDO> findByWhere(SystemAuthorizationDTO dto);
}
