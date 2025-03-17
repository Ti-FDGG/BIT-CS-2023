package com.jinxuliang;

import com.google.gson.Gson;
import com.jinxuliang.model.BroadcastInfo;
import com.jinxuliang.model.DeviceInfo;
import com.jinxuliang.model.UDPConstants;

import java.io.IOException;
import java.net.*;
import java.util.Scanner;
import java.util.UUID;

public class ConsoleClientMain {
    //使用UUID作为网络设置标识
    private static final String CLIENT_ID = UUID.randomUUID().toString();

    public static void main(String[] args) throws IOException {
        var discoverClientThread = new Thread(() -> {
            while (true) {
                System.out.println("\nUDP客户端:" + CLIENT_ID + "在端口" +
                        UDPConstants.DEVICE_LISTEN_PORT + "监听");
                try {
                    //接收广播消息，收到之后，回发“汇报”消息给控制中心
                    waitingDiscoveryMessage();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        discoverClientThread.start();
        System.out.println("敲回车键退出……");
        new Scanner(System.in).nextLine();
        System.exit(0);
    }

    private static void waitingDiscoveryMessage() throws IOException {
        try (var ds = new DatagramSocket(UDPConstants.DEVICE_LISTEN_PORT)) {
            //接收广播消息
            var message = UdpMessageHelper.receiveMessage(ds, true);
            //解析广播消息
            var deviceCenterInfo = new Gson().fromJson(message, BroadcastInfo.class);
            var myIP = InetAddress.getLocalHost().getHostAddress();
            //生成汇报消息
            var deviceInfo = DeviceInfo.builder().ip(myIP)
                    .info(CLIENT_ID).port(UDPConstants.DEVICE_LISTEN_PORT)
                    .build();
            Gson gson = new Gson();
            var json = gson.toJson(deviceInfo);
            //发送汇报消息
            UdpMessageHelper.sendMessageTo(json,
                    deviceCenterInfo.getDeviceCenterIp(),
                    deviceCenterInfo.getDeviceCenterListenPort(),
                    "本机信息己经发送到设备控制中心。");
        }
    }


}