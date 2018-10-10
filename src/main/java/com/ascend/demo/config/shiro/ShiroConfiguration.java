package com.ascend.demo.config.shiro;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authc.pam.AllSuccessfulStrategy;
import org.apache.shiro.authc.pam.ModularRealmAuthenticator;
import org.apache.shiro.cache.MemoryConstrainedCacheManager;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ShiroConfiguration {

	  //将自己的验证方式加入容器
	/**
	 * 主要realm
	 * @return
	 */
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
	/**
	 * 测试realm1
	 * @return
	 */
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
    /**
     * 测试realm2
     * @return
     */
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

    /**
     * 认证策略
     * @return
     */
    @Bean
    public ModularRealmAuthenticator myAuthenticator(){
    	ModularRealmAuthenticator authen=new ModularRealmAuthenticator();
    	authen.setAuthenticationStrategy(new AllSuccessfulStrategy());//设置认证策略
    	return authen;
    }
    
    /**  
     * cookie对象;  
     * rememberMeCookie()方法是设置Cookie的生成模版，比如cookie的name，cookie的有效时间等等。  
     * @return  
    */  
   @Bean  
   public SimpleCookie rememberMeCookie(){  
         //System.out.println("ShiroConfiguration.rememberMeCookie()");  
         //这个参数是cookie的名称，对应前端的checkbox的name = rememberMe  
         SimpleCookie simpleCookie = new SimpleCookie("rememberMe");  
         //<!-- 记住我cookie生效时间 ,单位秒;-->  
         simpleCookie.setMaxAge(20);  
         return simpleCookie;  
   }  
     
   /**  
     * cookie管理对象;  
     * rememberMeManager()方法是生成rememberMe管理器，而且要将这个rememberMe管理器设置到securityManager中  
     * @return  
    */  
   @Bean  
   public CookieRememberMeManager rememberMeManager(){  
         //System.out.println("ShiroConfiguration.rememberMeManager()");  
         CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();  
         cookieRememberMeManager.setCookie(rememberMeCookie());  
         //rememberMe cookie加密的密钥 建议每个项目都不一样 默认AES算法 密钥长度(128 256 512 位)  
         cookieRememberMeManager.setCipherKey(Base64.decode("2AvVhdsgUs0FSA3SDFAdag=="));  
         return cookieRememberMeManager;  
   }  
    
   /**
    * 权限管理，配置主要是Realm的管理认证
    * securityManager配置
    * @return
    */
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
        //使用shiro自带缓存
        securityManager.setCacheManager(new MemoryConstrainedCacheManager());
        //设置rememberMe 
        securityManager.setRememberMeManager(rememberMeManager());
        return securityManager;
    }

    //Filter工厂，设置对应的过滤条件和跳转条件
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        Map<String,String> map = new HashMap<String, String>();
        //匿名过滤器
        map.put("/","anon");
        //登出过滤器
        map.put("/logout","logout");
        map.put("/css/**", "anon");
		map.put("/js/**", "anon");
		map.put("/fonts/**", "anon");
		map.put("/img/**", "anon");
		map.put("/docs/**", "anon");
		map.put("/druid/**", "anon");
		map.put("/upload/**", "anon");
		map.put("/files/**", "anon");
        map.put("/home/index.action", "user");
//        map.put("/**","user");
        //对其他资源默认认证过滤 
        map.put("/**","authc");
        //对其他资源默认授权过滤
//        map.put("/**","perms");
       //登录
        shiroFilterFactoryBean.setLoginUrl("/login");
         //首页
        shiroFilterFactoryBean.setSuccessUrl("/home/index.action");
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
