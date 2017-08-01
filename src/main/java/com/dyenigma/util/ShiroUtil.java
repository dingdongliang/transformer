package com.dyenigma.util;

import com.dyenigma.shiro.LoginRealm;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.mgt.RealmSecurityManager;

/**
 * Description:刷新用户权限缓存类
 * author  dyenigma
 * date 2017/8/1 16:51
 */
public class ShiroUtil {
    /**
     * 更改权限后，调用该方法刷新用户权限缓存,在涉及权限变换控制器里调用
     */
    public static void refreshRealm() {
        RealmSecurityManager securityManager =
                (RealmSecurityManager) SecurityUtils.getSecurityManager();
        LoginRealm userRealm = (LoginRealm) securityManager.getRealms().iterator().next();
        userRealm.clearCachedAuthorizationInfo(SecurityUtils.getSubject().getPrincipal().toString());
    }
}
