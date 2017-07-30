/**
 * Title: Json.java
 * Package com.dyenigma.model
 * author dingdongliang
 * date 2015年9月21日 下午5:28:56
 * version V1.0
 * Copyright (c) 2015,dyenigma@163.com All Rights Reserved.
 */

package com.dyenigma.model;

import com.dyenigma.entity.BaseDomain;

/**
 * ClassName: Json
 * Description:JSON格式回复信息
 * author dingdongliang
 * date 2015年9月21日 下午5:28:56
 *
 */

public class Json extends BaseDomain {
    private String title = "提示";
    private String message;
    private boolean status = false;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

}
