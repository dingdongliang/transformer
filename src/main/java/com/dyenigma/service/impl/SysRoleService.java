package com.dyenigma.service.impl;

import com.dyenigma.dao.SysPermissionMapper;
import com.dyenigma.dao.SysPostRoleMapper;
import com.dyenigma.dao.SysPrjRoleMapper;
import com.dyenigma.dao.SysRoleMapper;
import com.dyenigma.dao.SysRolePmsnMapper;
import com.dyenigma.dao.SysUserRoleMapper;
import com.dyenigma.entity.BaseDomain;
import com.dyenigma.entity.SysPermission;
import com.dyenigma.entity.SysPostRole;
import com.dyenigma.entity.SysPrjRole;
import com.dyenigma.entity.SysRole;
import com.dyenigma.entity.SysRolePmsn;
import com.dyenigma.entity.SysUserRole;
import com.dyenigma.service.ISysRoleService;
import com.dyenigma.util.Constants;
import com.dyenigma.util.PageUtil;
import com.dyenigma.util.StringUtil;
import com.dyenigma.util.UUIDUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;


/**
 * Description:
 * author  dyenigma
 * date 2017/07/21
 */
@Service
@Transactional
public class SysRoleService extends BaseService<SysRole> implements
        ISysRoleService {

    private final Logger logger = LoggerFactory.getLogger(SysRoleService.class);

    @Autowired
    private SysRoleMapper sysRoleMapper;
    @Autowired
    private SysPermissionMapper sysPermissionMapper;
    @Autowired
    private SysRolePmsnMapper sysRolePmsnMapper;
    @Autowired
    private SysPostRoleMapper sysPostRoleMapper;
    @Autowired
    private SysPrjRoleMapper sysPrjRoleMapper;
    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;

    @Override
    public List<SysRole> findAllRoleList(PageUtil pageUtil) {
        logger.info("开始查找用户信息,分页显示");
        List<SysRole> roleList = sysRoleMapper.findAllByPage(pageUtil);
        return roleList;
    }

    @Override
    public Integer getCount(Map<String, Object> paramMap) {
        logger.info("开始查找用户信息的总条数");
        return sysRoleMapper.selectCountByCondition(paramMap);
    }

    /**
     * 新增和修改角色
     * param role
     * return
     */
    @Override
    public boolean persistenceRole(SysRole role) {
        String userId = Constants.getCurrendUser().getUserId();

        if (StringUtil.isEmpty(role.getRoleId())) {
            BaseDomain.createLog(role, userId);
            role.setStatus(Constants.PERSISTENCE_STATUS);
            role.setRoleId(UUIDUtil.getUUID());
            sysRoleMapper.insert(role);

            // 这里设置新增用户的默认权限,首先获取所有的默认且有效的权限
            List<SysPermission> pList = sysPermissionMapper.findAllDefault();
            //然后逐一添加进映射表
            for (SysPermission permission : pList) {
                SysRolePmsn rolePmsn;
                rolePmsn = new SysRolePmsn();
                rolePmsn.setStatus(Constants.PERSISTENCE_STATUS);
                BaseDomain.createLog(rolePmsn, userId);
                rolePmsn.setPmsnId(permission.getPmsnId());
                rolePmsn.setRoleId(role.getRoleId());
                rolePmsn.setRpId(UUIDUtil.getUUID());
                sysRolePmsnMapper.insert(rolePmsn);
            }
        } else {
            BaseDomain.editLog(role, userId);
            sysRoleMapper.updateByPrimaryKeySelective(role);
        }
        return true;
    }

    @Override
    public boolean delRole(String id) {

        //删除角色权限关联
        List<SysRolePmsn> rpList = sysRolePmsnMapper.findAllByRoleId(id);
        for (SysRolePmsn rolePmsn : rpList) {
            sysRoleMapper.deleteByPrimaryKey(rolePmsn.getRpId());
        }

        //删除角色岗位关联
        List<SysPostRole> prList = sysPostRoleMapper.findAllByRoleId(id);
        for (SysPostRole postRole : prList) {
            sysRoleMapper.deleteByPrimaryKey(postRole.getPrId());
        }

        //删除角色用户关联
        List<SysUserRole> urList = sysUserRoleMapper.findAllByRoleId(id);
        for (SysUserRole userRole : urList) {
            sysRoleMapper.deleteByPrimaryKey(userRole.getUrId());
        }

        //删除项目角色关联
        List<SysPrjRole> prjRoles = sysPrjRoleMapper.findAllByRoleId(id);
        for (SysPrjRole prjRole : prjRoles) {
            sysRoleMapper.deleteByPrimaryKey(prjRole.getPrId());
        }

        //删除角色
        SysRole role = sysRoleMapper.selectByPrimaryKey(id);
        role.setStatus(Constants.PERSISTENCE_DELETE_STATUS);
        return sysRoleMapper.updateByPrimaryKey(role) > 0;
    }

}
