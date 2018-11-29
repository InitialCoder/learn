package com.ascend.demo.config;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;

/**
 * 由于Druid暂时不在Spring Boot中的直接支持，故需要进行配置信息的定制：
 * 自动填充方式
 * @author wu
 *
 */
@SuppressWarnings("AlibabaRemoveCommentedCode")
@Configuration
public class DruidDBConfiguration {

	private Logger logger = Logger.getLogger(this.getClass());	//log4j日志
	
    @Bean     //声明其为Bean实例
    @Primary  //在同样的DataSource中，首先使用被标注的DataSource
    @ConfigurationProperties("spring.datasource")//springboot 的自动填充方式，也可以用此方式配置多数据源
    public DataSource dataSource(){
    	return DruidDataSourceBuilder.create().build();
    }


    /**
     * Druid内置提供了一个StatViewServlet用于展示Druid的统计信息。
		这个StatViewServlet的用途包括：
		提供监控信息展示的html页面
		提供监控信息的JSON API
     * @return
     */
    @Bean
    public ServletRegistrationBean druidServlet() {
        ServletRegistrationBean reg = new ServletRegistrationBean();
        reg.setServlet(new StatViewServlet());
        reg.addUrlMappings("/druid/*");
        reg.addInitParameter("allow", ""); //可以访问的白名单id 不支持IPv6
        reg.addInitParameter("deny", "");//黑名单，优先于白名单 不支持IPv6
        return reg;
    }
    
    /**
     * druid 配置WebStatFilter 网络统计以及监控url的filter
     * @return
     */
    @Bean public FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new WebStatFilter());
        filterRegistrationBean.addUrlPatterns("/*");
        filterRegistrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
//        filterRegistrationBean.addInitParameter("profileEnable", "true");
//        filterRegistrationBean.addInitParameter("principalCookieName","USER_COOKIE");//从cookie中得到用户名称
//        filterRegistrationBean.addInitParameter("principalSessionName","USER_SESSION");//当前session的用户是谁
        filterRegistrationBean.addInitParameter("DruidWebStatFilter","/*");
        return filterRegistrationBean;
    }
}
