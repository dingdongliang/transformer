

/**
 * Title: BaseController.java
 * Package com.dyenigma.controller
 * author dingdongliang
 * date 2015年9月14日 下午5:19:50
 * version V1.0
 * Copyright (c) 2015,dyenigma@163.com All Rights Reserved.
 */

package com.dyenigma.controller;

import com.dyenigma.model.Json;
import com.dyenigma.shiro.LoginRealm;
import com.dyenigma.util.Constants;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.mgt.RealmSecurityManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * author dingdongliang
 * ClassName: BaseController
 * Description: 控制器基类，用于编写统一使用方法
 * date 2015年9月14日 下午5:19:50
 */

public class BaseController {

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    protected Json getMessage(boolean flag) {
        Json json = new Json();
        if (flag) {
            json.setStatus(true);
            json.setMessage(Constants.POST_DATA_SUCCESS);
        } else {
            json.setMessage(Constants.POST_DATA_FAIL);
        }
        return json;
    }

    /**
     * 更改权限后，调用该方法刷新用户权限缓存,在用户角色控制器里调用
     */
    protected void refreshRealm() {
        RealmSecurityManager securityManager =
                (RealmSecurityManager) SecurityUtils.getSecurityManager();
        LoginRealm userRealm = (LoginRealm) securityManager.getRealms().iterator().next();
        userRealm.clearCachedAuthorizationInfo(SecurityUtils.getSubject().getPrincipal().toString());
    }
}
