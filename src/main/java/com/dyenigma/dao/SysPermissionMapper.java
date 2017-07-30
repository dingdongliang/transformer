package com.dyenigma.dao;

import com.dyenigma.core.Mapper;
import com.dyenigma.entity.SysPermission;

import java.util.List;

public interface SysPermissionMapper extends Mapper<SysPermission> {
    /**
     * Description: 超级管理员权限查询，type标志查询是菜单项(M)还是操作项(O)
     * Name:adminMenu
     * Author:Dyenigma
     * Time:2016/4/23 10:45
     * param:[type]
     * return:java.util.List<com.dyenigma.entity.SysPermission>
     */
    List<SysPermission> adminMenu(String type);

    /**
     * Description: 普通用户权限查询，type标志查询是菜单项(M)还是操作项(O)
     * Name:usersMenu
     * Author:Dyenigma
     * Time:2016/4/23 10:45
     * param:[userId, type]
     * return:java.util.List<com.dyenigma.entity.SysPermission>
     */
    List<SysPermission> usersMenu(String userId, String type);

    /**
     * Description: 查询某个父菜单的子项
     * Name:findByPid
     * Author:Dyenigma
     * Time:2016/4/23 10:46
     * param:[pid]
     * return:java.util.List<com.dyenigma.entity.SysPermission>
     */
    List<SysPermission> findByPid(String pid);


    /**
     * Description: 获取所有可添加子项的权限信息
     * Name:findSuperFunc
     * Author:Dyenigma
     * Time:2016/4/23 10:46
     * param:[]
     * return:java.util.List<com.dyenigma.entity.SysPermission>
     */
    List<SysPermission> findSuperFunc();

    /**
     * Description: 获取所有的权限，用于角色权限分配
     * Name:findAllMenu
     * Author:Dyenigma
     * Time:2016/4/23 10:46
     * param:[]
     * return:java.util.List<com.dyenigma.entity.SysPermission>
     */
    List<SysPermission> findAllMenu();

    /**
     * Description: 获取所有的默认有效权限，用于默认权限分配
     * Name:findAllDefault
     * Author:Dyenigma
     * Time:2016/4/23 10:46
     * param:[]
     * return:java.util.List<com.dyenigma.entity.SysPermission>
     */
    List<SysPermission> findAllDefault();

    /**
     * Description: 查找所有一级根目录
     * Name:findRootMenu
     * Author:Dyenigma
     * Time:2016/4/24 17:27
     * param:[]
     * return:java.util.List<com.dyenigma.entity.SysPermission>
     */
    List<SysPermission> findRootMenu();
}