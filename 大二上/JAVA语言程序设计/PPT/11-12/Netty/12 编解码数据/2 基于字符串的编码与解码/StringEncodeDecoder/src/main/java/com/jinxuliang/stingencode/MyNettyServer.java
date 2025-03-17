package com.jinxuliang.stingencode;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

import java.nio.charset.StandardCharsets;

public class MyNettyServer {
    private int port = 9000;

    public void run() throws InterruptedException {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap boot = new ServerBootstrap();
            boot.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            socketChannel.pipeline().addLast(new StringEncoder(StandardCharsets.UTF_8));
                            socketChannel.pipeline().addLast(new StringDecoder(StandardCharsets.UTF_8));
                            socketChannel.pipeline().addLast(new MyServerHandler());
                        }
                    });
            ChannelFuture f = boot.bind(port).addListener(future -> {
                if (future.isSuccess()) {
                    System.out.println(port + "端口绑定成功.");
                } else {
                    System.out.println(port + "端口绑定成功失败");
                }
            });
            f.channel().closeFuture().sync();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        new MyNettyServer().run();
    }
}
