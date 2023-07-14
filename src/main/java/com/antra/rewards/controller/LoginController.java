package com.antra.rewards.controller;

import com.antra.rewards.service.LoginService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

@Controller
public class LoginController {

    @Autowired
    LoginService loginService;

    @ResponseBody
    @PostMapping("/login")
    public String login(@RequestParam("userName") String userName, @RequestParam("password") String password) {
        Subject subject = SecurityUtils.getSubject();

        // password crypto type , use md5 as demo (can use aes for advanced)
        String md5Password = DigestUtils.md5DigestAsHex(password.getBytes());

        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(userName, md5Password);
        try {
            subject.login(usernamePasswordToken);
        } catch (AuthenticationException e) {
            e.printStackTrace();
            return "account password error！";
        } catch (AuthorizationException e) {
            e.printStackTrace();
            return "no permissions";
        }
        return "login success";
    }
}
