package com.ascend.demo.mgr.auth.dao;

import java.util.List;

import com.ascend.demo.common.domain.RoleResourceDO;
import com.ascend.demo.mgr.auth.condition.RoleResourceCondition;

import tk.mybatis.mapper.common.Mapper;

public interface RoleResourceDao extends Mapper<RoleResourceDO>{

	
	int saveList(List<RoleResourceDO> list);
	
	List<RoleResourceDO> findByWhere(RoleResourceCondition dto);

	List<RoleResourceDO> findByRoleId(String roleId);
}


