package echo;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class EchoClient {
    private static final String SERVER = "localhost";
    public static void main(String[] args) throws IOException {
        System.out.println("Echo Client is ready...");
        try (var socket = new Socket(SERVER, EchoServer.SERVER_PORT)) {
            var reader = new BufferedReader(new InputStreamReader(socket.getInputStream(),
                    StandardCharsets.UTF_8));
            var writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(),
                    StandardCharsets.UTF_8));
            //接收服务端传回的信息
            String echo = reader.readLine();
            System.out.println(echo);
            while (true) {
                System.out.print("\n请输入：");
                var consoleReader = new BufferedReader(new InputStreamReader(System.in));
                var userInput = consoleReader.readLine();
                //将用户从键盘输入的内容发送到Server端
                writer.write(userInput);
                writer.newLine();
                writer.flush();
                //接收服务端传回的信息
                echo = reader.readLine();
                System.out.println(echo);
                if (userInput.equalsIgnoreCase("quit")) {
                    //断开连接并退出
                    break;
                }
            }
        }
    }
}
