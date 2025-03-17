package com.jinxuliang.stingencode;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

import java.nio.charset.StandardCharsets;

public class MyNettyClient {
    public static void main(String[] args) throws InterruptedException {
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();
        Bootstrap bootstrap=new Bootstrap();
        bootstrap.group(workerGroup)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        //先加入编码器
                        ch.pipeline().addLast(new StringEncoder(StandardCharsets.UTF_8));
                        //再加入解码器
                        ch.pipeline().addLast(new StringDecoder(StandardCharsets.UTF_8));
                        //添加自定义handler将数据发送到Server端
                        ch.pipeline().addLast(new MyClientSendHandler());
                    }
                });
       var conn= bootstrap.connect("127.0.0.1",9000).addListener(future -> {
            if (future.isSuccess()) {
                System.out.println("连接成功");
            }else {
                System.out.println("连接失败");
            }
        });
       //阻塞等待通道的关闭
       conn.channel().closeFuture().sync();
       //关闭线程池，以结束整个进程
       workerGroup.shutdownGracefully().addListener(future -> {
           System.out.println("客户端已经退出");
       });
    }
}
