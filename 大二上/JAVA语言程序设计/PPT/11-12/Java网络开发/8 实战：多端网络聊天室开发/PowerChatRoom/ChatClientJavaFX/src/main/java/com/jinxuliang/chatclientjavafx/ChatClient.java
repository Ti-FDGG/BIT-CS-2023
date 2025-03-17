package com.jinxuliang.chatclientjavafx;

import com.jinxuliang.ChatMessage;
import com.jinxuliang.Constants;

import java.io.*;
import java.net.Socket;

public class ChatClient {
    private Socket socket;
    //接收消息
    private BufferedReader reader;
    //发送消息
    private BufferedWriter writer;
    private IShowMessage uiController;
    private String serverIP;
    private String userName;

    public ChatClient(String serverIP, IShowMessage uiController, String userName) {
        this.serverIP = serverIP;
        this.uiController = uiController;
        this.userName = userName;
    }

    // 发送消息给服务器
    public void send(String msg) throws IOException {
        if (!socket.isOutputShutdown()) {
            writer.write(msg + "\n");
            writer.flush();
        }
    }

    // 从服务器接收消息
    public String receive() {
        String msg = null;
        if (!socket.isInputShutdown()) {
            try {
                msg = reader.readLine();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
        return msg;
    }


    // 关闭Socket并退出
    public void close() {
        if (writer != null) {
            try {
                this.uiController.showStatus("关闭socket");
                //发送关闭消息
                ChatMessage quitMessage = ChatMessage.builder().userName(userName)
                        .message("quit").build();
                send(ChatMessage.toJson(quitMessage));
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
            uiController.showStatus("正在等待服务端传回的消息...");
            // 读取服务器转发的消息
            String msg = null;
            while ((msg = receive()) != null) {
                ChatMessage message = ChatMessage.fromJson(msg);
                uiController.showMessage(message);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            close();
        }
    }


}
