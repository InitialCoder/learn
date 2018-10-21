package com.ascend.demo.mgr.auth.service.impl;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ascend.demo.common.domain.ResourceDO;
import com.ascend.demo.mgr.auth.condition.ResourceCondition;
import com.ascend.demo.mgr.auth.dao.ResourceDao;
import com.ascend.demo.mgr.auth.service.ResourceService;

/**
 * 系统资源表，用户每次访问系统时都需要认证用户是否有次资源权限（ShiroAccessControlFilter已实现该功能）
 * 为减少数据库交互量，该表要做缓存
 * @author wu
 *
 */
@Service
@Transactional(readOnly=true)
public class ResourceServiceImpl implements ResourceService {

	@Autowired
	private ResourceDao resourceDao;
	
	@Override
	public ResourceDO getById(String id) {
		 
		return resourceDao.selectByPrimaryKey(id);
	}

	@Override
	@Transactional
	public int save(ResourceDO pojo) {
		return resourceDao.insertSelective(pojo);
	}

	@Override
	@Transactional
	public int update(ResourceDO pojo) {
		return resourceDao.updateByPrimaryKeySelective(pojo);
	}

	@Override
	public List<ResourceDO> findByParentId(String parentId) {
		
		return resourceDao.findByParentId(parentId);
	}

	@Override
	@Transactional
	public int deleteById(String id) {
		return resourceDao.deleteByPrimaryKey(id);
	}

	@Override
	public List<ResourceDO> findByWhere(ResourceCondition dto) {
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

	@Override
	public Set<String> listRolePerm(String roleCode) {
		List<String> list=resourceDao.listRolePerm(roleCode);
		Set<String> sets=new HashSet<String>(list);
		return sets;
	}

	@Override
	public String getPermByUrl(String url) {
		ResourceDO entity = resourceDao.getByUrl(url);
		if(null==entity){
			return null;
		}
		return entity.getPerms();
	}
	
}


