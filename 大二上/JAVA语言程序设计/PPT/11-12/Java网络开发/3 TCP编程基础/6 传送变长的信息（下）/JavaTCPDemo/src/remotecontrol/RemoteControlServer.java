package remotecontrol;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Date;

/**
 * 接收客户端发来的各项命令，完成特定的工作，然后将结果返回给客户端
 */
public class RemoteControlServer {
    private final static int PORT = 9999;

    public static void main(String[] args) {
        try (ServerSocket server = new ServerSocket(PORT)) {
            System.out.println("正在监听端口" + PORT + ",等待客户端连接……");
            try (Socket clientConnection = server.accept()) {
                System.out.println("接收到客户端连接：" + clientConnection);
                var writer = new OutputStreamWriter(clientConnection.getOutputStream(), StandardCharsets.UTF_8);
                var out = new PrintWriter(writer, true);
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(
                        clientConnection.getInputStream(), StandardCharsets.UTF_8));
                //接收客户端命令
                while (true) {
                    String command = bufferedReader.readLine();
                    System.out.println("收到命令：" + command);
                    //解析命令
                    if (command.equals(ControlCommands.QUIT)) {
                        out.println("Server:接收到退出请求，己退出！");
                        System.out.println("Server退出");
                        break;
                    } else if (command.equals(ControlCommands.NOW)) {
                        out.println("Server当前时间：" + new Date());
                        System.out.println("当前时间己经传给客户端");
                    } else {
                        out.println("Server：未知命令，无法执行");
                        System.out.println("不能识别的命令");
                    }
                }
            } catch (Exception e) {
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
