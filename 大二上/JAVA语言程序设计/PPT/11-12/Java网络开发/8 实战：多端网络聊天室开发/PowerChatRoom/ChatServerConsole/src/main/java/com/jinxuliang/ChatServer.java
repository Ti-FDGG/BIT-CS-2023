package com.jinxuliang;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

import static com.jinxuliang.Constants.QUIT;

public class ChatServer {

    //用于监听客户端连接的端口
    private ServerSocket serverSocket;
    //用于保存在线用户信息
    private Map<InetSocketAddress, Writer> connectedClients;

    public ChatServer() {
        connectedClients = new HashMap<>();
    }

    //有客户端连接进来之后，将其加入到在线用户清单中，同时在控制台窗口输出一条信息
    public synchronized void addClient(Socket socket) throws IOException {
        if (socket != null) {
            int port = socket.getPort();
            var ip = socket.getInetAddress();
            InetSocketAddress socketAddress = new InetSocketAddress(ip, port);
            BufferedWriter writer = new BufferedWriter(
                    new OutputStreamWriter(socket.getOutputStream())
            );
            connectedClients.put(socketAddress, writer);
            socketAddress = new InetSocketAddress(ip, port);
            System.out.println("客户端[" + port + "]已连接到服务器");
        }
    }

    //用户下线
    public synchronized void removeClient(Socket socket) throws IOException {
        if (socket != null) {
            int port = socket.getPort();
            var ip = socket.getInetAddress();
            InetSocketAddress socketAddress = new InetSocketAddress(ip, port);
            if (connectedClients.containsKey(socketAddress)) {
                connectedClients.get(socketAddress).close();
            }
            var result = connectedClients.remove(socketAddress);
            System.out.println("\n客户端[" + socket.getInetAddress() + ":"
                    + socket.getPort() + "]已断开连接,\n当前在线用户数:"
                    + connectedClients.size());
        }
    }

    //一个用户发来消息，Server转发消息给其他的在线用户
    public synchronized void forwardMessage(Socket socket, String fwdMsg)
            throws IOException {
        var ip = socket.getInetAddress();
        var port = socket.getPort();
        var socketAddress = new InetSocketAddress(ip, port);
        System.out.println("\n转发消息:" + socketAddress);
        for (var client : connectedClients.keySet()) {
            if (!socketAddress.equals(client)) {
                Writer writer = connectedClients.get(client);
                writer.write(fwdMsg);
                writer.flush();
            }
        }
    }

    //判断用户发来的消息是不是“下线”请求
    public boolean readyToQuit(String msg) {
        return QUIT.equals(msg);
    }

    //关闭聊天服务
    public synchronized void close() {
        if (serverSocket != null) {
            try {
                serverSocket.close();
                System.out.println("关闭serverSocket");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //启动聊天服务
    public void start() {
        try {
            // 绑定监听端口
            serverSocket = new ServerSocket(Constants.DEFAULT_SERVER_PORT);
            String myIP = InetAddress.getLocalHost().getHostAddress();
            System.out.println("ChatServerConsole在 " + myIP + ":"
                    + Constants.DEFAULT_SERVER_PORT + " 监听");
            while (true) {
                // 等待客户端连接
                Socket socket = serverSocket.accept();
                // 创建ChatHandler线程
                new Thread(new ChatHandler(this, socket)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            close();
        }
    }

}
