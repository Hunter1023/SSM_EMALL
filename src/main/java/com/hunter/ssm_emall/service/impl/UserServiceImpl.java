package com.hunter.ssm_emall.service.impl;

import com.hunter.ssm_emall.bean.User;
import com.hunter.ssm_emall.dao.UserMapper;
import com.hunter.ssm_emall.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    UserMapper userMapper;

    //验证用户名和密码
    @Override
    public User getUser(String username, String password) {
        return userMapper.getUser(username, password);
    }
}
