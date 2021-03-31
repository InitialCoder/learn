package com.ascend.demo.auth.service;

import java.util.List;

import com.ascend.demo.auth.domain.SystemUserDO;
import com.ascend.demo.auth.domain.dto.SystemUserDTO;

public interface SystemUserService {

	SystemUserDO getById(String id);
	
	int saveOne(SystemUserDO pojo);
	
	int saveList(List<SystemUserDO> pojo);

	int update(SystemUserDO pojos);

	int update(List<SystemUserDO> pojos);
	
	SystemUserDO findByUserAccount(String userName);
	
	List<SystemUserDO> findAll();
	
	List<SystemUserDO> findByWhere(SystemUserDTO dto);
	
	SystemUserDO findOneToOne(String id);
}