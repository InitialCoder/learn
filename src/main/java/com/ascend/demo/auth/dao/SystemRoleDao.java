package com.ascend.demo.auth.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ascend.demo.auth.domain.SystemRoleDO;

@Mapper
public interface SystemRoleDao {

	SystemRoleDO getById(String id);
	int saveOne(SystemRoleDO pojo);
	int update(SystemRoleDO pojo);
	List<SystemRoleDO> findAll();
	int deleteById(String id);
}


