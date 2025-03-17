package com.jinxuliang;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

//负责处理客户端连接请求
public class ChatHandler implements Runnable {
    private ChatServer server;
    private Socket socket;

    public ChatHandler(ChatServer server, Socket socket) {
        this.server = server;
        this.socket = socket;
    }

    @Override
    public void run() {
        if (socket.isInputShutdown() ||
                socket.isOutputShutdown() || socket.isClosed()) {
            return;
        }
        try {
            // 存储新上线用户
            server.addClient(socket);
            // 读取用户发送的消息
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(socket.getInputStream())
            );
            String msg = null;
            while ((msg = reader.readLine()) != null) {
                ChatMessage clientSendMessage = null;
                clientSendMessage = ChatMessage.fromJson(msg);
                if (server.readyToQuit(clientSendMessage.getMessage())) {
                    clientSendMessage = ChatMessage.builder()
                            .userName(clientSendMessage.getUserName())
                            .message("用户已经退出")
                            .build();
                }
                System.out.println(clientSendMessage.getUserName() + ":"
                        + clientSendMessage.getMessage());
                // 将消息转发给聊天室里在线的其他用户
                server.forwardMessage(socket, ChatMessage.toJson(clientSendMessage) + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                server.removeClient(socket);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
