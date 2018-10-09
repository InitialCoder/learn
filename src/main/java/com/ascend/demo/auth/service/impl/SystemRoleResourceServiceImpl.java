package com.ascend.demo.auth.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ascend.demo.auth.dao.SystemRoleResourceDao;
import com.ascend.demo.auth.domain.SystemRoleResourceDO;
import com.ascend.demo.auth.domain.dto.SystemRoleResourceDTO;
import com.ascend.demo.auth.service.SystemRoleResourceService;

@Service
@Transactional(readOnly=true)
public class SystemRoleResourceServiceImpl implements SystemRoleResourceService{

	@Autowired
	private SystemRoleResourceDao roleResourcedao;
	
	@Override
	public SystemRoleResourceDO getById(String id) {
		return roleResourcedao.getById(id);
	}

	@Override
	@Transactional
	public int save(SystemRoleResourceDO pojo) {
		return roleResourcedao.saveOne(pojo);
	}

	@Override
	@Transactional
	public int save(List<SystemRoleResourceDO> list) {
		return roleResourcedao.saveList(list);
	}

	@Override
	@Transactional
	public int update(SystemRoleResourceDO pojo) {
		return roleResourcedao.update(pojo);
	}

	@Override
	@Transactional
	public int deleteById(String id) {
		return roleResourcedao.deleteById(id);
	}

	@Override
	public List<SystemRoleResourceDO> findByWhere(SystemRoleResourceDTO dto) {
		 
		return roleResourcedao.findByWhere(dto);
	}

	@Override
	public List<SystemRoleResourceDO> findByRoleId(String roleId) {
		
		return roleResourcedao.findByRoleId(roleId);
	}

}
