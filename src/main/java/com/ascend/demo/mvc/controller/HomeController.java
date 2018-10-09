package com.ascend.demo.mvc.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ascend.demo.auth.domain.SystemUserDO;
import com.ascend.demo.auth.service.SystemUserService;
import com.ascend.demo.mvc.model.ResultInfo;

@Controller
@RequestMapping("/home")
public class HomeController {

	@Autowired
	private SystemUserService service;
	
	@RequestMapping({"index","/index.action"})
	public String index(){
		System.out.println("index");
		return "index";
	}
	
	@RequestMapping("/get.json")
	@ResponseBody
	public ResultInfo get(ResultInfo info){
		info.setAge("65");
		info.setName("Aloha");
		
		return info;
		
		
	}
	
	@RequestMapping("/get1.json")
	@ResponseBody
	public SystemUserDO get1(){

		SystemUserDO pojo=new SystemUserDO();
		pojo.setAge(16+1);
		pojo.setCreateCode("wu"+1);
		pojo.setEmail("11111@qq.com"+1);
		pojo.setModifyCode("he"+1);
		pojo.setModifyTime(new Date());
		pojo.setSex("man"+1);
		pojo.setUserAccount("adtest1"+1);
		pojo.setUserName("ad测试"+1);
		service.saveOne(pojo);
		return pojo;
		
	}
	
	@RequestMapping("/get2.json")
	@ResponseBody
	public int get2(){
		List<SystemUserDO> list=new ArrayList<SystemUserDO>();
		SystemUserDO pojo;
		for(int i=0;i<10;i++){
			pojo=new SystemUserDO();
			pojo.setAge(16);
			pojo.setCreateCode("wu");
			pojo.setEmail("11111@qq.com");
			pojo.setModifyCode("he");
			pojo.setModifyTime(new Date());
			pojo.setSex("man");
			pojo.setUserAccount("adtest1");
			pojo.setUserName("ad测试");
			list.add(pojo);
		}
		int num =service.saveList(list);
		System.out.println(num);
		return num;
		
	}
	
	@RequestMapping("udpate1.json")
	@ResponseBody
	public int update1(){
		SystemUserDO user=service.getById("123");
		user.setAge(100);
		user.setModifyCode("galigey");
		return service.update(user);
	}
	
	@RequestMapping("udpate2.json")
	@ResponseBody
	public int update2(){
		List<SystemUserDO> user=service.findAll();
		List<SystemUserDO> tem=new ArrayList<SystemUserDO>();
		SystemUserDO entity;
		for (int i=0;i<2;i++) {
			entity=user.get(i);
			entity.setAge(entity.getAge()+1);
			entity.setModifyTime(new Date());
			entity.setEmail(entity.getEmail()+1);
			tem.add(entity);
		}
		return service.update(tem);
	}  
	
	@RequestMapping("findAll.json")
	@ResponseBody
	@RequiresPermissions(value={"home:findAll"})
	public List<SystemUserDO> findAll(){
		return service.findAll();
		
	}
	
	@RequestMapping("admin.action")
	public String admin(){
		
		return "admin";
	}
	@RequestMapping("user.action")
	public String usern(){
		
		return "user";
	}
	
	@RequiresRoles(value={"USER"})
	@RequestMapping("list.action")
	public String list(){
		
		return "list";
	}
}
