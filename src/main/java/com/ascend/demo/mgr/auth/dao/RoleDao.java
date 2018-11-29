package com.ascend.demo.mgr.auth.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ascend.demo.common.domain.RoleDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

@Mapper
public interface RoleDao  extends BaseMapper<RoleDO> {

	public List<RoleDO> findAll();
}


