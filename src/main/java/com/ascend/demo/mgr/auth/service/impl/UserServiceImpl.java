package com.ascend.demo.mgr.auth.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ascend.demo.common.domain.UserDO;
import com.ascend.demo.mgr.auth.condition.UserCondition;
import com.ascend.demo.mgr.auth.dao.UserDao;
import com.ascend.demo.mgr.auth.service.UserService;

@Service
@Transactional(readOnly=true)
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;
	
	@Override
	public UserDO getById(String id) {
		
		return userDao.selectByPrimaryKey(id);
	}

	@Override
	@Transactional
	public int saveOne(UserDO pojo) {
	 
		return userDao.insert(pojo);
	}

	@Override
	@Transactional
	public int saveList(List<UserDO> pojos) {
		return userDao.saveList(pojos);
	}
	
	
	@Override
	@Transactional
	public int update(UserDO pojo) {
		return userDao.updateByPrimaryKeySelective(pojo);
	}
	
	@Override
	@Transactional
	public int update(List<UserDO> pojos) {
		return userDao.updateList(pojos);
	}

	@Override
	public List<UserDO> findAll() {
		
		return userDao.selectAll();
	}

	@Override
	public List<UserDO> findByWhere(UserCondition dto) {
		return 	userDao.findByWhere(dto);
		
	}

	@Override
	public UserDO findByUserAccount(String userAccount) {
		List<UserDO> list=userDao.findByUserAccount(userAccount);
		if(!list.isEmpty()){
			return list.get(0);
		}
		return null;
	}
}
