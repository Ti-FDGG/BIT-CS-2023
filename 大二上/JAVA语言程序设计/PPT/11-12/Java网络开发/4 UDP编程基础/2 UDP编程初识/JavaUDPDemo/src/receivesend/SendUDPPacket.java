package receivesend;

import java.io.IOException;
import java.net.*;

public class SendUDPPacket {
    private static final String MESSAGE = "Hello,World!";

    public static void main(String[] args) throws IOException {

        byte[] buffer = MESSAGE.getBytes();
        var address = InetAddress.getLocalHost();
        //接收方地址：localhost，接收方端口：1024
        var packet = new DatagramPacket(buffer, buffer.length, address, 1024);
        try (var datagramSocket = new DatagramSocket()) {
            datagramSocket.send(packet);
            System.out.println("SEND: " + MESSAGE);
            //输出本机地址以及监听的端口
            System.out.println(datagramSocket.getLocalAddress()+":"+datagramSocket.getLocalPort());

        }
    }
}
