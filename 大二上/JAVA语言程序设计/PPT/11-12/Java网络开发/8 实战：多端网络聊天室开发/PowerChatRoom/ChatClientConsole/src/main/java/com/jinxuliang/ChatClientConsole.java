package com.jinxuliang;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.UUID;

public class ChatClientConsole {

    public static void main(String[] args) throws IOException {
        // 等待用户输入消息
        BufferedReader consoleReader =
                new BufferedReader(new InputStreamReader(System.in));
        System.out.print("请输入您的名字(直接回车采用系统生成的名字）:");
        String userName = consoleReader.readLine();
        if (userName.isEmpty()) {
            userName = UUID.randomUUID().toString();
        }
        System.out.print("请输入服务器的IP地址:");
        String serverIP = consoleReader.readLine();
        System.out.println("本用户名:" + userName);
        ChatClient chatClient = new ChatClient(serverIP, userName);
        chatClient.start();
    }
}