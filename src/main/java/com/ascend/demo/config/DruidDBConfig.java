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
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;

/**
 * 由于Druid暂时不在Spring Boot中的直接支持，故需要进行配置信息的定制：
 * 网上常用方式
 * @author wu
 *
 */
//@SuppressWarnings("AlibabaRemoveCommentedCode")
//@Configuration
public class DruidDBConfig {

	private Logger logger = Logger.getLogger(this.getClass());	//log4j日志
	

    @Value("${spring.datasource.url}")
    private String dbUrl;
    
    @Value("${spring.datasource.username}")
    private String username;
    
    @Value("${spring.datasource.password}")
    private String password;
    
    @Value("${spring.datasource.driver-class-name}")
    private String driverClassName;
    
    @Value("${spring.datasource.initialSize}")
    private int initialSize;
    
    @Value("${spring.datasource.minIdle}")
    private int minIdle;
    
    @Value("${spring.datasource.maxActive}")
    private int maxActive;
    
    @Value("${spring.datasource.maxWait}")
    private int maxWait;
    
    @Value("${spring.datasource.timeBetweenEvictionRunsMillis}")
    private int timeBetweenEvictionRunsMillis;
    
    @Value("${spring.datasource.minEvictableIdleTimeMillis}")
    private int minEvictableIdleTimeMillis;
    
    @Value("${spring.datasource.validationQuery}")
    private String validationQuery;
    
    @Value("${spring.datasource.testWhileIdle}")
    private boolean testWhileIdle;
    
    @Value("${spring.datasource.testOnBorrow}")
    private boolean testOnBorrow;
    
    @Value("${spring.datasource.testOnReturn}")
    private boolean testOnReturn;
    
    @Value("${spring.datasource.poolPreparedStatements}")
    private boolean poolPreparedStatements;
    
    @Value("${spring.datasource.maxPoolPreparedStatementPerConnectionSize}")
    private int maxPoolPreparedStatementPerConnectionSize;
    
    @Value("${spring.datasource.filters}")
    private String filters;
    
    @Value("{spring.datasource.connectionProperties}")
    private String connectionProperties;
    
    @Bean     //声明其为Bean实例
    @Primary  //在同样的DataSource中，首先使用被标注的DataSource
    public DataSource dataSource(){
    	DruidDataSource datasource = new DruidDataSource();
    	
    	datasource.setUrl(this.dbUrl);
    	datasource.setUsername(username);
    	datasource.setPassword(password);
    	datasource.setDriverClassName(driverClassName);
    	
    	//configuration
    	datasource.setInitialSize(initialSize);
    	datasource.setMinIdle(minIdle);
    	datasource.setMaxActive(maxActive);
    	datasource.setMaxWait(maxWait);
    	datasource.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
    	datasource.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
    	datasource.setValidationQuery(validationQuery);
    	datasource.setTestWhileIdle(testWhileIdle);
    	datasource.setTestOnBorrow(testOnBorrow);
    	datasource.setTestOnReturn(testOnReturn);
    	datasource.setPoolPreparedStatements(poolPreparedStatements);
    	datasource.setMaxPoolPreparedStatementPerConnectionSize(maxPoolPreparedStatementPerConnectionSize);
    	try {
			datasource.setFilters(filters);
		} catch (SQLException e) {
			logger.error("druid configuration initialization filter", e);
		}
    	datasource.setConnectionProperties(connectionProperties);
    	
    	return datasource;
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
