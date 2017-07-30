package com.dyenigma.dao;

import com.dyenigma.core.Mapper;
import com.dyenigma.entity.SysPost;

import java.util.List;

public interface SysPostMapper extends Mapper<SysPost> {
    /**
     * Description: 根据部门ID查询该部门下所有的岗位信息
     * Name:findPostByDiv
     * Author:dyenigma
     * Time:2016/4/27 8:38
     * param:[divId]
     * return:java.util.List<com.dyenigma.entity.Post>
     */
    List<SysPost> findPostByDiv(String divId);

    /**
     * Description: 设置某个记录无效
     * Name:invalidByPrimaryKey
     * Author:dyenigma
     * Time:2016/4/27 9:02
     * param:[id]
     * return:int
     */
    int invalidByPrimaryKey(String id);
}