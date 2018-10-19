package com.ascend.demo.security.xss;


import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.util.HtmlUtils;

import com.ascend.demo.common.ext.util.Assert;
import com.ascend.demo.common.ext.util.JsoupUtil;


public class XssHttpServletRequestWrapper extends HttpServletRequestWrapper{

	HttpServletRequest orgRequest = null;  
  

	/**
	 * 不需要转码的富文本字段    字段名称，是否模糊匹配
	 */
	private static Map<String,Boolean> excludeParam;
	
	static{
		excludeParam=new HashMap<String,Boolean>();
		excludeParam.put("content",false);
		excludeParam.put("contents",false);
		excludeParam.put("descrip",false);
		excludeParam.put("answer",false);
		excludeParam.put("remessage",false);
		excludeParam.put("smessage",false);
		excludeParam.put("reply",false);
		excludeParam.put("theme",false);
		excludeParam.put("locale",false);
		excludeParam.put("role",false);
		excludeParam.put("site_preference",false);
		excludeParam.put("format",false);
		excludeParam.put("verifyMsg",false);
		excludeParam.put("question",false);
		excludeParam.put("otherReason",false);
		excludeParam.put("imageInput",false);
		excludeParam.put("dataList",false);
		excludeParam.put("prompt",false);
		excludeParam.put("vdef",true);
		excludeParam.put("sms",true);
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
		if(excludeParam.containsKey(param)){
			return true;
		}else{
			for (Map.Entry<String,Boolean> entry : excludeParam.entrySet()) {
				if(entry.getValue()&&param.indexOf(entry.getKey())>=0){
					return true; 
				}
			}
    	return false;
		}
    
    }
}
