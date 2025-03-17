package com.jinxuliang.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//此类将等待用户在键盘输入一个字符串，然后敲回车
public class InputHelper {
    //接收用户输入
    public static String getUserInput(String prompt) throws IOException {
        var bufferReader = new BufferedReader(new InputStreamReader(System.in));
        String userInput = "";
        while (true) {
            System.out.println(prompt);
            userInput = bufferReader.readLine();
            if (userInput.isBlank() || userInput.isEmpty()) {
                System.out.println("不能发空消息!");
                continue;
            }
            //输入有效，退出循环
            return userInput;
        }
    }
}
