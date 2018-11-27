package com.jiashidai.springboot_shiro_demo.servieces;

import com.jiashidai.springboot_shiro_demo.entity.Demo;

import java.util.List;

public interface DemoService {
    public List<Demo> selectAll();
}
