package com.ascend.demo.mgr.auth.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.ascend.demo.common.domain.UserDO;
import com.ascend.demo.mgr.auth.condition.UserCondition;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

@Mapper
public interface UserDao extends BaseMapper<UserDO>{
	
	int saveList(List<UserDO> pojos);
	
	int updateList(List<UserDO> pojos);
	
	List<UserDO> findByUserAccount(String userAccount);
	
	List<UserDO> findByWhere(UserCondition dto);

	List<UserDO> selectAll();
	
	@Select("select * from demo_user where 1=1")
	List<UserDO> findByUserAccount1();
	
}


