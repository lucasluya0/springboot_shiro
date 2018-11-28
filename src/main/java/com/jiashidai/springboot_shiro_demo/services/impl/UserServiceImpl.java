package com.jiashidai.springboot_shiro_demo.services.impl;

import com.jiashidai.springboot_shiro_demo.entity.User;
import com.jiashidai.springboot_shiro_demo.entity.UserExample;
import com.jiashidai.springboot_shiro_demo.mappers.UserMapper;
import com.jiashidai.springboot_shiro_demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Override
    public User getByUserName(String username) {
        UserExample userExample =new UserExample();
        UserExample.Criteria criteria=userExample.createCriteria();
        criteria.andLoginAccountEqualTo(username);
       List<User> users = userMapper.selectByExample(userExample);
        return users.get(0);
    }
}
