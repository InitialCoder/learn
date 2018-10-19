package com.ascend.demo.mgr.auth.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ascend.demo.common.domain.UserDO;
import com.ascend.demo.mgr.auth.condition.UserCondition;

@Mapper
public interface UserDao {

	UserDO getById(String value);
	
	int saveOne(UserDO pojo);
	int saveList(List<UserDO> pojos);
	int update(UserDO pojo);
	int updateList(List<UserDO> pojos);
	
	List<UserDO> findByUserAccount(String userAccount);
	
	List<UserDO> findAll();
	
	List<UserDO> findByWhere(UserCondition dto);
	
	UserDO oneToOneselect(String leId);
}


