package com.ascend.xssdemo.config.web;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

@WebFilter(filterName="xssMyfilter",urlPatterns="/*") 
public class XssFilter implements Filter{

	private static final Log logger = LogFactory.getLog(XssFilter.class);
	 
	public List<String> excludes = new ArrayList<>();
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		if(logger.isDebugEnabled()){
			logger.debug("xss filter init ====================");
		}
		String temp = "/favicon.ico,/symbol-defs.svg,/getnotice.json,*/index.action,"
				+ "*/login,/images/*,/img/*,/libs/*,/js/*,/css/*,*/kindeditor/*,/404,/405";
		if (temp != null) {
			String[] url = temp.split(",");
			for (int i = 0; url != null && i < url.length; i++) {
				excludes.add(url[i]);
			}
		}
	}
  
    @Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException,ServletException {
    	if(logger.isDebugEnabled()){
  			logger.debug("xss filter is open");
  		}
  		
  		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
  		if(handleExcludeURL(req, resp)){
  			filterChain.doFilter(request, response);
			return;
		}
  		
  		XssHttpServletRequestWrapper xssRequest = new XssHttpServletRequestWrapper((HttpServletRequest) request);
  		filterChain.doFilter(xssRequest, response);
    }
    
    private boolean handleExcludeURL(HttpServletRequest request, HttpServletResponse response) {
 
		if (excludes == null || excludes.isEmpty()) {
			return false;
		}
 
		String url = request.getServletPath();
		for (String pattern : excludes) {
			Pattern p = Pattern.compile("^" + pattern);
			Matcher m = p.matcher(url);
			if (m.find()) {
				return true;
			}
		}
 
		return false;
	}
 
	@Override
	public void destroy() {}  


}
