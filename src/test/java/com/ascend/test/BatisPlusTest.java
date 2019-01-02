/*package com.ascend.test;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.ascend.demo.Application;
import com.ascend.demo.common.domain.RoleDO;
import com.ascend.demo.common.domain.UserDO;
import com.ascend.demo.common.ext.util.DateUtil;
import com.ascend.demo.mgr.auth.dao.UserDao;
import com.ascend.demo.mgr.auth.service.RoleService;
import com.ascend.demo.mgr.auth.service.UserService;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes=Application.class)
@SpringBootTest
public class BatisPlusTest {

	@Autowired
	private RoleService roleService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserDao userDao;
	
	@Test
	public void test(){
		RoleDO pojo=new RoleDO();
		pojo.setRoleCode("testRole");
		pojo.setCreateTime(DateUtil.currentDateTime());
		pojo.setRoleName("测试权限");
		pojo.setState("1");
		roleService.saveOne(pojo);
			
		List<UserDO> list=userDao.findByUserAccount1();
		UserDO user =userService.findByUserAccount("user1");
		System.out.println(user.getPassword());
	}
	
	
}
*/