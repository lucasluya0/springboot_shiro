package com.jiashidai.springboot_shiro_demo.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {
    /*用户登录*/
    @RequestMapping("/acount/login")
//    @ResponseBody
    public String signin(@RequestParam("userName") String userName, @RequestParam("pwd")String pwd){
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(userName, pwd);
//        token.setRememberMe(rememberMe);
        //http://localhost:8080/acount/login?userName=hhsykx&pwd=123
        try {
            subject.login(token);
        } catch (AuthenticationException e) {
            e.printStackTrace();
//            rediect.addFlashAttribute("errorText", "您的账号或密码输入错误!");
//            return "{\"Msg\":\"您的账号或密码输入错误\",\"state\":\"failed\"}";
        }
//        return "{\"Msg\":\"登陆成功\",\"state\":\"success\"}";
        return "home";
    }
    @RequestMapping("/acount/loginout")
    @ResponseBody
    public String signout(){
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "{\"Msg\":\"退出成功\",\"state\":\"success\"}";
    }
}
