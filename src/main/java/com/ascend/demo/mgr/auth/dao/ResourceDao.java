package com.ascend.demo.mgr.auth.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ascend.demo.common.domain.ResourceDO;
import com.ascend.demo.mgr.auth.condition.ResourceCondition;

@Mapper
public interface ResourceDao {

	ResourceDO getById(String id);
	
	int saveOne(ResourceDO pojo);
	
	int update(ResourceDO pojo);
	
	List<ResourceDO> findByParentId(String parentId);
	
	int deleteById(String id);
	
	List<ResourceDO> findByWhere(ResourceCondition dto);

	List<String> listUserPerm(String userAccount);

	ResourceDO getByUrl(String url);

	List<String> listRolePerm(String roleCode);
	
}


