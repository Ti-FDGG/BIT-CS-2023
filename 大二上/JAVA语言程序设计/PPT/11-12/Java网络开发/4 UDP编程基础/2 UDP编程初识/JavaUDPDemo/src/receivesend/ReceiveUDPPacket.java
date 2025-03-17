package receivesend;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class ReceiveUDPPacket {
    private static final int BUFLEN = "Hello,World!".getBytes().length;

    public static void main(String[] args) throws IOException {
        try (var socket = new DatagramSocket(1024)) {
            var receiveData = new byte[BUFLEN];
            var packet = new DatagramPacket(receiveData, receiveData.length);
            socket.receive(packet);
            var message = new String(packet.getData());
            System.out.println("RECEIVED:" + message);
            System.out.println(packet.getAddress()+":"+packet.getPort());
        }
    }
}
