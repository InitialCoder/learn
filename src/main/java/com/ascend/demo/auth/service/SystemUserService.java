package com.ascend.demo.auth.service;

import java.util.List;

import com.ascend.demo.auth.domain.SystemUserDO;

public interface SystemUserService {

	SystemUserDO getById(Long id);
	
	int saveOne(SystemUserDO pojo);
	int saveList(List<SystemUserDO> pojo);

	int update(SystemUserDO pojos);

	int update(List<SystemUserDO> pojos);
	
	List<SystemUserDO> findAll();
}
