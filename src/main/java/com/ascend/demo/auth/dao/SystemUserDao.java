package com.ascend.demo.auth.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ascend.demo.auth.domain.SystemUserDO;
import com.ascend.demo.auth.domain.SystemUserDTO;

@Mapper
public interface SystemUserDao {

	SystemUserDO getById(String value);
	
	int saveOne(SystemUserDO pojo);
	int saveList(List<SystemUserDO> pojos);
	int update(SystemUserDO pojo);
	int updateList(List<SystemUserDO> pojos);
	
	List<SystemUserDO> findAll();
	
	List<SystemUserDO> findByWhere(SystemUserDTO dto);
	
	SystemUserDO oneToOneselect(String leId);
}


