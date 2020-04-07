package com.example.basicstemplate.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class test {
    @GetMapping(value = "test")

    @ResponseBody
    public String test(){
        return "这是测试解救";
    }

}
