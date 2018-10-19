package com.ascend.demo.mgr.auth.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ascend.demo.common.domain.RoleDO;

@Mapper
public interface RoleDao {

	RoleDO getById(String id);
	int saveOne(RoleDO pojo);
	int update(RoleDO pojo);
	List<RoleDO> findAll();
	int deleteById(String id);
}


