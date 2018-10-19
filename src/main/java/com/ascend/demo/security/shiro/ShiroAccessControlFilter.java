package com.ascend.demo.security.shiro;

import java.io.IOException;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.StringUtils;
import org.apache.shiro.web.filter.authz.AuthorizationFilter;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.ascend.demo.mgr.auth.service.ResourceService;

/**
 *  系统所有资源认证和权限过滤器
 *  本过滤器自动拦截请求路径，若用户未登陆认证则直接跳转至login页面，如果请求是ajax，则返回401状态码
 *  本过滤器自动判断用户是否有改资源的权限，若用户无权限则跳转至403页面，如果请求是ajax，则返回403状态码，如果系统无此资源，则返回404页面或者状态码。
 * @author wu
 *
 */
public class ShiroAccessControlFilter extends AuthorizationFilter{

	@Autowired
 	private ResourceService resource;
	
	/**
	 * 重写了isAccessAllowed方法，若返回为false，最后会将请求交给onAccessDenied 处理
	 */
	@Override
	protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue)
			throws Exception {
		
		String shiroAuthType="login";
		boolean flag=false;
		
		HttpServletRequest httpRequest=(HttpServletRequest) request;
		//获取请求路径
		String path=httpRequest.getServletPath();
		
		Subject subject =getSubject(httpRequest, response);
		
		if(null!=subject&&subject.isAuthenticated()){
			shiroAuthType="authrize";
			String perm=getPerm(path);
			if(null==perm){
				shiroAuthType="404";
				flag= false;
			}else if(subject.isPermitted(perm)){//是否有资源权限
				flag= true;
			}
		} 
		httpRequest.setAttribute("shiroAuthType", shiroAuthType);
		return flag;
	}

	@Override
	protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws IOException  {
		String type=(String) request.getAttribute("shiroAuthType");
		 Subject subject = getSubject(request, response);
		if(isAjax((HttpServletRequest)request)){
			switch(type){
				case "login":WebUtils.toHttp(response).sendError(401);break;//未登陆状态码
				case "authrize":WebUtils.toHttp(response).sendError(403);break;//返回为授权状态码
				case "404":WebUtils.toHttp(response).sendError(404);break;
			}
		}else{
			switch(type){
			case "login": 
				//结合原有框架逻辑
				// If the subject isn't identified, redirect to login URL
		        if (subject.getPrincipal() == null) {
		            saveRequestAndRedirectToLogin(request, response);
		        } else {
		            // If subject is known but not authorized, redirect to the unauthorized URL if there is one
		            // If no unauthorized URL is specified, just return an unauthorized HTTP status code
		            String unauthorizedUrl = getUnauthorizedUrl();
		            //SHIRO-142 - ensure that redirect _or_ error code occurs - both cannot happen due to response commit:
		            if (StringUtils.hasText(unauthorizedUrl)) {
		                WebUtils.issueRedirect(request, response, unauthorizedUrl);
		            } else {
		                WebUtils.toHttp(response).sendError(HttpServletResponse.SC_UNAUTHORIZED);
		            }
		        }
		        break;
			case "authrize":
				WebUtils.issueRedirect(request, response, "403");
				break;//返回为授权页面
			case "404":
				WebUtils.issueRedirect(request, response, "404");
				break;//返回404页面
			}
		}
		
		return false;
	}
	
	private String getPerm(String url){
		
		return resource.getPermByUrl(url);
	}
	
    private boolean isAjax(HttpServletRequest request) {
        String header = request.getHeader("x-requested-with");
        if (null != header && "XMLHttpRequest".endsWith(header)) {
            return true;
        }
        return false;

    }
    
}
