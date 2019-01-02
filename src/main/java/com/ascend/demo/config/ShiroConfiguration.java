package com.ascend.demo.config;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.Filter;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authc.pam.AllSuccessfulStrategy;
import org.apache.shiro.authc.pam.ModularRealmAuthenticator;
import org.apache.shiro.cache.MemoryConstrainedCacheManager;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.Cookie;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.ascend.demo.common.redis.shiro.RedisCacheManager;
import com.ascend.demo.common.redis.shiro.RedisSessionDAO;
import com.ascend.demo.common.redis.shiro.STShiroConf;
import com.ascend.demo.common.redis.shiro.ShiroSessionManager;
import com.ascend.demo.security.shiro.ShiroAccessControlFilter;
import com.ascend.demo.security.shiro.ShiroRealm;

@Configuration
public class ShiroConfiguration {

	/*@Value("${cacheType}")
    private String cacheType;*/
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
        securityManager.setSessionManager(sessionManager());
        return securityManager;
    }

    //Filter工厂，设置对应的过滤条件和跳转条件
    //过滤URL匹配规则：有限执行最小匹配的过滤器，再执行后面的过滤器，和基本的webFilter 执行顺序是一样的
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        Map<String,String> map = new HashMap<String, String>();
        //匿名过滤器
        map.put("/","anon");
        //登出过滤器
        map.put("/login","anon");
        map.put("/logout","logout");
        map.put("/css/**", "anon");
		map.put("/js/**", "anon");
		map.put("/fonts/**", "anon");
		map.put("/img/**", "anon");
		map.put("/docs/**", "anon");
		map.put("/druid/**", "anon");
		map.put("/upload/**", "anon");
		map.put("/files/**", "anon");
		map.put("/4*", "anon");
		map.put("/5*", "anon");
        map.put("/home/index.action", "anon");
//        map.put("/**","user");
        //对系统后台资源开启自定义过滤器
        map.put("/mgr/**","shiroAccess");
        //对其他资源默认认证过滤 
        map.put("/**","authc");
        //对其他资源默认授权过滤
//        map.put("/**","perms");
       //登录
        shiroFilterFactoryBean.setLoginUrl("/login");
         //首页
        shiroFilterFactoryBean.setSuccessUrl("/home/index.action");
        //错误页面，认证不通过跳转
        shiroFilterFactoryBean.setUnauthorizedUrl("/403");
        Map<String,Filter> filters=new HashMap<String,Filter>();
        filters.put("shiroAccess", shiroAccessControlFilter());
        shiroFilterFactoryBean.setFilters(filters);
        shiroFilterFactoryBean.setFilterChainDefinitionMap(map);
        return shiroFilterFactoryBean;
    }

    /**
     * 此处要有bean注解，不然shiroAccessControlFilter  里面的autowired不生效
     * @return
     */
    @Bean
    public ShiroAccessControlFilter shiroAccessControlFilter(){
    	ShiroAccessControlFilter filter=new ShiroAccessControlFilter();
    	return filter;
    }
    
    /**
     * 自定义cache策略的实现，只需要将EhCacheManager的配置文件定位到自己写的配置文件就可以了
     * @return
     *//*
    @Bean
    public EhCacheManager ehCacheManager(){
    	
    	EhCacheManager  ehCacheManager =new EhCacheManager();
    	ehCacheManager.setCacheManagerConfigFile("classpath:config/ehcache-shiro.xml");
    	return ehCacheManager;
    }*/
    
    @Bean
    public RedisCacheManager redisCacheManager() {
        return new RedisCacheManager();
    }
    
    @Bean
    public RedisSessionDAO redisSessionDAO(){
    	return new RedisSessionDAO();
    }

    @Bean
    public SessionManager sessionManager() {
    	ShiroSessionManager sessionManager = new ShiroSessionManager();
        sessionManager.setSessionDAO(redisSessionDAO());
        sessionManager.setGlobalSessionTimeout(getSTShiroConf().getSessionTimeout());
        sessionManager.setCacheManager(redisCacheManager());
        sessionManager.setDeleteInvalidSessions(true);//删除过期的session
        sessionManager.setSessionIdCookieEnabled(true);
		sessionManager.setSessionIdCookie(sessionIdCookie());
        return sessionManager;
    }
    
    //设置cookie
    @Bean
    public Cookie sessionIdCookie(){
    	Cookie sessionIdCookie=new SimpleCookie("STID");
    	sessionIdCookie.setMaxAge(-1);
    	sessionIdCookie.setHttpOnly(true);
    	return sessionIdCookie;
    }
   
    @Bean("sTShiroConf")
    @Primary
    public STShiroConf getSTShiroConf(){
    	return new STShiroConf();
    }
    
    //加入注解的使用，不加入这个注解不生效
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }
}
