package com.jiashidai.springboot_shiro_demo.entity;

import com.alibaba.druid.support.monitor.annotation.MTable;

import java.io.Serializable;

public class Demo implements Serializable {
    private Integer id;
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
