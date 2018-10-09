package com.ascend.demo.config.shiro;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authc.pam.AllSuccessfulStrategy;
import org.apache.shiro.authc.pam.ModularRealmAuthenticator;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ShiroConfiguration {

	  //将自己的验证方式加入容器
	@Bean
	public ShiroRealm  shiroRealm() {
		ShiroRealm realm = new ShiroRealm ();
		HashedCredentialsMatcher hash=new HashedCredentialsMatcher();
		hash.setHashAlgorithmName("MD5");
		hash.setHashIterations(1024);
		realm.setCredentialsMatcher(hash);
		hash.setStoredCredentialsHexEncoded(true);
		return realm;
	}
    @Bean
    public DemoShiroRealm  realm1() {
        DemoShiroRealm realm = new DemoShiroRealm ();
        HashedCredentialsMatcher hash=new HashedCredentialsMatcher();
        hash.setHashAlgorithmName("MD5");
        hash.setHashIterations(1024);
        realm.setCredentialsMatcher(hash);
        hash.setStoredCredentialsHexEncoded(true);
        return realm;
    }
    @Bean
    public SecondShiroRealm  realm2() {
    	SecondShiroRealm realm = new SecondShiroRealm ();
    	HashedCredentialsMatcher hash=new HashedCredentialsMatcher();
    	hash.setHashAlgorithmName("SHA1");
    	hash.setHashIterations(1024);
    	hash.setStoredCredentialsHexEncoded(true);
    	realm.setCredentialsMatcher(hash);
    	return realm;
    }

    @Bean
    public ModularRealmAuthenticator myAuthenticator(){
    	ModularRealmAuthenticator authen=new ModularRealmAuthenticator();
    	authen.setAuthenticationStrategy(new AllSuccessfulStrategy());//设置认证策略
    	return authen;
    }
    
    //权限管理，配置主要是Realm的管理认证
    @Bean
    public SecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
//        securityManager.setRealm(myShiroRealm()); 单realm 写法
        //多realm写法  myAuthenticator 可以设置认证策略，也可在里面配置多realms 属性
       /* securityManager.setAuthenticator(myAuthenticator());*/
        //直接将realMs 赋值给securityManager  好处：方便使用，权限检验的时候是需要这样配置滴
        Collection<Realm> realms=new ArrayList<Realm>();
//    	realms.add(realm1());
//    	realms.add(realm2());
        realms.add(shiroRealm());
        securityManager.setRealms(realms);
        return securityManager;
    }

    //Filter工厂，设置对应的过滤条件和跳转条件
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        Map<String,String> map = new HashMap<String, String>();
        //匿名过滤器
        map.put("/login/*","anon");
        //登出过滤器
        map.put("/login/logout","logout");
        //对其他资源默认认证过滤
        map.put("/**","authc");
        //对其他资源默认授权过滤
//        map.put("/**","perms");
       //登录
        shiroFilterFactoryBean.setLoginUrl("/login/index.action");
         //首页
        shiroFilterFactoryBean.setSuccessUrl("/home/index");
        //错误页面，认证不通过跳转
        shiroFilterFactoryBean.setUnauthorizedUrl("/error");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(map);
        return shiroFilterFactoryBean;
    }

    //加入注解的使用，不加入这个注解不生效
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }
	
}
