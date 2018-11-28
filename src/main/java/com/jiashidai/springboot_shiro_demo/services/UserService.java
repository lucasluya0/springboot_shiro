package com.jiashidai.springboot_shiro_demo.services;

import com.jiashidai.springboot_shiro_demo.entity.User;

public interface UserService {
    User getByUserName(String username);
}
