package com.dyenigma.dao;

import com.dyenigma.core.Mapper;
import com.dyenigma.entity.SysPostRole;

import java.util.List;

public interface SysPostRoleMapper extends Mapper<SysPostRole> {
    List<SysPostRole> findAllByPostId(String postId);

    List<SysPostRole> findAllByRoleId(String roleId);
}