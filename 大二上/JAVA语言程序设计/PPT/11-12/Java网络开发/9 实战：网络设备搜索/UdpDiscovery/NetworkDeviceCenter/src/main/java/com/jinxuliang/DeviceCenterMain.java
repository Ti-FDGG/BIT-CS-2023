package com.jinxuliang;

import com.google.gson.Gson;
import com.jinxuliang.model.BroadcastInfo;
import com.jinxuliang.model.UDPConstants;

import java.io.IOException;
import java.net.*;
import java.util.Scanner;

public class DeviceCenterMain {
    public static void main(String[] args) throws IOException {
        System.out.println("网络设备控制中心");
        var address = InetAddress.getLocalHost();
        var deviceCenterInfo = BroadcastInfo.builder()
                .deviceCenterIp(address.getHostAddress())
                .deviceCenterListenPort(UDPConstants.MANAGER_LISTEN_PORT);
        var json = new Gson().toJson(deviceCenterInfo);
        Thread thread = new Thread(() -> {
            try {
                receiveMessage();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        });
        thread.start();

        var scanner = new Scanner(System.in);
        System.out.println("敲回车发送广播消息");
        var inputString = scanner.nextLine();
        while (!inputString.equalsIgnoreCase("quit")) {
            sendMessage(json);
            System.out.println("广播消息已经发送，敲回车键继续发送，quit退出");
            inputString = scanner.nextLine();
        }
        System.exit(0);
    }

    //发送信息
    private static void sendMessage(String message) throws IOException {
        //设置为广播地址
        UdpMessageHelper.sendMessageTo(message,
                "255.255.255.255",
                UDPConstants.DEVICE_LISTEN_PORT,
                "消息己经发送到目标机器的端口：" + UDPConstants.DEVICE_LISTEN_PORT);
    }

    //接收信息
    private static void receiveMessage() throws IOException {
        try (var ds = new DatagramSocket(UDPConstants.MANAGER_LISTEN_PORT)) {
            while (true) {
                System.out.println("\n正在等待设备发回响应消息");
                UdpMessageHelper.receiveMessage(ds, true);
            }
        }
    }
}