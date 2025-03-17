package echo;

import java.io.*;
import java.net.ServerSocket;
import java.nio.charset.StandardCharsets;

//基于换行符实现的“一对一通讯”
public class EchoServer {
    public static final int SERVER_PORT = 10010;

    public static void main(String[] args) throws IOException {
        System.out.println("Echo Server is ready...");
        try (var server = new ServerSocket(SERVER_PORT)) {
            System.out.println("正在监听端口" + SERVER_PORT + ",等待客户端连接……");
            while (true) {
                try (var client = server.accept()) {
                    System.out.println("收到客户端连接请求["+client.getInetAddress()+":"+ client.getPort()+"]");
                    var reader = new BufferedReader(new InputStreamReader(client.getInputStream(), StandardCharsets.UTF_8));
                    var writer = new BufferedWriter(new OutputStreamWriter(client.getOutputStream(), StandardCharsets.UTF_8));
                    //输出欢迎信息
                    writer.write("欢迎访问鹦鹉学舌服务器，请输入字符串，输入”quit“退出。");
                    writer.newLine();
                    writer.flush();
                    //响应客户端请求
                    while (true){
                        String message = reader.readLine();
                        //结束服务，断开客户端连接
                        if(message.equalsIgnoreCase("quit")){
                            System.out.println("客户端退出");
                            writer.write("Server:谢谢惠顾，下次再来！");
                            writer.newLine();
                            writer.flush();
                            break;
                        }
                        //向客户端返回信息
                        writer.write("Server:" + message);
                        writer.newLine();
                        writer.flush();
                    }
                }
            }
        }
    }
}
