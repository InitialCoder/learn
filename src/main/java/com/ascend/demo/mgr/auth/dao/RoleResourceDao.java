package com.ascend.demo.mgr.auth.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ascend.demo.common.domain.RoleResourceDO;
import com.ascend.demo.mgr.auth.condition.RoleResourceCondition;

@Mapper
public interface RoleResourceDao {

	RoleResourceDO getById(String id);
	
	int saveOne(RoleResourceDO pojo);
	
	int saveList(List<RoleResourceDO> list);
	
	int update(RoleResourceDO pojo);
	
	int deleteById(String id);
	
	List<RoleResourceDO> findByWhere(RoleResourceCondition dto);

	List<RoleResourceDO> findByRoleId(String roleId);
}


