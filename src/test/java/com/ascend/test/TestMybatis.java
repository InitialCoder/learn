package com.ascend.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.ascend.demo.Application;
import com.ascend.demo.common.domain.UserDO;
import com.ascend.demo.mgr.auth.service.UserService;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes=Application.class)
@SpringBootTest
public class TestMybatis {

	@Autowired
	private UserService service;
	
//	@Test
//	public void test1(){
//		List<SystemUserDO> list=new ArrayList<SystemUserDO>();
//		SystemUserDO pojo;
//		for(int i=0;i<10;i++){
//			pojo=new SystemUserDO();
//			pojo.setAge(16+1);
//			pojo.setCreateCode("wu"+1);
//			pojo.setEmail("11111@qq.com"+1);
//			pojo.setModifyCode("he"+1);
//			pojo.setModifyTime(new Date());
//			pojo.setSex("man"+1);
//			pojo.setUserAccount("adtest1"+1);
//			pojo.setUserName("ad测试"+1);
//			list.add(pojo);
//		}
//		int num =service.saveList(list);
//		System.out.println(num);
//	}
	
	@Test
	public void test(){
		
/*		List<SystemUserDO> list=service.findAll(); 
		int i=1;
		for (SystemUserDO pojo : list) {
			pojo.setAge(16+i);
			pojo.setCreateCode("wu"+i);
			pojo.setEmail("iiiii@qq.com"+i);
			pojo.setModifyCode("he"+i);
			pojo.setModifyTime(new Date());
			pojo.setSex("man"+i);
			pojo.setUserAccount("adtesti"+i);
			pojo.setUserName("ad测试"+i);
			i++;
		}
		
		service.update(list);*/
		
//		SystemUserDTO dto=new SystemUserDTO();
//		dto.setAge(17);
//		dto.setModifyCode("he");
//		dto.setUserAccount("adtest");
//		
//		List<SystemUserDO> list=service.findByWhere(dto);
//		
//		list.forEach(entity->System.out.println(entity.getAge()+":"+entity.getUserAccount()+":"+entity.getUserName()));
		
		
		//one to one
		
		UserDO dos=service.findOneToOne("124");
		System.out.println("-----------=====================");
		System.out.println(dos);
	}
}
