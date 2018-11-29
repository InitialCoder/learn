package com.ascend.demo.mgr.auth.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ascend.demo.common.domain.UserRoleDO;
import com.ascend.demo.mgr.auth.condition.UserRoleCondition;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

@Mapper
public interface UserRoleDao extends BaseMapper<UserRoleDO>{
	
	List<UserRoleDO> getByUserAccont(String userAccount);
	
	List<UserRoleDO> findByWhere(UserRoleCondition condition);
	
}
