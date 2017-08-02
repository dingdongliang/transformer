package com.dyenigma.service;

import com.dyenigma.entity.SysPrjUser;
import com.dyenigma.entity.SysUser;

import java.util.List;


/**
 * Description:
 * author  dyenigma
 * date 2017/07/21
 */
public interface ISysPrjUserService extends IBaseService<SysPrjUser> {
    List<SysUser> getPrjUserByPrjId(String prjId);
}
