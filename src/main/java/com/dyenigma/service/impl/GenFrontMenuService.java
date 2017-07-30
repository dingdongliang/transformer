package com.dyenigma.service.impl;

import com.dyenigma.dao.GenFrontMenuMapper;
import com.dyenigma.entity.GenFrontMenu;
import com.dyenigma.service.IGenFrontMenuService;
import com.dyenigma.service.impl.BaseService;
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
public class GenFrontMenuService extends BaseService<GenFrontMenu> implements
IGenFrontMenuService {
    @Resource
    private GenFrontMenuMapper genFrontMenuMapper;

}
