package com.ascend.demo.auth.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ascend.demo.auth.domain.SystemRoleResourceDO;
import com.ascend.demo.auth.domain.dto.SystemRoleResourceDTO;

@Mapper
public interface SystemRoleResourceDao {

	SystemRoleResourceDO getById(String id);
	
	int saveOne(SystemRoleResourceDO pojo);
	
	int saveList(List<SystemRoleResourceDO> list);
	
	int update(SystemRoleResourceDO pojo);
	
	int deleteById(String id);
	
	List<SystemRoleResourceDO> findByWhere(SystemRoleResourceDTO dto);

	List<SystemRoleResourceDO> findByRoleId(String roleId);
}


