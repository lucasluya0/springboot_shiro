package com.jiashidai.springboot_shiro_demo.services;

import com.jiashidai.springboot_shiro_demo.entity.Authority;

import java.util.List;

public interface AuthorityService {
    /**
     * 得到所用权限
     * @return
     */
    List<Authority> findAllPermission();

    /**
     * 根据user_id 得到相关的授权
     * @param userId
     * @return
     */
    List<Authority> getByUserId(Long userId);
}
