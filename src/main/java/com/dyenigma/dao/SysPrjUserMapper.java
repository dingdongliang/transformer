package com.dyenigma.dao;

import com.dyenigma.core.Mapper;
import com.dyenigma.entity.SysPrjUser;

import java.util.List;

public interface SysPrjUserMapper extends Mapper<SysPrjUser> {
    List<SysPrjUser> getPrjUserByPrjId(String prjId);

    List<SysPrjUser> findAllByUserId(String userId);
}