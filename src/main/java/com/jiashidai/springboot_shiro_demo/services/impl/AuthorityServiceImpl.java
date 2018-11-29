package com.jiashidai.springboot_shiro_demo.services.impl;

import com.jiashidai.springboot_shiro_demo.entity.Authority;
import com.jiashidai.springboot_shiro_demo.mappers.AuthorityMapper;
import com.jiashidai.springboot_shiro_demo.services.AuthorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@SuppressWarnings("ALL")
@Service
public class AuthorityServiceImpl implements AuthorityService {

    @Autowired
    private AuthorityMapper authorityMapper;
    @Override
    public List<Authority> findAllPermission() {
        return authorityMapper.selectByExample(null);
    }

    @Override
    public List<Authority> getByUserId(Long userId) {
        return authorityMapper.getByUserId(userId);
    }
}
