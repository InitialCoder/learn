package com.ascend.demo.auth.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ascend.demo.auth.dao.SystemRoleDao;
import com.ascend.demo.auth.domain.SystemRoleDO;
import com.ascend.demo.auth.service.SystemRoleService;

@Service
@Transactional(readOnly=true)
public class SystemRoleServiceImpl implements SystemRoleService {

	@Autowired
	private SystemRoleDao roleDao;
	
	@Override
	public SystemRoleDO getById(String id) {
		return roleDao.getById(id);
	}

	@Override
	@Transactional
	public int saveOne(SystemRoleDO pojo) {
		return roleDao.saveOne(pojo);
	}

	@Override
	@Transactional
	public int update(SystemRoleDO pojo) {
		return roleDao.update(pojo);
	}

	@Override
	public List<SystemRoleDO> findAll() {
		return roleDao.findAll();
	}

	@Override
	@Transactional
	public int deleteById(String id) {
		return roleDao.deleteById(id);
	}
	 
}
