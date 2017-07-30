/**
 * Title: MenuModel.java
 * Package com.dyenigma.model
 * author dingdongliang
 * date 2015年9月14日 下午6:26:04
 * version V1.0
 * Copyright (c) 2015,dyenigma@163.com All Rights Reserved.
 */

package com.dyenigma.model;

import com.dyenigma.entity.BaseDomain;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.util.ArrayList;
import java.util.List;

/**
 * ClassName: MenuModel
 * Description: 菜单模型类
 * author dingdongliang
 * date 2015年9月14日 下午6:26:04
 */
public class MenuModel extends BaseDomain {
    private String id;
    private String pid;
    private String name;
    private String iconCls;
    private String url;
    @JsonManagedReference
    private List<MenuModel> child = new ArrayList<MenuModel>();


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIconCls() {
        return iconCls;
    }

    public void setIconCls(String iconCls) {
        this.iconCls = iconCls;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<MenuModel> getChild() {
        return child;
    }

    public void setChild(List<MenuModel> child) {
        this.child = child;
    }
}
