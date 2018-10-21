package com.ascend.demo.mgr.auth.service;

import java.util.List;

import com.ascend.demo.common.domain.UserRoleDO;
import com.ascend.demo.mgr.auth.condition.AuthorizationCondition;

public interface UserRoleService {

	void saveOne(UserRoleDO pojo);
	
	List<UserRoleDO> getByUserAccont(String userAccount);
	
	UserRoleDO getById(String id);
	
	void deleteById(String id);
	
	List<UserRoleDO> findAll();
	
	List<UserRoleDO> findByWhere(AuthorizationCondition dto);
}
