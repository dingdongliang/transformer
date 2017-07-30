package com.dyenigma.dao;

import com.dyenigma.core.Mapper;
import com.dyenigma.entity.SysProject;

import java.util.List;

public interface SysProjectMapper extends Mapper<SysProject> {
    List<SysProject> getPrjByCoId(String coId);
}