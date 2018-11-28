package com.jiashidai.springboot_shiro_demo.services.impl;

import com.jiashidai.springboot_shiro_demo.entity.Role;
import com.jiashidai.springboot_shiro_demo.mappers.UserRoleMapper;
import com.jiashidai.springboot_shiro_demo.services.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@SuppressWarnings("ALL")
@Service
public class UserRoleServiceImpl implements UserRoleService {
    @Autowired
    private UserRoleMapper userRoleMapper;
    @Override
    public List<Role> getRoles(Long userId) {
        return userRoleMapper.getRoles(userId);
    }
}
