package com.ascend.demo.mvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ascend.demo.auth.dao.SystemUserDao;
import com.ascend.demo.auth.domain.SystemUserDO;
import com.ascend.demo.mvc.model.ResultInfo;

@Controller
@RequestMapping("/home")
public class HomeController {

	@Autowired
	private SystemUserDao userDao;
	
	@RequestMapping("/index.action")
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
	public SystemUserDO get1(ResultInfo info){

		return userDao.getById(new Long(123));
		
	}
}
