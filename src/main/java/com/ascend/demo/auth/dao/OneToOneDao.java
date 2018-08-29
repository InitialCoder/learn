package com.ascend.demo.auth.dao;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OneToOneDao {

	void save(OneToOneDao one);
}
