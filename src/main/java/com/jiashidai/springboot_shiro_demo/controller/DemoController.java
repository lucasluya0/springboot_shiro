package com.jiashidai.springboot_shiro_demo.controller;

import com.jiashidai.springboot_shiro_demo.entity.Demo;
import com.jiashidai.springboot_shiro_demo.services.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DemoController {

    @Autowired
    private DemoService demoService;

    @RequestMapping("/test")
    String home() {
        List<Demo> demos = demoService.selectAll();
        for (Demo demo : demos){
            System.out.println(demo.getName());
        }
        return "Hello World!";
    }
    @RequestMapping("/test2")
    String home2() {
        List<Demo> demos = demoService.selectAll();
        for (Demo demo : demos){
            System.out.println(demo.getName());
        }
        return "Hello World !!!!!!!!!!!!!!!!!!!!!!!!!!!!!";
    }
}
