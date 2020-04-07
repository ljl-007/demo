package com.example.basicstemplate.controller;

import com.example.basicstemplate.untils.Resrult;
import com.example.basicstemplate.untils.ResrultUtil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Registerontroller {

    @PostMapping(value = "register")
    public Resrult register(){
        return ResrultUtil.error();
    }
}
