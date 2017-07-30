package com.dyenigma.service;

import com.dyenigma.entity.SysProject;
import com.dyenigma.entity.SysUser;

import java.util.List;


/**
* Description:
* author  dyenigma
* date 2017/07/21
*/
public interface ISysProjectService extends IBaseService<SysProject> {
    /**
     * Description:查询公司所属项目组信息
     * Name:getPrjByCoId
     * Author:dyenigma
     * Time:2016/4/29 16:02
     * param:[coId]
     * return:java.util.List<com.dyenigma.entity.SysProject>
     */
    List<SysProject> getPrjByCoId(String coId);

    /**
     * Description: 实例化项目组信息
     * Name:persistencePrj
     * Author:dyenigma
     * Time:2016/4/29 16:02
     * param:[prj]
     * return:boolean
     */
    boolean persistencePrj(SysProject prj);

    /**
     * Description: 删除项目组
     * Name:delPrj
     * Author:dyenigma
     * Time:2016/4/29 16:04
     * param:[prjId]
     * return:boolean
     */
    boolean delPrj(String prjId);


    /**
     * Description: 保存分配给项目组的角色
     * Name:savePrjRole
     * Author:dyenigma
     * Time:2016/4/29 16:17
     * param:[prjId, checkedIds]
     * return:boolean
     */
    boolean savePrjRole(String prjId, String checkedIds);

    /**
     * Description: 保存分配给项目组的成员
     * Name:savePrjRole
     * Author:dyenigma
     * Time:2016/4/29 16:17
     * param:[prjId, checkedIds]
     * return:boolean
     */
    boolean savePrjUsers(String prjId, String checkedIds);


    /**
     * Description: 查找项目组成员
     * Name:searchPrjUser
     * Author:dyenigma
     * Time:2016/4/29 16:23
     * param:[prjId]
     * return:java.util.List<com.dyenigma.entity.SysUser>
     */
    List<SysUser> searchPrjUser(String prjId);
}
