package com.ascend.demo.auth.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ascend.demo.auth.domain.SystemAuthorizationDO;
import com.ascend.demo.auth.domain.dto.SystemAuthorizationDTO;

@Mapper
public interface SystemAuthorizationDao {

	int saveOne(SystemAuthorizationDO pojo);
	
	List<SystemAuthorizationDO> getByUserAccont(String userAccount);
	
	SystemAuthorizationDO getById(String id);
	
	int deleteById(String id);
	
	List<SystemAuthorizationDO> findAll();
	
	List<SystemAuthorizationDO> findByWhere(SystemAuthorizationDTO dto);
	
}
