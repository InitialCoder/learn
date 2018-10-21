package com.ascend.demo.mgr.auth.service;

import java.util.List;

import com.ascend.demo.common.domain.UserDO;
import com.ascend.demo.mgr.auth.condition.UserCondition;

public interface UserService {

	UserDO getById(String id);
	
	int saveOne(UserDO pojo);
	
	int saveList(List<UserDO> pojo);

	int update(UserDO pojos);

	int update(List<UserDO> pojos);
	
	UserDO findByUserAccount(String userName);
	
	List<UserDO> findAll();
	
	List<UserDO> findByWhere(UserCondition dto);
	
}
