package com.dyenigma.service;

import com.dyenigma.entity.SysRole;
import com.dyenigma.entity.SysUserRole;

import java.util.List;


/**
 * Description:
 * author  dyenigma
 * date 2017/07/21
 */
public interface ISysUserRoleService extends IBaseService<SysUserRole> {
    /**
     * 好代码自己会说话
     * param roleId
     * return
     */
    List<SysRole> findAllByUserId(String userId);

    /**
     * Description: 根据用户ID获取所有用户角色映射关系
     * Name:findByUserId
     * Author:Dyenigma
     * Time:2016/4/28 22:13
     * param:[userId]
     * return:java.util.List<com.dyenigma.entity.SysUserRole>
     */
    List<SysUserRole> findByUserId(String userId);

    /**
     * 保存分配角色权限
     * param roleId 角色id
     * param checkedIds 菜单权限ID集合
     * return
     */
    boolean saveRole(String userId, String checkedIds);
}
