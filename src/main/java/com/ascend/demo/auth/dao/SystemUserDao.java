package com.ascend.demo.auth.dao;

import org.apache.ibatis.annotations.Mapper;

import com.ascend.demo.auth.domain.SystemUserDO;

@Mapper
public interface SystemUserDao {

	SystemUserDO getById(Long value);
}
