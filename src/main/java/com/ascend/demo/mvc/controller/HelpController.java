package com.ascend.demo.mvc.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ascend.demo.mvc.model.FormQueryModel;
import com.ascend.demo.util.DateUtil;

@Controller
@RequestMapping("help")
public class HelpController {

	private Logger log =LoggerFactory.getLogger(HelpController.class);
	
	@RequestMapping("/querymodel.json")
	@ResponseBody
	public void queryModel(FormQueryModel model){
//		System.out.print(DateUtil.currentDateTime()+"  ");
//		System.out.println(model);
//		log.info(model.toString());
		
		log.info(DateUtil.currentDateTime()+" "+model.toString());
	}
	
	@RequestMapping("/test.json")
	@ResponseBody
	public String queryModel(String name){
		
		System.out.println(name);
		return "get  it  aleady";
	}
	
	@RequestMapping("/disabled.json")
	@ResponseBody
	public void disabled(String name){
		
//		System.out.println(name);
		log.info( DateUtil.currentDateTime()+" :"+  name);
	}
}
