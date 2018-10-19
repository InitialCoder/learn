package com.ascend.demo.mvc.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ascend.demo.common.ext.util.ShiroUtils;

@Controller
public class LoginController {

	private Logger log=LoggerFactory.getLogger(LoginController.class);
	
	@GetMapping({"/","login","login.action","index.action"})
	public String login(){ 
	
		return "login";
	
	}
	
	@PostMapping({"/login"})
	public String shiroLogin(@RequestParam String userAccount, @RequestParam String password){
		
		/**
		 * 获取当前用户
		 */
		Subject currentUser=SecurityUtils.getSubject();
			
		UsernamePasswordToken token=new UsernamePasswordToken(userAccount,password);
		token.setRememberMe(false);
		
		try{
			currentUser.login(token);
			return "redirect:home/index";
		}catch(Exception e){
			log.info(e.getMessage());
			return "login";
		}
		
		/*catch (UnknownAccountException uae) {
			log.info("There is no user with the AccountName "+token.getPrincipal());
			return R.error("There is no user with the AccountName");
		}catch(IncorrectCredentialsException ice ){
			log.info("The password of "+token.getPrincipal() +" is incorrect");
			return R.error("There is no user with the AccountName");
		}catch(LockedAccountException lae){
			log.info("The account for username "+token.getPrincipal()+" is locked. Please contact you administrator to unlock it");
		}catch(AuthenticationException ae){
			log.info("Authentication Exception");
		}catch(Exception e){
			log.info(e.getMessage());
		}*/
	}

	
	@GetMapping("/logout")
	String logout() {
		ShiroUtils.logout();
		return "redirect:/login";
	}

}
