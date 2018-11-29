package com.ascend.demo.mgr.auth.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ascend.demo.common.domain.UserRoleDO;
import com.ascend.demo.mgr.auth.condition.UserRoleCondition;
import com.ascend.demo.mgr.auth.dao.UserRoleDao;
import com.ascend.demo.mgr.auth.service.UserRoleService;


@Service
@Transactional(readOnly=true)
public class UserRoleServiceImpl implements UserRoleService {

	@Autowired
	private UserRoleDao authDao;
	
	@Override
	@Transactional
	public void saveOne(UserRoleDO pojo) {
		authDao.insert(pojo);
	}

	@Override
	public List<UserRoleDO> getByUserAccont(String userAccount) {
		return authDao.getByUserAccont(userAccount);
	}

	@Override
	public UserRoleDO getById(String id) {
		return authDao.selectById(id);
	}

	@Override
	@Transactional
	public void deleteById(String id) {
		authDao.deleteById(id);
	}

	@Override
	public List<UserRoleDO> findAll() {
		return authDao.selectList(null);
	}

	@Override
	public List<UserRoleDO> findByWhere(UserRoleCondition condition) {
		return authDao.findByWhere(condition);
	}
	
}
