package com.ascend.demo.auth.service.impl;

import java.util.List;

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

	@Override
	@Transactional
	public int saveOne(SystemUserDO pojo) {
	 
		return userDao.saveOne(pojo);
	}

	@Override
	@Transactional
	public int saveList(List<SystemUserDO> pojos) {
		return userDao.saveList(pojos);
	}
	
	
	@Override
	@Transactional
	public int update(SystemUserDO pojos) {
		return userDao.update(pojos);
	}
	
	@Override
	@Transactional
	public int update(List<SystemUserDO> pojos) {
		return userDao.updateList(pojos);
	}

	@Override
	public List<SystemUserDO> findAll() {
		
		return userDao.findAll();
	}

}
