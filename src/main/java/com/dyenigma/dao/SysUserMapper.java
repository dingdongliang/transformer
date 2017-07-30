package com.dyenigma.dao;

import com.dyenigma.core.Mapper;
import com.dyenigma.entity.SysUser;
import com.dyenigma.util.PageUtil;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

public interface SysUserMapper extends Mapper<SysUser> {

    /**
     * Description: 好名字自己会解释
     * Name:findByName
     * Author:Dyenigma
     * Time:2016/4/23 10:49
     * param:[name]
     * return:com.dyenigma.entity.SysUser
     */
    SysUser findByName(String name);

    /**
     * Description: 好名字自己会解释
     * Name:findAllByPage
     * Author:Dyenigma
     * Time:2016/4/23 10:49
     * param:[pageUtil]
     * return:java.util.List<com.dyenigma.entity.SysUser>
     */
    List<SysUser> findAllByPage(PageUtil pageUtil);

    /**
     * Description: 按用户ID集合查询用户信息，分页，用于获取岗位、部门、公司的所有用户信息
     * Name:findUserByPage
     * Author:dyenigma
     * Time:2016/4/28 10:20
     * param:[pageUtil, idList]
     * return:java.util.List<com.dyenigma.entity.SysUser>
     */
    List<SysUser> findUserByPage(@Param("pageUtil") PageUtil pageUtil, @Param("idSet") Set<String> idSet);


    /**
     * Description: 按用户ID集合查询用户信息，分页，用于获取岗位、部门、公司的所有用户信息
     * Name:findUserByPage
     * Author:dyenigma
     * Time:2016/4/28 10:20
     * return:java.util.List<com.dyenigma.entity.SysUser>
     */
    List<SysUser> findUserByList(@Param("idSet") Set<String> idSet);
}