package com.ascend.demo.mgr.auth.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ascend.demo.common.domain.UserRoleDO;
import com.ascend.demo.mgr.auth.condition.AuthorizationCondition;

@Mapper
public interface UserRoleDao {

	int saveOne(UserRoleDO pojo);
	
	List<UserRoleDO> getByUserAccont(String userAccount);
	
	UserRoleDO getById(String id);
	
	int deleteById(String id);
	
	List<UserRoleDO> findAll();
	
	List<UserRoleDO> findByWhere(AuthorizationCondition dto);
	
}
