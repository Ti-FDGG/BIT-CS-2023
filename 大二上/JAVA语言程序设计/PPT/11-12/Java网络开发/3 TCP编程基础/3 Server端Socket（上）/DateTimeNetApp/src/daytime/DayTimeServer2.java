package daytime;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Date;


//向客户端返回Server端的时间,
//使用多线程处理客户连接
public class DayTimeServer2 {
    public final static int PORT = 8899;
    public static void main(String[] args) {
        try (ServerSocket server = new ServerSocket(PORT)) {
            System.out.println("正在监听端口" + PORT + ",等待客户端连接……");
            while (true) {
                Socket clientConnection = server.accept();
                //启用新线程响应客户端的连接请求
                new DateTimeThread(clientConnection).start();
                //线程启动之后，监听线程继续调用accept()方法等待新的连接请求
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    //在一个独立的线程中向客户端发送数据
    private static class DateTimeThread extends Thread {
        private final Socket clientConnection;
        public DateTimeThread(Socket connection) {
            this.clientConnection = connection;
        }
        @Override
        public void run() {
            try {
                System.out.println("接收到客户端连接：" + clientConnection);
                System.out.println("负责处理的线程：" + Thread.currentThread());
                var out = new BufferedWriter(new OutputStreamWriter(
                        clientConnection.getOutputStream(),
                        StandardCharsets.UTF_8));
                Date now = new Date();
                String message=now.toString();
                out.write(message);
                out.newLine();
                out.flush();
                System.out.println("写入数据："+message);

            } catch (Exception e) {
            } finally {
                try {
                    clientConnection.close();  //数据发送完毕，关闭连接
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
