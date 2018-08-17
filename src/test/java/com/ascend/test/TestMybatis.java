package com.ascend.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.ascend.demo.Application;
import com.ascend.demo.auth.domain.SystemUserDO;
import com.ascend.demo.auth.service.SystemUserService;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes=Application.class)
@SpringBootTest
public class TestMybatis {

	private SystemUserService service;
	
	@Test
	public void test1(){
		
		SystemUserDO pojo=service.getById(new Long(""));
		System.out.println(pojo.getUserAccount());
		System.out.println(pojo.getEmail());
	}
	
}
