package echo;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class SimplestEchoServer {
    public static final int SERVER_PORT = 10001;

    public static void main(String[] args) throws IOException {
        System.out.println("Echo Server is ready...");
        try (var server = new ServerSocket(SERVER_PORT)) {
            Socket client = server.accept();
            InputStream in = client.getInputStream();
            OutputStream out = client.getOutputStream();
            var writer = new BufferedWriter(new OutputStreamWriter(out, StandardCharsets.UTF_8));
            //输出欢迎信息
            writer.write("欢迎访问鹦鹉学舌服务器，请输入字符串，输入”quit“退出。");
            writer.newLine();
            writer.flush();
            while (true) {
                //使用JDK9引入的transferTo方法，可以直接在两个流中传输数据
                in.transferTo(out);
            }
        }
    }
}
