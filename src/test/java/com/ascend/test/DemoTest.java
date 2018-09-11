package com.ascend.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.ascend.demo.Application;
import com.ascend.demo.auth.domain.SystemAuthorizationDO;
import com.ascend.demo.auth.domain.SystemRoleDO;
import com.ascend.demo.auth.domain.SystemUserDO;
import com.ascend.demo.auth.service.SystemAuthorizationService;
import com.ascend.demo.auth.service.SystemRoleService;
import com.ascend.demo.auth.service.SystemUserService;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes=Application.class)
@SpringBootTest
public class DemoTest {

	@Autowired
	private SystemUserService service;
	
	@Autowired
	private SystemRoleService roleService;
	
	@Autowired
	private SystemAuthorizationService authService;
	
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
		 
		
		SystemUserDO entity= service.getById("123");
		System.out.println(entity);
		
	}
}
