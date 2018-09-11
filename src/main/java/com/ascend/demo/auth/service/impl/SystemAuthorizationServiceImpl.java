package com.ascend.demo.auth.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ascend.demo.auth.dao.SystemAuthorizationDao;
import com.ascend.demo.auth.domain.SystemAuthorizationDO;
import com.ascend.demo.auth.domain.dto.SystemAuthorizationDTO;
import com.ascend.demo.auth.service.SystemAuthorizationService;

@Service
@Transactional(readOnly=true)
public class SystemAuthorizationServiceImpl implements SystemAuthorizationService{

	@Autowired
	private SystemAuthorizationDao authDao;
	
	@Override
	@Transactional
	public void saveOne(SystemAuthorizationDO pojo) {
		authDao.saveOne(pojo);
	}

	@Override
	public List<SystemAuthorizationDO> getByUserAccont(String userAccount) {
		return authDao.getByUserAccont(userAccount);
	}

	@Override
	public SystemAuthorizationDO getById(String id) {
		return authDao.getById(id);
	}

	@Override
	@Transactional
	public void deleteById(String id) {
		authDao.deleteById(id);
	}

	@Override
	public List<SystemAuthorizationDO> findAll() {
		return authDao.findAll();
	}

	@Override
	public List<SystemAuthorizationDO> findByWhere(SystemAuthorizationDTO dto) {
		return authDao.findByWhere(dto);
	}

	 
}
