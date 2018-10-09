package com.ascend.demo.auth.dao;

import java.util.List;
import java.util.Set;

import org.apache.ibatis.annotations.Mapper;

import com.ascend.demo.auth.domain.SystemResourceDO;
import com.ascend.demo.auth.domain.dto.SystemResourceDTO;

@Mapper
public interface SystemResourceDao {

	SystemResourceDO getById(String id);
	
	int saveOne(SystemResourceDO pojo);
	
	int update(SystemResourceDO pojo);
	
	List<SystemResourceDO> findByParentId(String parentId);
	
	int deleteById(String id);
	
	List<SystemResourceDO> findByWhere(SystemResourceDTO dto);

	List<String> listUserPerm(String userAccount);
	
}


