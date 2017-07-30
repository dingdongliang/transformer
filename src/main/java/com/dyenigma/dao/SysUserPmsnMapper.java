package com.dyenigma.dao;

import com.dyenigma.core.Mapper;
import com.dyenigma.entity.SysUserPmsn;

import java.util.List;

public interface SysUserPmsnMapper extends Mapper<SysUserPmsn> {
    List<SysUserPmsn> findAllByUserId(String userId);

    int delByPmsnId(String pmsnId);

    int delByUserId(String userId);
}