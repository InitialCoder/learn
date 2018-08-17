package com.ascend.demo.auth.service;

import com.ascend.demo.auth.domain.SystemUserDO;

public interface SystemUserService {

	SystemUserDO getById(Long id);
	
}
