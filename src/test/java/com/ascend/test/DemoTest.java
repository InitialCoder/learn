package com.ascend.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.ascend.demo.Application;
import com.ascend.demo.common.domain.UserDO;
import com.ascend.demo.mgr.auth.service.AuthorizationService;
import com.ascend.demo.mgr.auth.service.RoleService;
import com.ascend.demo.mgr.auth.service.UserService;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes=Application.class)
@SpringBootTest
public class DemoTest {

	@Autowired
	private UserService service;
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private AuthorizationService authService;
	
	@Test
	public void addRole(){
		
		/*SystemRoleDO role=new SystemRoleDO();
		
		role.setRoleCode("DATAQUERY");
		role.setRoleName("查看员");
		role.setState("Y");
		roleService.saveOne(role);
		
		
		role.setRoleCode("OPERATER");
		role.setRoleName("操作员");
		role.setState("Y");
		roleService.saveOne(role);*/
		 
		
		UserDO entity= service.getById("123");
		System.out.println(entity);
		
	}
}
