package com.ascend.demo.mgr.auth.dao;

import java.util.List;

import com.ascend.demo.common.domain.UserDO;
import com.ascend.demo.mgr.auth.condition.UserCondition;

import tk.mybatis.mapper.common.Mapper;

public interface UserDao extends Mapper<UserDO>{
	
	int saveList(List<UserDO> pojos);
	
	int updateList(List<UserDO> pojos);
	
	List<UserDO> findByUserAccount(String userAccount);
	
	List<UserDO> findByWhere(UserCondition dto);
	
}


