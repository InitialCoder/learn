/*package com.ascend.test;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ShiroTest {

	private static final transient Logger log=LoggerFactory.getLogger(ShiroTest.class);
	public static void main(String[] args) {
		log.info("the shiroTest is begin");
		
		Factory<SecurityManager> factory=new IniSecurityManagerFactory("classpath:shiro.ini");
		
		SecurityManager securityManager=factory.getInstance();
		
		SecurityUtils.setSecurityManager(securityManager);
		
		Subject currentUser =SecurityUtils.getSubject();
		
		Session session=currentUser.getSession();
		
		session.setAttribute("someKey", "value");
		
		String value=(String) session.getAttribute("someKey");
		
		log.info(value);
		
		if(!currentUser.isAuthenticated()){
			UsernamePasswordToken token=new UsernamePasswordToken("root","secret");
			token.setRememberMe(true);
			
			try {
				currentUser.login(token);
			} catch (UnknownAccountException uae) {
				log.info("There is no user with the AccountName "+token.getPrincipal());
			}catch(IncorrectCredentialsException ice ){
				log.info("The password of "+token.getPrincipal() +" is incorrect");
			}catch(LockedAccountException lae){
				log.info("The account for username "+token.getPrincipal()+" is locked. Please contact you administrator to unlock it");
			}catch(AuthenticationException ae){
				log.info("Authentication Exception");
			}
		}
		log.info("User ["+currentUser.getPrincipal()+"] logged in successfully" );
		
		if(currentUser.hasRole("schwartz")){
			log.info("Schwartz role be with you");
		}else{
			log.info("you has no Schewartz role");
		}
//		List<Permission> permiss=new ArrayList<Permission>(); 
		if(currentUser.isPermitted("lightsaber:weild")){
			log.info("You may use a lightsaber ring.  Use it wisely.");
		}else{
			log.info("Sorry, lightsaber rings are for schwartz masters only.");
		}
	
		 //a (very powerful) Instance Level permission:
        if (currentUser.isPermitted("winnebago:drive:eagle5")) {
            log.info("You are permitted to 'drive' the winnebago with license plate (id) 'eagle5'.  " +
                    "Here are the keys - have fun!");
        } else {
            log.info("Sorry, you aren't allowed to drive the 'eagle5' winnebago!");
        }

        if(currentUser.isPermitted("lightsaber:*")){
        	log.info("ightsaber is permis you");
        }else{
        	log.info("dis permiss you");
        }
        
        //all done - log out!
        currentUser.logout();
	}
}
*/