package com.dyenigma.service;

import com.dyenigma.entity.SysDivision;
import com.dyenigma.model.TreeModel;

import java.util.List;


/**
 * Description:
 * author  dyenigma
 * date 2017/07/21
 */
public interface ISysDivisionService extends IBaseService<SysDivision> {
    /**
     * Title: deleteById
     * Description: 删除某个节点组织(更新状态为I)
     * param   id
     * param return 参数
     * return boolean 返回类型
     * throws
     */
    boolean deleteById(String id);

    /**
     * Title: findSuperOrgan
     * Description: 获取所有可添加子项的组织
     * param return 参数
     * return List<TreeModel> 返回类型
     * throws
     */
    List<TreeModel> findSuperOrgan(String id);

    /**
     * Title: persistenceOrgan
     * Description:持久化处理组织
     * param   permission
     * param return 参数
     * return boolean 返回类型
     * throws
     */
    boolean persistenceOrgan(SysDivision organ);

    /**
     * Description: 根据公司ID查询部门信息
     * Name:findDivByCoId
     * Author:dyenigma
     * Time:2016/4/26 14:30
     * param:[id]
     * return:java.util.List<com.dyenigma.entity.SysDivision>
     */
    List<SysDivision> findDivByCoId(String id);
}
