package com.jiashidai.springboot_shiro_demo.services.impl;

import com.jiashidai.springboot_shiro_demo.entity.Demo;
import com.jiashidai.springboot_shiro_demo.mappers.DemoMapper;
import com.jiashidai.springboot_shiro_demo.services.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@SuppressWarnings("ALL")
@Service
public class DemoServiceImpl implements DemoService {

    @Autowired
    private DemoMapper demoMapper;
    @Override
    public List<Demo> selectAll() {
        return demoMapper.selectAll();
    }
}
