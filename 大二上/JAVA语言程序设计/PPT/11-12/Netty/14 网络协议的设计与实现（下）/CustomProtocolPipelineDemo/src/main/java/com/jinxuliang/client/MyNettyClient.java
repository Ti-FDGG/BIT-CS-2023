package com.jinxuliang.client;

import com.jinxuliang.handler.PacketDecoder;
import com.jinxuliang.handler.PacketEncoder;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.AttributeKey;

public class MyNettyClient {
    //用于保存登录结果
    static AttributeKey<Object> clientKey =
            AttributeKey.newInstance("loginResult");

    public static void main(String[] args) {
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(workerGroup)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel ch) throws Exception {
                        ch.pipeline().addLast(new MyNettyClientLoginRequestHandler());
                        ch.pipeline().addLast(new PacketDecoder());
                        ch.pipeline().addLast(new LoginResponseHandler());
                        ch.pipeline().addFirst(new PacketEncoder());
                    }
                });
        bootstrap.connect("127.0.0.1", 9000).syncUninterruptibly()
                .addListener((ChannelFutureListener) future -> {
                    if (future.isSuccess()) {
                        System.out.println("连接成功");
                    } else {
                        System.out.println("连接失败");
                    }
                });
    }
}
