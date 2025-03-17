package com.jinxuliang.client;

import com.jinxuliang.server.ChatServer;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.io.IOException;
import java.net.InetSocketAddress;

public class ChatClient {
    private final String host;
    private final int port;

    public ChatClient(String host, int port) {
        this.host = host;
        this.port = port;
    }

    //客户端启动流程
    public void start() throws Exception {
        EventLoopGroup group = new NioEventLoopGroup();
        var clientHandler = new ChatClientHandler();
        try {
            Bootstrap boot = new Bootstrap();
            boot.group(group)
                    .channel(NioSocketChannel.class)
                    .remoteAddress(new InetSocketAddress(host, port))
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast(clientHandler);
                        }
                    });
            try {
                //连接服务器
                ChannelFuture f = boot.connect().sync();
                InetSocketAddress addr = (InetSocketAddress) f.channel().localAddress();
                System.out.println("成功连接服务器,监听：" + addr.getPort());
                //在独立的线程中接收用户输入
                handleUserInput(clientHandler);
                f.channel().closeFuture().sync();
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        } finally {
            group.shutdownGracefully().sync();
        }
    }

    //启动一个单独的线程，接收用户输入：
    private static void handleUserInput(ChatClientHandler clientHandler) {
        var userInputThread = new Thread(() -> {
            try {
                while (true) {
                    String userInput = InputHelper.getUserInput(">>>");
                    if (userInput.equals("exit")) {
                        //退出并关闭通道
                        clientHandler.close();
                        break;
                    } else {
                        //将消息发送出去
                        clientHandler.sendMessage(userInput);
                    }
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        userInputThread.setDaemon(true);
        userInputThread.start();
    }

    public static void main(String[] args) throws Exception {
        new ChatClient("127.0.0.1", ChatServer.SERVER_PORT).start();
    }
}
