package com.ascend.demo.mvc.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/login")
public class LoginController {

	private Logger log=LoggerFactory.getLogger(LoginController.class);
	
	@RequestMapping({"/","login","login.action","index.action"})
	public String login(){ 
	
		return "login";
	
	}
	
	@RequestMapping({"/shirologin.action"})
	public String shiroLogin(@RequestParam String userAccount, @RequestParam String password){
		
		Subject currentUser=SecurityUtils.getSubject();
		
		if(!currentUser.isAuthenticated()){
			
			UsernamePasswordToken token=new UsernamePasswordToken(userAccount,password);
			
			token.setRememberMe(true);
			
			try{
				currentUser.login(token);
			}catch (UnknownAccountException uae) {
				log.info("There is no user with the AccountName "+token.getPrincipal());
				return "login";
			}catch(IncorrectCredentialsException ice ){
				log.info("The password of "+token.getPrincipal() +" is incorrect");
				return "login";
			}catch(LockedAccountException lae){
				log.info("The account for username "+token.getPrincipal()+" is locked. Please contact you administrator to unlock it");
				return "login";
			}catch(AuthenticationException ae){
				log.info("Authentication Exception");
				return "login";
			}catch(Exception e){
				log.info(e.getMessage());
				return "login";
			}
		}
		
		return "redirect:/home/index.action";
		
	}

}
