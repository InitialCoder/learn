package com.ascend.demo.ext.util;

import java.security.Principal;
import java.util.Collection;
import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

import com.ascend.demo.auth.domain.SystemUserDO;

public class ShiroUtils {
    @Autowired
    private static SessionDAO sessionDAO;

    public static Subject getSubjct() {
        return SecurityUtils.getSubject();
    }
    public static SystemUserDO getUser() {
        Object object = getSubjct().getPrincipal();
        return (SystemUserDO)object;
    }
    public static String getUserAccount() {
        return getUser().getUserAccount();
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
