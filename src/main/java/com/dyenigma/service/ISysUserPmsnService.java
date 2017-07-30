package com.dyenigma.service;

import com.dyenigma.entity.SysUserPmsn;

import java.util.List;


/**
* Description:
* author  dyenigma
* date 2017/07/21
*/
public interface ISysUserPmsnService extends IBaseService<SysUserPmsn> {
    /**
     * 保存分配用户权限
     * param userId 用户id
     * param checkedIds 岗位ID集合
     * return
     */
    boolean saveUserPmsn(String userId, String checkedIds);


    /**
     * Description:  获取用户的所有直接权限映射信息
     * Name:findByUserId
     * Author:Dyenigma
     * Time:2016/4/28 22:15
     * param:[userId]
     * return:java.util.List<com.dyenigma.entity.SysUserPmsn>
     */
    List<SysUserPmsn> findByUserId(String userId);
}
