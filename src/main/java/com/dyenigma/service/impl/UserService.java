package com.dyenigma.service.impl;

import com.dyenigma.dao.UserMapper;
import com.dyenigma.entity.User;
import com.dyenigma.service.IUserService;
import com.dyenigma.service.impl.BaseService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
* Description:
* author  dyenigma
* date 2017/07/28
*/
@Service
@Transactional
public class UserService extends BaseService<User> implements
IUserService {

    private final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Resource
    private UserMapper userMapper;

}
