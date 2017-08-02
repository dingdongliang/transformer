package com.dyenigma.service.impl;

import com.dyenigma.dao.SysCompanyMapper;
import com.dyenigma.dao.SysDivisionMapper;
import com.dyenigma.dao.SysPostMapper;
import com.dyenigma.dao.SysPostRoleMapper;
import com.dyenigma.dao.SysUserPostMapper;
import com.dyenigma.entity.BaseDomain;
import com.dyenigma.entity.SysCompany;
import com.dyenigma.entity.SysDivision;
import com.dyenigma.entity.SysPost;
import com.dyenigma.entity.SysPostRole;
import com.dyenigma.entity.SysUserPost;
import com.dyenigma.model.TreeModel;
import com.dyenigma.service.ISysPostService;
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
public class SysPostService extends BaseService<SysPost> implements
        ISysPostService {
    @Autowired
    private SysPostMapper sysPostMapper;
    @Autowired
    private SysCompanyMapper sysCompanyMapper;
    @Autowired
    private SysDivisionMapper sysDivisionMapper;
    @Autowired
    private SysUserPostMapper sysUserPostMapper;
    @Autowired
    private SysPostRoleMapper sysPostRoleMapper;

    /**
     * Description: 根据部门ID查询该部门下所有的岗位信息
     * Name:finaPostByDiv
     * Author:dyenigma
     * Time:2016/4/27 8:36
     * param:[id]
     * return:java.util.List<com.dyenigma.entity.Post>
     */
    @Override
    public List<SysPost> finaPostByDiv(String id) {
        return sysPostMapper.findPostByDiv(id);
    }

    /**
     * Description: 新增或修改岗位信息
     * Name:persistencePost
     * Author:dyenigma
     * Time:2016/4/27 9:17
     * param:[post]
     * return:java.lang.Boolean
     */
    @Override
    public Boolean persistencePost(SysPost post) {
        String userId = Constants.getCurrendUser().getUserId();
        if (StringUtil.isEmpty(post.getPostId())) {
            BaseDomain.createLog(post, userId);
            post.setStatus(Constants.PERSISTENCE_STATUS);
            post.setPostId(UUIDUtil.getUUID());
            sysPostMapper.insert(post);
        } else {
            BaseDomain.editLog(post, userId);
            sysPostMapper.updateByPrimaryKeySelective(post);
        }
        return true;
    }

    /**
     * Description: 获取所有可添加岗位的公司和部门
     * Name:getCoDivList
     * Author:dyenigma
     * Time:2016/4/27 10:02
     * param:[]
     * return:java.util.List<com.dyenigma.model.TreeModel>
     */
    @Override
    public List<TreeModel> getCoDivList() {
        //获取所有的公司信息，并用treeModel格式化
        List<SysCompany> coList = sysCompanyMapper.selectAll();
        List<TreeModel> coTrees = coToTree("0", coList);
        childToTree(coTrees);
        return coTrees;
    }

    //添加部门数据到公司树模型下
    private void childToTree(List<TreeModel> list) {
        for (TreeModel treeModel : list) {
            List<TreeModel> coChild = treeModel.getChildren();
            if (coChild.size() == 0) {
                //获取每个节点的id，即公司id
                String coId = treeModel.getId();
                //获取该公司下属的所有部门
                List<SysDivision> divList = sysDivisionMapper.findByCompId(coId);
                //把部门加入公司节点内
                addDivToCo(divList, coChild);
            } else {
                List<TreeModel> childList = treeModel.getChildren();
                //内层循环，添加节点
                childToTree(childList);
                //获取每个节点的id，即公司id
                String coId = treeModel.getId();
                //获取该公司下属的所有部门
                List<SysDivision> divList = sysDivisionMapper.findByCompId(coId);
                //把部门加入公司节点内
                addDivToCo(divList, childList);
            }
        }
    }

    //把部门加入公司节点内
    private void addDivToCo(List<SysDivision> list, List<TreeModel> childList) {
        for (SysDivision div : list) {
            TreeModel tm = new TreeModel();
            tm.setId(div.getDivId());
            tm.setIconCls(div.getIconCls());
            tm.setPid("DIV");
            tm.setText(div.getDivName());
            tm.setState(Constants.TREE_STATUS_OPEN);
            childList.add(tm);
        }
    }


    //公司递归转化成Tree模型，支持无限级节点
    private List<TreeModel> coToTree(String id, List<SysCompany> list) {
        List<TreeModel> menuList = new ArrayList<>();
        list.stream().filter(co -> id.equals(co.getPrntId())).forEach(co -> {
            TreeModel menu = new TreeModel();
            menu.setState(Constants.TREE_STATUS_OPEN); //这里必须关闭节点，否则会出现无限节点
            menu.setId(co.getCoId());
            menu.setPid("0".equals(co.getPrntId()) ? "" : co.getPrntId());
            menu.setIconCls(co.getIconCls());
            menu.setText(co.getCoName());
            menu.setChildren(coToTree(co.getCoId(), list));
            menuList.add(menu);
        });
        return menuList;
    }

    @Override
    public boolean delPostById(String postId) {
        //先检查岗位下是否有用户，判断能否删除岗位
        List<SysUserPost> upList = sysUserPostMapper.findByPostId(postId);
        if (upList.size() > 0) {
            return false;
        } else {
            //没有用户的岗位可以删除，首先删除岗位角色的对应关系，然后删除岗位
            List<SysPostRole> prList = sysPostRoleMapper.findAllByPostId(postId);
            for (SysPostRole postRole : prList) {
                sysPostMapper.deleteByPrimaryKey(postRole.getPrId());
            }
            SysPost post = sysPostMapper.selectByPrimaryKey(postId);
            post.setStatus(Constants.PERSISTENCE_DELETE_STATUS);
            return sysPostMapper.updateByPrimaryKey(post) > 0;
        }
    }

    /**
     * Description: 设置某个记录无效
     * Name:invalidByPrimaryKey
     * Author:dyenigma
     * Time:2016/4/27 9:02
     * param:[id]
     * return:int
     *
     * @param id
     */
    @Override
    public int invalidByPrimaryKey(String id) {
        return sysPostMapper.invalidByPrimaryKey(id);
    }
}
