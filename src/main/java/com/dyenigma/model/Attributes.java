/**
 * Title: Attributes.java
 * Package com.dyenigma.model
 * author dingdongliang
 * date 2015年9月23日 上午11:17:19
 * version V1.0
 * Copyright (c) 2015,dyenigma@163.com All Rights Reserved.
 */

package com.dyenigma.model;

import com.dyenigma.entity.BaseDomain;

/**
 * ClassName: Attributes
 * Description: 状态属性, TreeModel模型中调用
 * author dingdongliang
 * date 2015年9月23日 上午11:17:19
 *
 */

public class Attributes extends BaseDomain {
    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
