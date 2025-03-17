package com.jinxuliang;

import java.io.*;
import java.net.Socket;

public class ChatClient {
    private Socket socket;
    //接收消息
    private BufferedReader reader;
    //发送消息
    private BufferedWriter writer;

    private String serverIP;
    private String userName;

    public ChatClient(String serverIP, String userName) {
        this.serverIP = serverIP;
        this.userName = userName;
    }

    // 发送消息给服务器
    public void send(String msg) throws IOException {
        ChatMessage messageToBeSent = null;
        if (!socket.isOutputShutdown()) {
            messageToBeSent = ChatMessage.builder().userName(userName)
                    .message(msg)
                    .build();
            writer.write(ChatMessage.toJson(messageToBeSent) + "\n");
            writer.flush();

        }
    }

    // 从服务器接收消息
    public String receive() {
        String msg = null;
        if (!socket.isInputShutdown() && !socket.isClosed()) {
            try {
                msg = reader.readLine();
            } catch (IOException e) {

            }
        }
        return msg;
    }

    // 检查用户是否准备退出
    public boolean readyToQuit(String msg) {
        return Constants.QUIT.equals(msg);
    }

    // 关闭Socket并退出
    public void close() {
        if (writer != null && !socket.isClosed()) {
            try {
                System.out.println("关闭socket");
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void start() {
        try {
            // 创建socket
            socket = new Socket(serverIP, Constants.DEFAULT_SERVER_PORT);
            // 创建IO流
            reader = new BufferedReader(
                    new InputStreamReader(socket.getInputStream())
            );
            writer = new BufferedWriter(
                    new OutputStreamWriter(socket.getOutputStream())
            );
            // 处理用户的输入
            new Thread(new UserInputHandler(this)).start();
            // 读取服务器转发的消息
            String msg = null;
            while ((msg = receive()) != null) {
                ChatMessage message = ChatMessage.fromJson(msg);
                System.out.println("\n" + message.getUserName() + ":" + message.getMessage());
                System.out.print(">");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
