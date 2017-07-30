package com.dyenigma.dao;

import com.dyenigma.core.Mapper;
import com.dyenigma.entity.SysUserRole;

import java.util.List;

public interface SysUserRoleMapper extends Mapper<SysUserRole> {
    /**
     * Description: 获取某个用户的所有角色信息
     * Name:findAllByUserId
     * Author:Dyenigma
     * Time:2016/4/23 10:48
     * param:[userId]
     * return:java.util.List<com.dyenigma.entity.UserRole>
     */
    List<SysUserRole> findAllByUserId(String userId);

    List<SysUserRole> findAllByRoleId(String roleId);
}