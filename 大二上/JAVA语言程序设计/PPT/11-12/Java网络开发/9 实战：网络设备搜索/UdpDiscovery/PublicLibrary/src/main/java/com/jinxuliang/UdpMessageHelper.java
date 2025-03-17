package com.jinxuliang;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.nio.charset.StandardCharsets;

//封装UDP收发信息的一些公用代码
public class UdpMessageHelper {

    //将指定的消息发送给指定IP和端口的目标计算机
    //最后一个参数可选，不为空时，将在控制台窗口中显示它
    public static void sendMessageTo(String message, String destinationIP,
                                     int destinationPort,String statusInfo)
            throws IOException {
        byte[] messageData = message.getBytes(StandardCharsets.UTF_8);
        //目标地址是必须设置的，否则，会抛出异常：IllegalArgumentException
        var address = InetAddress.getByName(destinationIP);
        var sendPacket = new DatagramPacket(messageData, messageData.length, address,
                destinationPort);
        try (var datagramSocket = new DatagramSocket()) {
            datagramSocket.send(sendPacket);
            if(!statusInfo.isEmpty()){
                System.out.println(statusInfo);
            }
        }
    }

    //从DatagramSocket中接收信息，以字符串方式返回收到的信息
    //第2个参数为true时，在控制台显示相关信息
    public static String  receiveMessage(DatagramSocket ds,boolean showInfo) throws IOException {
            var dataBuffer = new byte[1024];
            var receivePack = new DatagramPacket(dataBuffer, dataBuffer.length);
            //在此阻塞等待接收
            ds.receive(receivePack);
            String ip = receivePack.getAddress().getHostAddress();
            int port = receivePack.getPort();// 发送者的端口
            int dataLen = receivePack.getLength(); //获取接收到的数据长度
            //转换为字符串
            String message = new String(receivePack.getData(), 0, dataLen);
            if(showInfo){
                System.out.println("\n收到外部发来的信息：" + message);
                System.out.print("外部数据包的");
                System.out.println("ip:" + ip + "\tport:" + port);
            }
            return message;

    }
}
