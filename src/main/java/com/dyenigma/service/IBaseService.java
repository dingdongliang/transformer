package com.dyenigma.service;

import org.apache.ibatis.exceptions.TooManyResultsException;
import tk.mybatis.mapper.entity.Condition;

import java.util.List;
import java.util.Map;

/**
 * Description:
 * author  dyenigma
 * date 2017/7/14 11:56
 */
public interface IBaseService<T> {

    void save(T model);


    void save(List<T> models);


    void deleteById(Integer id);

    /**
     * 批量刪除
     * param ids -> “1,2,3,4”
     */
    void deleteByIds(String ids);

    void update(T model);

    T findById(Integer id);

    /**
     * 通过Model中某个成员变量名称（非数据表中column的名称）查找
     * param fieldName
     * param value需符合unique约束
     * return
     * throws TooManyResultsException
     */
    T findBy(String fieldName, Object value) throws TooManyResultsException;

    /**
     * 通过多个ID查找符合条件的集合
     * param ids -> “1,2,3,4”
     * return
     */
    List<T> findByIds(String ids);

    /**
     * 根据条件查找
     * param condition查询条件
     * return
     */
    List<T> findByCondition(Condition condition);

    List<T> findAll();

    /**
     * Description: 获取某个查询的结果条数
     * Name:getCount
     * Author:dyenigma
     * Time:2016/4/22 11:57
     * param:[paramMap]
     * return:Integer
     */
    Integer getCount(Map<String, Object> paramMap);


}
