package com.jiashidai.springboot_shiro_demo.mappers;

import com.jiashidai.springboot_shiro_demo.entity.Demo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
public interface DemoMapper {
    public List<Demo> selectAll();
}
