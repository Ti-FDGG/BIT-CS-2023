package com.jinxuliang.fixedlength;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.AttributeKey;

public class MyNettyClient {
    public static void main(String[] args) {
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();
        Bootstrap bootstrap=new Bootstrap();
        var clientName=AttributeKey.newInstance("clientName");
        //可以将一些信息放到chennel中
        bootstrap.attr(clientName,"nettyClient");
        bootstrap.group(workerGroup)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        System.out.println(ch.attr(clientName)+"初始化结束...");
                        //添加handler将数据发送到Server端
                        ch.pipeline().addLast(new MyClientSendHandler());
                    }
                });
        bootstrap.connect("127.0.0.1",9000).addListener(future -> {
            if (future.isSuccess()) {
                System.out.println("连接成功");
            }else {
                System.out.println("连接失败");
            }
        });
    }
}
