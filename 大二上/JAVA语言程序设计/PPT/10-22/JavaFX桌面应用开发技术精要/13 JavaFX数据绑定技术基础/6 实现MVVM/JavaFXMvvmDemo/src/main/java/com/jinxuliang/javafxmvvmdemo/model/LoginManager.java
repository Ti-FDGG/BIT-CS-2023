package com.jinxuliang.javafxmvvmdemo.model;

//负责封装用户登录密码校验功能
public class LoginManager {
    public LoginResult login(String name, String password) {
        if (name.equals("jxl") && password.equals("123456")) {
            return LoginResult.SUCCESS;
        }
        return LoginResult.FAILURE;
    }
}
