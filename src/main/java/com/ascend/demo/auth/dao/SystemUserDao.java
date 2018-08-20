package com.ascend.demo.auth.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ascend.demo.auth.domain.SystemUserDO;

@Mapper
public interface SystemUserDao {

	SystemUserDO getById(Long value);
	
	int saveOne(SystemUserDO pojo);
	int saveList(List<SystemUserDO> pojos);
	int update(SystemUserDO pojo);
	int updateList(List<SystemUserDO> pojos);
	
	List<SystemUserDO> findAll();
}
