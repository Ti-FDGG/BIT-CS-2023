package sendcallback;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.nio.charset.StandardCharsets;

public class UDPReceiver {
    public static void main(String[] args) throws IOException {
        System.out.println("正在监听：" + UDPConstants.RECEIVER_LISTEN_PORT);
        try(var ds = new DatagramSocket(UDPConstants.RECEIVER_LISTEN_PORT)){
            var dataBuffer = new byte[1024];
            var receivePack = new DatagramPacket(dataBuffer, dataBuffer.length);
            //在此阻塞等待接收
            ds.receive(receivePack);
            // 打印接收到的信息与发送者的信息
            // 发送者的IP地址
            String ip = receivePack.getAddress().getHostAddress();
            int port = receivePack.getPort();// 发送者的端口
            int dataLen = receivePack.getLength(); //获取接收到的数据长度
            //转换为字符串
            String message = new String(receivePack.getData(), 0, dataLen);
            System.out.println("收到信息：" + message);
            System.out.print("发送方信息：");
            System.out.println("ip:" + ip + "\tport:" + port);
            System.out.println("发送回执");
            //生成回执信息
            var callbackMessage = "你的信息我己经收到，勿念！".getBytes(StandardCharsets.UTF_8);
            var sendPack = new DatagramPacket(callbackMessage, callbackMessage.length,
                    receivePack.getAddress(), UDPConstants.SENDER_LISTEN_PORT);
            ds.send(sendPack); //发送回执
            System.out.println("回执己发送...");
        }
    }
}
