package daytime;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Date;

/**
 * 向客户端返回Server端的时间
 * 使用单线程模型
 *
 * @author JinXuLiang
 */
public class DayTimeServer {
    public final static int PORT = 9000;
    public static void main(String[] args) {
        try (ServerSocket server = new ServerSocket(PORT)) {
            System.out.println("正在监听端口" + PORT + ",等待客户端连接……");
            while (true) {
                //数据发送完毕之后，自动地断开与Client端的连接
                try (Socket clientConnection = server.accept()) {
                    System.out.println("接收到客户端连接：" + clientConnection);
                    //向客户端发送数据，数据以“\r\n”结束
                    var out = new BufferedWriter(new OutputStreamWriter(
                            clientConnection.getOutputStream(),
                            StandardCharsets.UTF_8));
                    Date now = new Date();
                    out.write(now.toString());
                    out.newLine();
                    out.flush();  //通知底层操作系统，将缓冲区的数据全部发送出去
                } catch (IOException e) {
                }
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
