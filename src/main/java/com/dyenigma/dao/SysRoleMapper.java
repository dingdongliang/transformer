package com.dyenigma.dao;

import com.dyenigma.core.Mapper;
import com.dyenigma.entity.SysRole;
import com.dyenigma.util.PageUtil;

import java.util.List;

public interface SysRoleMapper extends Mapper<SysRole> {
    /**
     * Description: 分页获取角色信息
     * Name:findAllByPage
     * Author:Dyenigma
     * Time:2016/4/23 10:47
     * param:[pageUtil]
     * return:java.util.List<com.dyenigma.entity.SysRole>
     */
    List<SysRole> findAllByPage(PageUtil pageUtil);

    List<SysRole> findDefaultRole();
}