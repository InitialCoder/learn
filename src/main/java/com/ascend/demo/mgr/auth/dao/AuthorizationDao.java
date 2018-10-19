package com.ascend.demo.mgr.auth.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ascend.demo.common.domain.AuthorizationDO;
import com.ascend.demo.mgr.auth.condition.AuthorizationCondition;

@Mapper
public interface AuthorizationDao {

	int saveOne(AuthorizationDO pojo);
	
	List<AuthorizationDO> getByUserAccont(String userAccount);
	
	AuthorizationDO getById(String id);
	
	int deleteById(String id);
	
	List<AuthorizationDO> findAll();
	
	List<AuthorizationDO> findByWhere(AuthorizationCondition dto);
	
}
