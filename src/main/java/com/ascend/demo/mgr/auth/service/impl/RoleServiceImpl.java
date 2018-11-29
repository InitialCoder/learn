package com.ascend.demo.mgr.auth.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ascend.demo.common.domain.RoleDO;
import com.ascend.demo.mgr.auth.dao.RoleDao;
import com.ascend.demo.mgr.auth.service.RoleService;

@Service
@Transactional(readOnly=true)
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleDao roleDao;
	
	@Override
	public RoleDO getById(String id) {
		return roleDao.selectById(id);
	}

	@Override
	@Transactional
	public int saveOne(RoleDO pojo) {
		return roleDao.insert(pojo);
	}

	@Override
	@Transactional
	public int update(RoleDO pojo) {
		return roleDao.updateById(pojo);
	}

	@Override
	public List<RoleDO> findAll() {
		return roleDao.selectList(null);
	}

	@Override
	@Transactional
	public int deleteById(String id) {
		return roleDao.deleteById(id);
	}
	
}
