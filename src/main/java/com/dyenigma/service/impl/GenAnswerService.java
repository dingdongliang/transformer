package com.dyenigma.service.impl;

import com.dyenigma.dao.GenAnswerMapper;
import com.dyenigma.entity.GenAnswer;
import com.dyenigma.service.IGenAnswerService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Description:
 * author  dyenigma
 * date 2017/07/21
 */
@Service
@Transactional
public class GenAnswerService extends BaseService<GenAnswer> implements
        IGenAnswerService {
    @Resource
    private GenAnswerMapper genAnswerMapper;

}
