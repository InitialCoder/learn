package com.ascend.demo.mgr.auth.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ascend.demo.common.domain.RoleResourceDO;
import com.ascend.demo.mgr.auth.condition.RoleResourceCondition;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

@Mapper
public interface RoleResourceDao extends BaseMapper<RoleResourceDO>{

	
	int saveList(List<RoleResourceDO> list);
	
	List<RoleResourceDO> findByWhere(RoleResourceCondition dto);

	List<RoleResourceDO> findByRoleId(String roleId);
}


