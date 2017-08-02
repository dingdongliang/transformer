package com.dyenigma.service;

import com.dyenigma.entity.SysPermission;
import com.dyenigma.entity.SysRolePmsn;

import java.util.List;


/**
 * Description:
 * author  dyenigma
 * date 2017/07/21
 */
public interface ISysRolePmsnService extends IBaseService<SysRolePmsn> {
    /**
     * 好代码自己会说话
     * param roleId
     * return
     */
    List<SysPermission> findAllByRoleId(String roleId);

    /**
     * 保存分配角色权限
     * param roleId 角色id
     * param checkedIds 菜单权限ID集合
     * return
     */
    boolean savePermission(String roleId, String checkedIds);

    boolean setDefaultRole(String roleId);
}
