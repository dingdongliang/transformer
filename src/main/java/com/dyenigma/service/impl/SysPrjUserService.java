package com.dyenigma.service.impl;

import com.dyenigma.dao.SysPrjUserMapper;
import com.dyenigma.dao.SysUserMapper;
import com.dyenigma.entity.SysPrjUser;
import com.dyenigma.entity.SysUser;
import com.dyenigma.service.ISysPrjUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


/**
 * Description:
 * author  dyenigma
 * date 2017/07/21
 */
@Service
@Transactional
public class SysPrjUserService extends BaseService<SysPrjUser> implements
        ISysPrjUserService {
    @Autowired
    private SysPrjUserMapper sysPrjUserMapper;
    @Autowired
    private SysUserMapper sysUserMapper;

    @Override
    public List<SysUser> getPrjUserByPrjId(String prjId) {
        List<SysPrjUser> puList = sysPrjUserMapper.getPrjUserByPrjId(prjId);
        Set<String> idList = puList.stream().map(SysPrjUser::getUserId).collect(Collectors.toSet());
        if (idList.size() > 0) {
            return sysUserMapper.findUserByList(idList);
        } else {
            return null;
        }
    }
}
