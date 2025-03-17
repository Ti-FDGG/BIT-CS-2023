package com.jinxuliang.lengthbased;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.AttributeKey;

import java.nio.charset.StandardCharsets;

public class MyNettyClient {
    public static void main(String[] args) {

        NioEventLoopGroup workerGroup = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(workerGroup)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        //添加handler将数据发送到Server端
                        ch.pipeline().addLast(new LengthFieldBasedFrameDecoder(
                                102400,
                                0,
                                2,
                                0,
                                2));
                        ch.pipeline().addLast(new StringDecoder(StandardCharsets.UTF_8));
                        ch.pipeline().addLast(new MyClientSendHandler());
                        //注意，以下两个Handler是放到开头的
                        ch.pipeline().addFirst(new StringEncoder(StandardCharsets.UTF_8));
                        ch.pipeline().addFirst(new LengthFieldPrepender(2));
                    }
                });

        bootstrap.connect("127.0.0.1", 9000).addListener(future -> {
            if (future.isSuccess()) {
                System.out.println("连接成功");
            } else {
                System.out.println("连接失败");
            }
        });
    }
}
