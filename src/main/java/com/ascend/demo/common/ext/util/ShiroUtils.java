package com.ascend.demo.common.ext.util;

import java.security.Principal;
import java.util.Collection;
import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.mgt.RealmSecurityManager;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

import com.ascend.demo.common.domain.UserDO;
import com.ascend.demo.security.shiro.ShiroRealm;

public class ShiroUtils {
    @Autowired
    private static SessionDAO sessionDAO;

    public static Subject getSubjct() {
    	Subject subject=SecurityUtils.getSubject();
        return subject;
    }
    public static UserDO getUser() {
        Object object = getSubjct().getPrincipal();
        return (UserDO)object;
    }
    public static String getUserAccount() {
        return getUser().getUserAccount();
    }
    /**
     * 清除用户所有的权限资源
     */
    public static void clearAuthorz(){
    	RealmSecurityManager  rsm=(RealmSecurityManager)SecurityUtils.getSecurityManager();
    	ShiroRealm realm=(ShiroRealm)rsm.getRealms().iterator().next();
    	realm.clearAuthorz();
    	
    }
    
    /**
     * 清除用户认证信息
     */
    public static void clearAuthenz(){
    	RealmSecurityManager  rsm=(RealmSecurityManager)SecurityUtils.getSecurityManager();
    	ShiroRealm realm=(ShiroRealm)rsm.getRealms().iterator().next();
    	realm.clearAuthorz();
    	
    }
    public static void logout() {
        getSubjct().logout();
    }

    
    public static List<Principal> getPrinciples() {
        List<Principal> principals = null;
        Collection<Session> sessions = sessionDAO.getActiveSessions();
        
        return principals;
    }
}
