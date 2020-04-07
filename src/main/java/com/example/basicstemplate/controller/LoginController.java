package com.example.basicstemplate.controller;

import com.example.basicstemplate.entity.UserLogin;
import com.example.basicstemplate.untils.Resrult;
import com.example.basicstemplate.untils.ResrultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@Api(value = "登陆信息", tags = "登录接口")
public class LoginController {
    @PostMapping(value = "login")
    @ApiOperation(value = "登陆信息", notes = "用户名和密码")
    @ResponseBody
    public Resrult login(@RequestBody UserLogin userLogin) {
        System.out.println("用户名==" + userLogin.getUsername() + "密码==" + userLogin.getPassword() + "验证码==" + userLogin.getCode() + "记住我" + userLogin.getRemember());
        if (userLogin.getUsername().equals("admin") && userLogin.getPassword().equals("admin")) {
            return ResrultUtil.success();
        }else {
            return ResrultUtil.error();
        }
    }
}
