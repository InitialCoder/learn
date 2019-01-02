package com.ascend.demo.common.redis.shiro;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * shiro加密配置
 * @author wu
 *
 */
//@ConfigurationProperties(prefix="ts.shiro.conf")
//@PropertySource("classpath:shiro.properties")
public class STShiroConf {

	/**
	 * session时效
	 */
	private Long sessionTimeout=(long) 1800000;

	public Long getSessionTimeout() {
		return sessionTimeout;
	}

	public void setSessionTimeout(Long sessionTimeout) {
		this.sessionTimeout = sessionTimeout;
	}
	
	
}
