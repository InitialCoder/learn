package com.ascend.demo.mgr.auth.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ascend.demo.common.domain.RoleResourceDO;
import com.ascend.demo.mgr.auth.condition.RoleResourceCondition;
import com.ascend.demo.mgr.auth.dao.RoleResourceDao;
import com.ascend.demo.mgr.auth.service.RoleResourceService;

@Service
@Transactional(readOnly=true)
public class RoleResourceServiceImpl implements RoleResourceService {

	@Autowired
	private RoleResourceDao roleResourcedao;
	
	@Override
	public RoleResourceDO getById(String id) {
		return roleResourcedao.selectById(id);
	}

	@Override
	@Transactional
	public int save(RoleResourceDO pojo) {
		return roleResourcedao.insert(pojo);
	}

	@Override
	@Transactional
	public int save(List<RoleResourceDO> list) {
		return roleResourcedao.saveList(list);
	}

	@Override
	@Transactional
	public int update(RoleResourceDO pojo) {
		return roleResourcedao.updateById(pojo);
	}

	@Override
	@Transactional
	public int deleteById(String id) {
		return roleResourcedao.deleteById(id);
	}

	@Override
	public List<RoleResourceDO> findByWhere(RoleResourceCondition dto) {
		 
		return roleResourcedao.findByWhere(dto);
	}

	@Override
	public List<RoleResourceDO> findByRoleId(String roleId) {
		
		return roleResourcedao.findByRoleId(roleId);
	}
	
 
}


