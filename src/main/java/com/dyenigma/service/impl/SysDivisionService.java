package com.dyenigma.service.impl;

import com.dyenigma.dao.SysDivisionMapper;
import com.dyenigma.dao.SysPostMapper;
import com.dyenigma.entity.BaseDomain;
import com.dyenigma.entity.SysDivision;
import com.dyenigma.entity.SysPost;
import com.dyenigma.model.TreeModel;
import com.dyenigma.service.ISysDivisionService;
import com.dyenigma.util.Constants;
import com.dyenigma.util.StringUtil;
import com.dyenigma.util.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


/**
* Description:
* author  dyenigma
* date 2017/07/21
*/
@Service
@Transactional
public class SysDivisionService extends BaseService<SysDivision> implements
ISysDivisionService { 

    @Autowired
    private SysPostMapper sysPostMapper;
    @Autowired
    private SysDivisionMapper sysDivisionMapper;

    @Override
    public boolean deleteById(String id) {
        //如果部门下面有岗位，不能删除
        List<SysPost> pList = sysPostMapper.findPostByDiv(id);
        if (pList.size() > 0) {
            return false;
        } else {
            SysDivision divi = mapper.selectByPrimaryKey(id);
            divi.setStatus(Constants.PERSISTENCE_DELETE_STATUS);
            return mapper.updateByPrimaryKey(divi) > 0;
        }
    }

    @Override
    public List<TreeModel> findSuperOrgan(String id) {
        List<SysDivision> organList = sysDivisionMapper.findSuperOrgan(id);
        List<TreeModel> tList = new ArrayList<>();
        for (SysDivision divi : organList) {
            TreeModel treeModel = new TreeModel();
            treeModel.setId(divi.getDivId());
            treeModel.setPid("");
            treeModel.setText(divi.getDivName()); //注意部门管理修改为combotree形式
            treeModel.setIconCls(divi.getIconCls());
            treeModel.setState(Constants.TREE_STATUS_OPEN);
            tList.add(treeModel);
        }

        return tList;
    }

    @Override
    public boolean persistenceOrgan(SysDivision divi) {
        String userId = Constants.getCurrendUser().getUserId();

        if (StringUtil.isEmpty(divi.getDivId())) {

            BaseDomain.createLog(divi, userId);
            divi.setStatus(Constants.PERSISTENCE_STATUS);
            // 部门下级不允许有子部门
            divi.setState(Constants.TREE_STATUS_OPEN);
            divi.setDivId(UUIDUtil.getUUID());
            divi.setIconCls(Constants.DIVISION_ICON);
            mapper.insert(divi);
        } else {
            divi.setState(Constants.TREE_STATUS_OPEN);
            BaseDomain.editLog(divi, userId);
            divi.setIconCls(Constants.DIVISION_ICON);
            mapper.updateByPrimaryKeySelective(divi);
        }
        return true;
    }

    /**
     * Description: 根据公司ID查询部门信息
     * Name:findDivByCoId
     * Author:dyenigma
     * Time:2016/4/26 14:30
     * param:[id]
     * return:java.util.List<com.dyenigma.entity.Division>
     */
    @Override
    public List<SysDivision> findDivByCoId(String id) {
        return sysDivisionMapper.findByCompId(id);
    }
}
