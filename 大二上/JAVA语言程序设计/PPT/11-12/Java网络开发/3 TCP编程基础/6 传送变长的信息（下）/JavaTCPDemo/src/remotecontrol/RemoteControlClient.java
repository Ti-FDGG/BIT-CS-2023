package remotecontrol;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Reader;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Scanner;

/**
 * 向Server端发送各项命令 Server端完成相应操作之后返回结果
 *
 * @author JinXuLiang
 *
 */
public class RemoteControlClient {

    private final static int port = 9999;
    private final static String server = "127.0.0.1";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try (Socket socket = new Socket(server, port)) {
            System.out.println("Server己连接");
            // 准备好读取和写入数据的流对象
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            Reader reader = new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8);
            BufferedReader bufferedReader = new BufferedReader(reader);
            while (true) {
                System.out.print("输入命令：");
                String userInput = scanner.nextLine();
                // 将命令发送到Server端
                if (userInput != null && userInput.length() > 0) {
                    userInput = userInput.trim().toLowerCase();
                    out.println(userInput);
                    // 接收Server端发来的响应
                    String response = bufferedReader.readLine();
                    if (response != null) {
                        System.out.println(response);
                        // 如果是退出命令并且Server端己经成功发来响应，则退出
                        if (userInput.equals(ControlCommands.QUIT)) {
                            break;
                        }
                    }
                }
            }
            System.out.println("客户端退出");
            scanner.close();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("无法连接Server...");
        }
    }
}

