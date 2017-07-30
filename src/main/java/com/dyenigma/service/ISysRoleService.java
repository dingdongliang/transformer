package com.dyenigma.service;

import com.dyenigma.entity.SysRole;
import com.dyenigma.util.PageUtil;

import java.util.List;


/**
 * Description:
 * author  dyenigma
 * date 2017/07/21
 */
public interface ISysRoleService extends IBaseService<SysRole> {
    /**
     * 分页查询角色信息
     * param pageUtil
     * return
     */
    List<SysRole> findAllRoleList(PageUtil pageUtil);

    /**
     * 持久化角色信息（包含新增或修改）
     * param role
     * return
     */

    boolean persistenceRole(SysRole role);

    /**
     * 先判断是否有级联关系，再删除角色信息
     * param id
     * return
     */
    boolean delRole(String id);
}
