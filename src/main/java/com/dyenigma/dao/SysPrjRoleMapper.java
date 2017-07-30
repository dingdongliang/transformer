package com.dyenigma.dao;

import com.dyenigma.core.Mapper;
import com.dyenigma.entity.SysPrjRole;

import java.util.List;

public interface SysPrjRoleMapper extends Mapper<SysPrjRole> {
    List<SysPrjRole> findAllByPrjId(String prjId);

    List<SysPrjRole> findAllByRoleId(String roleId);
}