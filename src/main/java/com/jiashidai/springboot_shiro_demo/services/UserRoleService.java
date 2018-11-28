package com.jiashidai.springboot_shiro_demo.services;

import com.jiashidai.springboot_shiro_demo.entity.Role;

import java.util.List;

public interface UserRoleService {
    List<Role> getRoles(Long userId);
}
