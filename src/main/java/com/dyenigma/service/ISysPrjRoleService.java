package com.dyenigma.service;

import com.dyenigma.entity.SysPrjRole;

import java.util.List;


/**
 * Description:
 * author  dyenigma
 * date 2017/07/21
 */
public interface ISysPrjRoleService extends IBaseService<SysPrjRole> {
    List<SysPrjRole> getPrjRoleByPrjId(String prjId);
}
