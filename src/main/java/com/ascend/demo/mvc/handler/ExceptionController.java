package com.ascend.demo.mvc.handler;

import javax.naming.AuthenticationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import com.ascend.demo.common.ext.util.ShiroUtils;

@ControllerAdvice
public class ExceptionController {
	 
	private Logger logger=LoggerFactory.getLogger(ExceptionController.class);
	
	@ExceptionHandler(AuthenticationException.class)
	@ResponseStatus(HttpStatus.OK)
	public ModelAndView authenticationException(RuntimeException exception){
		logger.info(ShiroUtils.getUser().getUserAccount()+" 权限不足！");
		ModelAndView view=new ModelAndView("error/403");
		view.addObject("exceptionMsg", exception.getMessage());
		return view;
	}
	

}
