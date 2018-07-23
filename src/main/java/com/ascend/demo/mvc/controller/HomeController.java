package com.ascend.demo.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ascend.demo.mvc.model.ResultInfo;

@Controller
@RequestMapping("/home")
public class HomeController {

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
}
