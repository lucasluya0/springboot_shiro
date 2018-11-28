package com.jiashidai.springboot_shiro_demo.entity;

public class UserRoleKey {
    private Long userId;

    private Integer roleId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }
}