package com.ascend.test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

	@Autowired
	private SystemUserService service;
	
	@Test
	public void test1(){
		List<SystemUserDO> list=new ArrayList<SystemUserDO>();
		SystemUserDO pojo;
		for(int i=0;i<10;i++){
			pojo=new SystemUserDO();
			pojo.setAge(16+1);
			pojo.setCreateCode("wu"+1);
			pojo.setEmail("11111@qq.com"+1);
			pojo.setModifyCode("he"+1);
			pojo.setModifyTime(new Date());
			pojo.setSex("man"+1);
			pojo.setUserAccount("adtest1"+1);
			pojo.setUserName("ad测试"+1);
			list.add(pojo);
		}
		int num =service.saveList(list);
		System.out.println(num);
	}
	
}
