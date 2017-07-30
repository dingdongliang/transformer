package com.dyenigma.service.impl;

import com.dyenigma.dao.SysPrjRoleMapper;
import com.dyenigma.entity.SysPrjRole;
import com.dyenigma.service.ISysPrjRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * Description:
 * author  dyenigma
 * date 2017/07/21
 */
@Service
@Transactional
public class SysPrjRoleService extends BaseService<SysPrjRole> implements
        ISysPrjRoleService {

    @Autowired
    private SysPrjRoleMapper sysPrjRoleMapper;

    @Override
    public List<SysPrjRole> getPrjRoleByPrjId(String prjId) {
        return sysPrjRoleMapper.findAllByPrjId(prjId);
    }

}
