package sendcallback;

import java.io.IOException;
import java.net.*;
import java.nio.charset.StandardCharsets;

public class UDPSender {
    public static void main(String[] args)
            throws IOException {
        sendMessage("这是一个测试用字符串");
        System.out.println("等待回信...");
        waitCallback();
    }

    private static void sendMessage(String message) throws IOException {
        byte[] messageData = message.getBytes(StandardCharsets.UTF_8);
        //目标地址是必须设置的，否则，会抛出异常：IllegalArgumentException
        var address = InetAddress.getLocalHost();
        //也可以设置为广播地址 address=InetAddress.getByName("255.255.255.255");
        var sendPacket = new DatagramPacket(messageData, messageData.length, address,
                UDPConstants.RECEIVER_LISTEN_PORT);
        try (var datagramSocket = new DatagramSocket()) {
            datagramSocket.send(sendPacket);
            System.out.println("消息己经发送到目标机器的端口：" + UDPConstants.RECEIVER_LISTEN_PORT);
        }
    }

    private static void waitCallback() throws IOException {
        try (var ds = new DatagramSocket(UDPConstants.SENDER_LISTEN_PORT)) {
            var buf = new byte[1024];
            DatagramPacket receivePack = new DatagramPacket(buf, buf.length);
            // 接收
            ds.receive(receivePack);
            String ip = receivePack.getAddress().getHostAddress();
            int port = receivePack.getPort();
            int dataLen = receivePack.getLength();
            String callback = new String(receivePack.getData(), 0, dataLen);
            System.out.println("收到回执：" + callback);
            System.out.print("回信方信息：");
            System.out.println("ip:" + ip + "\tport:" + port);
        }
    }
}
