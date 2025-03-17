package com.jinxuliang.javafxmvvmdemo.model;

public enum LoginResult {
    SUCCESS("登录成功"), FAILURE("无效的用户名或密码");

    private final String info;

    LoginResult(String info) {
        this.info = info;
    }

    @Override
    public String toString() {
        return info;
    }
}
