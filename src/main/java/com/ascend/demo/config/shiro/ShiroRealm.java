package com.ascend.demo.config.shiro;

import java.util.HashSet;
import java.util.Set;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import com.ascend.demo.auth.domain.SystemUserDO;
import com.ascend.demo.auth.service.SystemUserService;

public class ShiroRealm extends AuthorizingRealm{

	@Autowired
	private SystemUserService userService;
	
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		//1.从principals 中获取登录用户的信息
		//2.利用登录的信息来获取用户权限信息（获取从数据库获取用户的角色和权限信息并且设置用户的权限或角色）
		//3.c创建AuthorizationInfo 的子类SimpleAuthorizationInfo
		SystemUserDO principal=(SystemUserDO)principals.getPrimaryPrincipal();
		
		Set<String> roles=new HashSet<>();
		//以下数据可以从数据库中获取
		roles.add("user");
		if("user1".equals(principal.getUserAccount())){
			roles.add("admin");
		}
		SimpleAuthorizationInfo info=new SimpleAuthorizationInfo(roles);	
		return info;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		System.out.println("shirorealm     ----------------");
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
		//SimpleAuthenticationInfo info=new SimpleAuthenticationInfo(user, user.getPassword(), getName());
		ByteSource salt=ByteSource.Util.bytes(userAccount);
		SimpleAuthenticationInfo info=new SimpleAuthenticationInfo(user, user.getPassword(), salt, getName());
		
		return info;
	}

}
