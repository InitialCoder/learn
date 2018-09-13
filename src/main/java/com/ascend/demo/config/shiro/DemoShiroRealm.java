package com.ascend.demo.config.shiro;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthenticatingRealm;
import org.springframework.beans.factory.annotation.Autowired;

import com.ascend.demo.auth.domain.SystemUserDO;
import com.ascend.demo.auth.service.SystemUserService;

public class DemoShiroRealm extends AuthenticatingRealm{

	@Autowired
	private SystemUserService userService;

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		System.out.println(token);
		//1 AuthenticationToken 转为userNamePasswordToken
		//2 从usernamePasswordToken 中获取username;
		//查询数据库
		//4比对和异常处理
		//5 构件authentication 并且返回
		UsernamePasswordToken pasToken=(UsernamePasswordToken) token;
		String userAccount=pasToken.getUsername();
		SystemUserDO user=userService.findByUserAccount(userAccount);
		if(user==null){
			throw new  UnknownAccountException("用户不存在！");
		}
		//参数说明： principal 认证的实体信息 可以是userAccount 也可以是数据表中对应的实体对象。
		//		credential:密码
		// 	readmName=父类的 getName();
		SimpleAuthenticationInfo info=new SimpleAuthenticationInfo(user, user.getPassword(), getName());
		
		
		return info;
	}
	
	
}
