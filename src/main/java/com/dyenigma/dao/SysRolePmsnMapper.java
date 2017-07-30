package com.dyenigma.dao;

import com.dyenigma.core.Mapper;
import com.dyenigma.entity.SysRolePmsn;

import java.util.List;

public interface SysRolePmsnMapper extends Mapper<SysRolePmsn> {
    /**
     * Description: 根据角色ID查询所有的映射信息，用于角色管理菜单中勾选treeGrid
     * Name:findAllByRoleId
     * Author:Dyenigma
     * Time:2016/4/23 10:47
     * param:[roleId]
     * return:java.util.List<com.dyenigma.entity.SysRolePmsn>
     */
    List<SysRolePmsn> findAllByRoleId(String roleId);

    /**
     * Description: 查找所有的有映射记录的角色信息，用于自动分配新增加的默认权限菜单
     * Name:findAllRoleId
     * Author:Dyenigma
     * Time:2016/4/23 10:48
     * param:[]
     * return:java.util.List<java.lang.Integer>
     */
    List<String> findAllRoleId();

    /**
     * Description: 查找所有的映射菜单记录，用于判断某个菜单是否能删除
     * Name:findAllByPmsnId
     * Author:Dyenigma
     * Time:2016/4/23 10:48
     * param:[permissionId]
     * return:java.util.List<java.lang.Integer>
     */
    List<String> findAllByPmsnId(String permissionId);

    int delByPmsnId(String pmsnId);
}