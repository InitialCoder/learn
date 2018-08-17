package com.ascend.demo.auth.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ascend.demo.auth.dao.SystemUserDao;
import com.ascend.demo.auth.domain.SystemUserDO;
import com.ascend.demo.auth.service.SystemUserService;

@Service
@Transactional(readOnly=true)
public class SystemUserServiceImpl implements SystemUserService{

	@Autowired
	private SystemUserDao userDao;
	
	@Override
	public SystemUserDO getById(Long id) {
		
		return userDao.getById(id);
	}

}
