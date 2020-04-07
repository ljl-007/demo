package com.example.basicstemplate.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserLogin implements Serializable {
    private String username;
    private String password;
    private String code;
    private String remember;

}
