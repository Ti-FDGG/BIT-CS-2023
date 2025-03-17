package com.jinxuliang;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.AttributeKey;

import java.time.LocalTime;
import java.util.concurrent.TimeUnit;

public class MyNettyClient {
    public static void main(String[] args) {
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();

        Bootstrap bootstrap = new Bootstrap();
        var clientName = AttributeKey.newInstance("clientName");
        //可以将一些信息放到chennel中
        bootstrap.attr(clientName, "nettyClient");
        bootstrap.group(workerGroup)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        System.out.println(ch.attr(clientName) + "初始化结束...");
                    }
                });
        //连接Server
        connect(bootstrap, "127.0.0.1", 9000, 5);


    }

    //最大尝试次数为5
    private final static int MAX_RETRY = 5;
    //自动重连5次
    private static void connect(Bootstrap bootstrap, String host, int port, int retry) {
        bootstrap.connect(host, port).addListener(future -> {
            if (future.isSuccess()) {
                System.out.println("连接成功");
            } else {
                if (retry == 0) {
                    System.out.println("重试次数已经用完，放弃连接并关闭退出。");
                    ((ChannelFuture) future).channel().close().sync();
                    bootstrap.config().group().shutdownGracefully();
                } else {
                    //这是第几次重连？
                    int order = (MAX_RETRY - retry) + 1;
                    //重新计算等待的时间
                    int delayTime = 1 << order;
                    System.out.println(LocalTime.now() + ":连接失败，第" + order + "次重连,等待" + delayTime + "秒");
                    //调用EventLoopGroup的延时调用功能，递归调用connect方法进行连接……
                    bootstrap.config().group().schedule(() ->
                                    connect(bootstrap, host, port, retry - 1),
                            delayTime, TimeUnit.SECONDS);

                }
            }
        });
    }
}
