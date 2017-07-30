package com.dyenigma.service.impl;

import com.dyenigma.dao.GenQuestionMapper;
import com.dyenigma.entity.GenQuestion;
import com.dyenigma.service.IGenQuestionService;
import com.dyenigma.util.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Description:
 * author  dyenigma
 * date 2017/07/21
 */
@Service
@Transactional
public class GenQuestionService extends BaseService<GenQuestion> implements
        IGenQuestionService {
    @Autowired
    private GenQuestionMapper genQuestionMapper;

    /**
     * Description: 分页查询所有信息
     * Name:findAllByPage
     * Author:dyenigma
     * Time:2016/5/13 11:10
     * param:[pageUtil]
     * return:java.util.List<com.dyenigma.entity.GenQuestion>
     *
     * param pageUtil
     */
    @Override
    public List<GenQuestion> findAllByPage(PageUtil pageUtil) {
        return genQuestionMapper.findAllByPage(pageUtil);
    }

    /**
     * Description:查找某个用户的所有问题
     * Name:findAllByUser
     * Author:dyenigma
     * Time:2016/5/13 11:04
     * param:[userId]
     * return:java.util.List<com.dyenigma.entity.GenQuestion>
     *
     * param userId
     */
    @Override
    public List<GenQuestion> findAllByUser(String userId) {
        return null;
    }

    /**
     * Description:查找某段时间内的所有问题
     * Name:findAllByTime
     * Author:dyenigma
     * Time:2016/5/13 11:04
     * param:[start, end]
     * return:java.util.List<com.dyenigma.entity.GenQuestion>
     *
     * param start
     * param end
     */
    @Override
    public List<GenQuestion> findAllByTime(String start, String end) {
        return null;
    }

    /**
     * Description:按关键字列查询问题
     * Name:findAllByKey
     * Author:dyenigma
     * Time:2016/5/13 11:04
     * param:[key]
     * return:java.util.List<com.dyenigma.entity.GenQuestion>
     *
     * param key
     */
    @Override
    public List<GenQuestion> findAllByKey(String key) {
        return null;
    }

    /**
     * Description:模糊查询
     * Name:findAllByWord
     * Author:dyenigma
     * Time:2016/5/13 11:05
     * param:[word]
     * return:java.util.List<com.dyenigma.entity.GenQuestion>
     *
     * param word
     */
    @Override
    public List<GenQuestion> findAllByWord(String word) {
        return null;
    }
}
