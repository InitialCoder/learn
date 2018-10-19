package com.ascend.demo.mgr.auth.service;

import java.util.List;

import com.ascend.demo.common.domain.AuthorizationDO;
import com.ascend.demo.mgr.auth.condition.AuthorizationCondition;

public interface AuthorizationService {

	void saveOne(AuthorizationDO pojo);
	
	List<AuthorizationDO> getByUserAccont(String userAccount);
	
	AuthorizationDO getById(String id);
	
	void deleteById(String id);
	
	List<AuthorizationDO> findAll();
	
	List<AuthorizationDO> findByWhere(AuthorizationCondition dto);
}
