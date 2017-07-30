package com.dyenigma.service;

import com.dyenigma.entity.SysCompany;
import com.dyenigma.model.TreeModel;
import com.dyenigma.util.PageUtil;

import java.util.List;


/**
 * Description:
 * author  dyenigma
 * date 2017/07/21
 */
public interface ISysCompanyService extends IBaseService<SysCompany> {
    /**
     * Description: 获取所有的公司名称和ID
     * Name:getAllCoName
     * Author:dyenigma
     * Time:2016/4/26 10:19
     * param:[]
     * return:java.util.List<com.dyenigma.model.DataList>
     */
    List<TreeModel> getAllCoName();

    /**
     * Description: 查找所有的公司信息
     * Name:findComp
     * Author:dyenigma
     * Time:2016/4/22 11:59
     * param:[pageUtil]
     * return:java.util.List<com.dyenigma.entity.SysCompany>
     */
    List<SysCompany> findComp(PageUtil pageUtil);


    /**
     * Description: 根据ID删除公司信息
     * Name:delComp
     * Author:dyenigma
     * Time:2016/4/22 11:59
     * param:[compId]
     * return:boolean
     */
    boolean delComp(String compId);


    /**
     * Description: 持久化公司信息，根据ID判断是insert还是update
     * Name:persistenceComp
     * Author:dyenigma
     * Time:2016/4/22 11:59
     * param:[SysCompany]
     * return:boolean
     */
    boolean persistenceComp(SysCompany company);

    /**
     * Description: 查询某个公司下所有的公司信息，包含下属多级公司
     * Name:AllCoById
     * Author:dyenigma
     * Time:2016/4/28 11:10
     * param:[coId]
     * return:java.util.List<com.dyenigma.entity.SysCompany>
     */
    List<SysCompany> AllCoById(String coId);

    List<SysCompany> findByPid(String pid);
}
