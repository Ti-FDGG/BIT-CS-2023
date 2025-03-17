package com.jinxuliang.javafxmvvmdemo;

import com.jinxuliang.javafxmvvmdemo.model.LoginViewModel;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;

public class LoginModelTest {
    @Test
    public void testListener() {
        var login = new LoginViewModel();
        login.nameProperty().addListener(observable -> {
            System.out.println("name value changed:" + login.getName());
        });
        for (int i = 0; i < 10; i++)
            login.setName("修改时间：" + LocalTime.now());
    }
}
