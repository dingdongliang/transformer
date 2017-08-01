package com.dyenigma.service.impl;

import com.dyenigma.dao.SysCompanyMapper;
import com.dyenigma.dao.SysDivisionMapper;
import com.dyenigma.dao.SysProjectMapper;
import com.dyenigma.entity.BaseDomain;
import com.dyenigma.entity.SysCompany;
import com.dyenigma.entity.SysDivision;
import com.dyenigma.entity.SysProject;
import com.dyenigma.model.TreeModel;
import com.dyenigma.service.ISysCompanyService;
import com.dyenigma.util.Constants;
import com.dyenigma.util.PageUtil;
import com.dyenigma.util.StringUtil;
import com.dyenigma.util.UUIDUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


/**
 * Description:
 * author  dyenigma
 * date 2017/07/21
 */
@Service
@Transactional
public class SysCompanyService extends BaseService<SysCompany> implements
        ISysCompanyService {

    private final Logger logger = LoggerFactory.getLogger(SysCompanyService.class);
    @Autowired
    private SysCompanyMapper sysCompanyMapper;
    @Autowired
    private SysDivisionMapper divisionMapper;
    @Autowired
    private SysProjectMapper projectMapper;

    /**
     * Description: 获取所有的公司名称和ID
     * Name:getAllCoName
     * Author:dyenigma
     * Time:2016/4/26 10:19
     * param:[]
     * return:java.util.List<com.dyenigma.model.TreeModel>
     */
    @Override
    public List<TreeModel> getAllCoName() {
        List<SysCompany> coList = mapper.selectAll();
        return permToTree("0", coList);
    }


    //递归转化成菜单模型，支持无限级菜单
    private List<TreeModel> permToTree(String id, List<SysCompany> list) {
        List<TreeModel> menuList = new ArrayList<>();
        list.stream().filter(co -> id.equals(co.getPrntId())).forEach(co -> {
            TreeModel menu = new TreeModel();
            menu.setState(Constants.TREE_STATUS_OPEN); //这里必须关闭节点，否则会出现无限节点
            menu.setId(co.getCoId());
            menu.setPid("0".equals(co.getPrntId()) ? "" : co.getPrntId());
            menu.setIconCls(co.getIconCls());
            menu.setText(co.getCoName());
            menu.setChildren(permToTree(co.getCoId(), list));
            menuList.add(menu);
        });
        return menuList;
    }


    @Override
    public List<SysCompany> findComp(PageUtil pageUtil) {
        logger.info("开始查找公司信息,分页显示");
        List<SysCompany> compList = sysCompanyMapper.findAllByPage(pageUtil);
        return compList;
    }

    @Override
    public Integer getCount(Map<String, Object> paramMap) {
        logger.info("开始查找公司信息的总条数");
        return mapper.selectCountByCondition(paramMap);
    }

    @Override
    public boolean delComp(String compId) {
        List<SysDivision> divList = divisionMapper.findByCompId(compId);
        List<SysCompany> coList = sysCompanyMapper.findByPid(compId);
        List<SysProject> pList = projectMapper.getPrjByCoId(compId);
        //如果公司下面有组织信息或子公司，则不能删除
        if (divList.size() > 0 && coList.size() > 0 && pList.size() > 0) {
            return false;
        } else {
            SysCompany co = mapper.selectByPrimaryKey(compId);
            co.setStatus(Constants.PERSISTENCE_DELETE_STATUS);
            return mapper.updateByPrimaryKey(co) > 0;
        }
    }

    @Override
    public boolean persistenceComp(SysCompany company) {

        String userId = Constants.getCurrendUser().getUserId();
        if (StringUtil.isEmpty(company.getCoId())) {
            BaseDomain.createLog(company, userId);
            company.setStatus(Constants.PERSISTENCE_STATUS);
            company.setCoId(UUIDUtil.getUUID());
            company.setState(Constants.TREE_STATUS_CLOSED);
            company.setIconCls(Constants.COMPANY_ICON);
            if (StringUtil.isEmpty(company.getPrntId())) {
                company.setPrntId("0");
            }
            mapper.insert(company);
        } else {
            BaseDomain.editLog(company, userId);
            mapper.updateByPrimaryKeySelective(company);
        }
        return true;
    }

    /**
     * Description: 查询某个公司下所有的公司信息，包含下属多级公司
     * Name:AllCoById
     * Author:dyenigma
     * Time:2016/4/28 11:10
     * param:[coId]
     * return:java.util.List<com.dyenigma.entity.Company>
     */
    @Override
    public List<SysCompany> AllCoById(String coId) {
        List<SysCompany> returnList = new ArrayList<>();

        returnList.add(mapper.selectByPrimaryKey(coId));
        List<SysCompany> coList = new ArrayList<>();
        coList.add(mapper.selectByPrimaryKey(coId));
        returnList.addAll(findByPrntId(coId, coList).stream().collect(Collectors.toList()));

        return returnList;
    }

    //递归获取所有的子公司信息
    private List<SysCompany> findByPrntId(String coId, List<SysCompany> coList) {
        List<SysCompany> allList = new ArrayList<>();
        for (SysCompany company : coList) {
            List<SysCompany> resultList = sysCompanyMapper.selectByPrntId(coId);
            if (resultList.size() > 0) {
                allList.addAll(resultList.stream().collect(Collectors.toList()));
                findByPrntId(company.getCoId(), resultList);
            }
        }
        return allList;
    }


    @Override
    public List<SysCompany> findByPid(String pid) {
        List<SysCompany> pList = StringUtil.isEmpty(pid) ?
                sysCompanyMapper.findByPid("0") : sysCompanyMapper.findByPid(pid);
        pList.stream().filter(Company -> StringUtil.isEmpty(pid)).forEach(Company -> Company.setPrntId("0"));
        return pList;
    }

}
