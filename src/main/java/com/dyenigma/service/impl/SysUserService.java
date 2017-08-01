package com.dyenigma.service.impl;

import com.dyenigma.dao.SysPostMapper;
import com.dyenigma.dao.SysPrjUserMapper;
import com.dyenigma.dao.SysRoleMapper;
import com.dyenigma.dao.SysUserMapper;
import com.dyenigma.dao.SysUserPmsnMapper;
import com.dyenigma.dao.SysUserPostMapper;
import com.dyenigma.dao.SysUserRoleMapper;
import com.dyenigma.entity.BaseDomain;
import com.dyenigma.entity.SysPost;
import com.dyenigma.entity.SysPrjUser;
import com.dyenigma.entity.SysRole;
import com.dyenigma.entity.SysUser;
import com.dyenigma.entity.SysUserPmsn;
import com.dyenigma.entity.SysUserPost;
import com.dyenigma.entity.SysUserRole;
import com.dyenigma.model.TreeModel;
import com.dyenigma.service.ISysUserService;
import com.dyenigma.util.Constants;
import com.dyenigma.util.PageUtil;
import com.dyenigma.util.StringUtil;
import com.dyenigma.util.UUIDUtil;
import com.dyenigma.util.security.Md5Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Description:
 * author  dyenigma
 * date 2017/07/21
 */
@Service
@Transactional
public class SysUserService extends BaseService<SysUser> implements
        ISysUserService {

    private final Logger logger = LoggerFactory.getLogger(SysUserService.class);

    @Autowired
    private SysUserMapper userMapper;
    @Autowired
    private SysRoleMapper roleMapper;
    @Autowired
    private SysUserRoleMapper userRoleMapper;
    @Autowired
    private SysUserPmsnMapper userPmsnMapper;
    @Autowired
    private SysUserPostMapper userPostMapper;
    @Autowired
    private SysPrjUserMapper prjUserMapper;
    @Autowired
    private SysPostMapper postMapper;

    @Override
    public List<SysUser> findAll() {
        logger.debug("run the users findall");
        return mapper.selectAll();
    }

    @Override
    public SysUser getUserByName(String name) {

        return userMapper.findByName(name);
    }

    @Override
    public boolean persistenceUser(SysUser sysUser) {
        String userId = Constants.getCurrendUser().getUserId();

        if (StringUtil.isEmpty(sysUser.getUserId())) {
            BaseDomain.createLog(sysUser, userId);
            sysUser.setPassword(Md5Utils.hash(Constants.DEFAULT_PASSWORD));
            sysUser.setStatus(Constants.PERSISTENCE_STATUS);
            sysUser.setUserId(UUIDUtil.getUUID());
            mapper.insert(sysUser);

            List<SysRole> rList = roleMapper.findDefaultRole();
            for (SysRole role : rList) {
                SysUserRole userRole = new SysUserRole();
                BaseDomain.createLog(userRole, userId);
                userRole.setUrId(UUIDUtil.getUUID());
                userRole.setUserId(sysUser.getUserId());
                userRole.setRoleId(role.getRoleId());
                userRole.setStatus(Constants.PERSISTENCE_STATUS);
                userRoleMapper.insert(userRole);
            }

        } else {
            BaseDomain.editLog(sysUser, userId);
            mapper.updateByPrimaryKeySelective(sysUser);
        }
        return true;
    }

    /**
     * 分页查询用户信息
     * param pageUtil
     * return
     * param pageUtil
     */
    @Override
    public List<SysUser> allUserByPage(PageUtil pageUtil) {
        logger.info("开始查找用户信息,分页显示");
        return userMapper.findAllByPage(pageUtil);
    }

    @Override
    public boolean delUser(String userId) {
        //删除用户角色映射
        List<SysUserRole> urList = userRoleMapper.findAllByUserId(userId);
        for (SysUserRole userRole : urList) {
            mapper.deleteByPrimaryKey(userRole.getUrId());
        }

        //删除用户权限映射
        List<SysUserPmsn> upList = userPmsnMapper.findAllByUserId(userId);
        for (SysUserPmsn userPmsn : upList) {
            mapper.deleteByPrimaryKey(userPmsn.getUpmId());
        }

        //删除用户岗位映射
        List<SysUserPost> userPostList = userPostMapper.findAllByUserId(userId);
        for (SysUserPost userPost : userPostList) {
            mapper.deleteByPrimaryKey(userPost.getUpId());
        }
        //删除项目组用户映射
        List<SysPrjUser> prjUserList = prjUserMapper.findAllByUserId(userId);
        for (SysPrjUser prjUser : prjUserList) {
            mapper.deleteByPrimaryKey(prjUser.getPuId());
        }
        //删除用户

        SysUser user = mapper.selectByPrimaryKey(userId);
        user.setStatus(Constants.PERSISTENCE_DELETE_STATUS);
        return mapper.updateByPrimaryKey(user) > 0;

    }


    /**
     * Description: 获取所有可添加用户的岗位，按树状结构展示
     * stepByStep: 传递过来公司与部门树
     * 1.循环判断该树中哪个节点是部门节点
     * 2.然后查找该节点的下属岗位
     * 3.添加到节点内
     * Name:getPostList
     * Author:Dyenigma
     * Time:2016/4/27 23:24
     * param:[list]
     * return:java.util.List<com.dyenigma.model.TreeModel>
     */
    @Override
    public List<TreeModel> getPostList(List<TreeModel> list) {
        //递归处理树结构，添加岗位信息
        postToTree(list);
        return list;
    }

    //添加岗位数据到公司-部门树模型下（第一步处理方法：判断是否有子节点）
    private void postToTree(List<TreeModel> list) {
        for (TreeModel treeModel : list) {
            List<TreeModel> children = treeModel.getChildren();
            //有子节点递归，没有子节点不参与递归，在每个部门节点处理岗位信息的嫁接
            if (children == null || children.size() == 0) {
                addPost(treeModel, children);
            } else {
                List<TreeModel> childList = treeModel.getChildren();
                //内层循环，添加节点
                postToTree(childList);
                addPost(treeModel, children);
            }
        }
    }

    //添加岗位数据到公司-部门树模型下（第二步处理方法：判断是否是部门节点）
    private void addPost(TreeModel treeModel, List<TreeModel> children) {
        //判断是否是部门节点
        if ("DIV".equals(treeModel.getPid())) {
            //获取每个节点的id，即部门id
            String divId = treeModel.getId();
            //获取该部门下属的所有岗位
            List<SysPost> postList = postMapper.findPostByDiv(divId);
            //把岗位加入部门节点内
            treeModel.setChildren(addPostToDiv(postList, children));
        }
    }

    //添加岗位数据到公司-部门树模型下（第三步处理方法：把岗位加入部门节点内）
    private List<TreeModel> addPostToDiv(List<SysPost> list, List<TreeModel> childList) {
        if (childList == null) {
            childList = new ArrayList<>();
        }
        for (SysPost post : list) {
            TreeModel tm = new TreeModel();
            tm.setId(post.getPostId());
            tm.setIconCls(Constants.DEFAULT_ICON);
            tm.setPid("POST");
            tm.setText(post.getPostName());
            tm.setState(Constants.TREE_STATUS_OPEN);
            childList.add(tm);
        }
        return childList;
    }

    /**
     * Description: 按用户ID集合查询用户信息，分页，用于获取岗位、部门、公司的所有用户信息
     * Name:findUserByPage
     * Author:dyenigma
     * Time:2016/4/28 10:20
     * param:[pageUtil, idList]
     * return:java.util.List<com.dyenigma.entity.User>
     */
    @Override
    public List<SysUser> findUserByPage(PageUtil pageUtil, Set<String> idList) {
        return userMapper.findUserByPage(pageUtil, idList);
    }
}
