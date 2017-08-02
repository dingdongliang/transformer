package com.dyenigma.service.impl;

import com.dyenigma.dao.GenQaLogMapper;
import com.dyenigma.entity.GenQaLog;
import com.dyenigma.service.IGenQaLogService;
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
public class GenQaLogService extends BaseService<GenQaLog> implements
        IGenQaLogService {
    @Resource
    private GenQaLogMapper genQaLogMapper;

}
