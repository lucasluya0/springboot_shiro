package com.jiashidai.springboot_shiro_demo.services.impl;

import com.jiashidai.springboot_shiro_demo.entity.Authority;
import com.jiashidai.springboot_shiro_demo.mappers.RoleAuthorityMapper;
import com.jiashidai.springboot_shiro_demo.services.RoleAuthorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@SuppressWarnings("ALL")
@Service
public class RoleAuthorityServiceImpl implements RoleAuthorityService {

    @Autowired
    private  RoleAuthorityMapper roleAuthorityMapper;

}
