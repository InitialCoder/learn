package com.ascend.demo.config.web;


import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.util.HtmlUtils;

import com.ascend.demo.util.Assert;
import com.ascend.demo.util.JsoupUtil;


public class XssHttpServletRequestWrapper extends HttpServletRequestWrapper{

	HttpServletRequest orgRequest = null;  
  
	/**
	 * 不需要转义的富文本字段
	 */
	private static List<String> excludeParam;
	
	static{
		excludeParam=new ArrayList<String>();
		excludeParam.add("content");
		excludeParam.add("contents");
		excludeParam.add("descrip");
		excludeParam.add("answer");
		excludeParam.add("remessage");
		excludeParam.add("smessage");
		excludeParam.add("reply");
		excludeParam.add("theme");
		excludeParam.add("locale");
		excludeParam.add("role");
		excludeParam.add("site_preference");
		excludeParam.add("format");
		excludeParam.add("verifyMsg");
		excludeParam.add("question");
		excludeParam.add("otherReason");
	}
	
	
    public XssHttpServletRequestWrapper(HttpServletRequest request) {  
        super(request);  
        orgRequest = request;
    }  
  
    /** 
    * 覆盖getParameter方法，将参数名和参数值都做xss过滤。<br/> 
    * 如果需要获得原始的值，则通过super.getParameterValues(name)来获取<br/> 
    * getParameterNames,getParameterValues和getParameterMap也可能需要覆盖 
    */  
    @Override  
    public String getParameter(String name) {
        name = JsoupUtil.clean(name);
        String value = super.getParameter(name);  
        if (StringUtils.isNotBlank(value)) {
            value = JsoupUtil.clean(value);
            if(!handleExcludeParam(value)){
            	value=HtmlUtils.htmlEscape(value);
            	return value;
            } 
        }
        return value;  
    }  
    
    @Override
    public String[] getParameterValues(String name) {
    	String[] arr = super.getParameterValues(name); 
    	boolean flag=handleExcludeParam(name);
    	if(arr != null){
    		for (int i=0;i<arr.length;i++) {
    			arr[i] = JsoupUtil.clean(arr[i]);
    			if(!flag){
    				arr[i]=HtmlUtils.htmlEscape(arr[i]);
    	         } 
    		}
    	}
    	return arr;
    }
    
  
    /** 
    * 覆盖getHeader方法，将参数名和参数值都做xss过滤。<br/> 
    * 如果需要获得原始的值，则通过super.getHeaders(name)来获取<br/> 
    * getHeaderNames 也可能需要覆盖 
    */  
    @Override  
    public String getHeader(String name) {  
        name = JsoupUtil.clean(name);
        String value = super.getHeader(name);  
        if (StringUtils.isNotBlank(value)) {  
            value = JsoupUtil.clean(value); 
        }  
        return value;  
    }  
  
    /** 
    * 获取最原始的request 
    * 
    * @return 
    */  
    public HttpServletRequest getOrgRequest() {  
        return orgRequest;  
    }  
  
    /** 
    * 获取最原始的request的静态方法 
    * 
    * @return 
    */  
    public static HttpServletRequest getOrgRequest(HttpServletRequest req) {  
        if (req instanceof XssHttpServletRequestWrapper) {  
            return ((XssHttpServletRequestWrapper) req).getOrgRequest();  
        }  
  
        return req;  
    }  

    
    public static boolean handleExcludeParam(String param){
    	
    	if(Assert.isEmpty(param)){
    		return true;
    	}
    	if (excludeParam == null || excludeParam.isEmpty()) {
			return false;
		}
    	for (String str : excludeParam) {
			if(str.equals(param)){
				return true;
			}
		}
    	return false;
    	
    }

}
