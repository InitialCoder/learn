package com.ascend.demo.mgr.auth.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ascend.demo.common.domain.ResourceDO;
import com.ascend.demo.mgr.auth.condition.ResourceCondition;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

@Mapper
public interface ResourceDao extends BaseMapper<ResourceDO>{

	
	List<ResourceDO> findByParentId(String parentId);
	
	List<ResourceDO> findByWhere(ResourceCondition dto);

	List<String> listUserPerm(String userAccount);

	ResourceDO getByUrl(String url);

	List<String> listRolePerm(String roleCode);
	
}


