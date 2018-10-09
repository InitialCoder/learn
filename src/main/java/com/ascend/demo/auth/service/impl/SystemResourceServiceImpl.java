package com.ascend.demo.auth.service.impl;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ascend.demo.auth.dao.SystemResourceDao;
import com.ascend.demo.auth.domain.SystemResourceDO;
import com.ascend.demo.auth.domain.dto.SystemResourceDTO;
import com.ascend.demo.auth.service.SystemResourceService;

@Service
@Transactional(readOnly=true)
public class SystemResourceServiceImpl implements SystemResourceService{

	@Autowired
	private SystemResourceDao resourceDao;
	
	@Override
	public SystemResourceDO getById(String id) {
		 
		return resourceDao.getById(id);
	}

	@Override
	@Transactional
	public int save(SystemResourceDO pojo) {
		return resourceDao.saveOne(pojo);
	}

	@Override
	@Transactional
	public int update(SystemResourceDO pojo) {
		return resourceDao.update(pojo);
	}

	@Override
	public List<SystemResourceDO> findByParentId(String parentId) {
		
		return resourceDao.findByParentId(parentId);
	}

	@Override
	@Transactional
	public int deleteById(String id) {
		return resourceDao.deleteById(id);
	}

	@Override
	public List<SystemResourceDO> findByWhere(SystemResourceDTO dto) {
		return resourceDao.findByWhere(dto);
	}

	@Override
	public Set<String> listUserPerm(String userAccount) {
		List<String> list= resourceDao.listUserPerm(userAccount);
		Set<String> set=new HashSet<String>(list);
		for (String perm : list) {
			set.addAll(Arrays.asList(perm.trim().split(",")));
		}
		return set;
	}

}
