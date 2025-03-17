package OneToOne;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * 从Server端接收多条消息 每条消息的格式如下： 开头4个字节表示的消息数据长度（以字节为单位）+按照UTF-8编码后的消息二进制数据
 * 当收到的开头4个字节为全0时，表示Server端消息己经发送完毕。
 *
 * @author JinXuLiang
 *
 */
public class One2OneClient {

    static String server = "127.0.0.1";
    static int port = 9999;

    public static void main(String[] args) {
        try (Socket socket = new Socket(server, port)) {
            // 反复接收消息，直到Server传回空消息（消息长度为0）
            while (true) {
                InputStream inputStream = socket.getInputStream();
                byte[] messageLengthData = new byte[4];
                int received = 0;
                // 接收消息长度信息
                while (received < 4) {
                    received += inputStream.read(messageLengthData, received, 4 - received);
                }
                int messageLength = One2OneHelper.byte4ToInt(messageLengthData, 0);
                if (messageLength == 0) {
                    System.out.println("数据接收结束");
                    break;
                }
                // 接收消息数据
                byte[] messageData = new byte[messageLength];
                received = 0;
                while (received < messageLength) {
                    received += inputStream.read(messageData, received, messageLength - received);
                }
                // 按照UTF-8格式解码收到的消息
                String message = One2OneHelper.getStringFromUTF8ByteArray(messageData);
                System.out.println(message);
            }
            System.out.println("客户端退出");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

