package OneToOne;

import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;


/**
 * Server端准备一段话，当客户端连接后，分多次发送给客户端
 * 消息格式：
 * 4字节的消息长度+字符串的UTF8编码字符串
 *
 * @author JinXuLiang
 */
public class One2OneServer {

    private final static int PORT = 9999;

    private static List<String> fillMessages() {
        List<String> messages = new ArrayList<>();
        messages.add("古诗一首");
        messages.add("画");
        messages.add("---------");
        messages.add("远看山有色");
        messages.add("近听水无声");
        messages.add("春去花还在");
        messages.add("人来鸟不惊");
        messages.add("---------");
        return messages;
    }

    public static void main(String[] args) {

        try (ServerSocket server = new ServerSocket(PORT)) {
            System.out.println("正在监听端口" + PORT + ",等待客户端连接……");
            while (true) {
                try (Socket clientConnection = server.accept()) {
                    System.out.println("接收到客户端连接：" + clientConnection);
                    OutputStream outputStream = clientConnection.getOutputStream();
                    List<String> messages = fillMessages(); //生成要发送的消息
                    for (String message : messages) {
                        //消息编码
                        byte[] messageData = One2OneHelper.getStringBytes(message);
                        //发送消息
                        outputStream.write(messageData);
                    }
                    //输入4个字节，全0，表示输入结束
                    byte[] end = {0, 0, 0, 0};
                    outputStream.write(end);
                    outputStream.flush();
                    clientConnection.close();
                    System.out.println("数据输出完毕！");
                } catch (IOException e) {
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }

    }

}
