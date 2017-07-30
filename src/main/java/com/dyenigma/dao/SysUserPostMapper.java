package com.dyenigma.dao;

import com.dyenigma.core.Mapper;
import com.dyenigma.entity.SysUserPost;

import java.util.List;

public interface SysUserPostMapper extends Mapper<SysUserPost> {
    List<SysUserPost> findByPostId(String postId);

    List<SysUserPost> findAllByUserId(String userId);
}