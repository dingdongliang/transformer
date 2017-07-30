package com.dyenigma.dao;

import com.dyenigma.core.Mapper;
import com.dyenigma.entity.SysCompany;
import com.dyenigma.util.PageUtil;

import java.util.List;

public interface SysCompanyMapper extends Mapper<SysCompany> {
    /**
     * Description: 分页查询公司信息
     * Name:findAllByPage
     * Author:Dyenigma
     * Time:2016/4/23 10:43
     * param:[pageUtil]
     * return:java.util.List<com.dyenigma.entity.SysCompany>
     */
    List<SysCompany> findAllByPage(PageUtil pageUtil);

    /**
     * Description:  根据父ID查询公司信息
     * Name:selectByPrntId
     * Author:dyenigma
     * Time:2016/4/28 11:13
     * param:[prntId]
     * return:java.util.List<com.dyenigma.entity.SysCompany>
     */
    List<SysCompany> selectByPrntId(String prntId);

    List<SysCompany> findByPid(String pid);
}