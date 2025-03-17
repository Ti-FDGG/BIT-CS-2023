package com.jinxuliang;

import com.jinxuliang.handlers.MyServerPipeline;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;


public class MyNettyServer {
    private final int port = 9000;

    public void run() throws InterruptedException {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            ServerBootstrap boot = new ServerBootstrap();

            boot.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<NioSocketChannel>() {
                        @Override
                        protected void initChannel(NioSocketChannel ch) throws Exception {
                            //testInboundChannelPipeline(ch);
                            testOutboundChannelPipeline(ch);
                        }
                    });

            ChannelFuture future = boot.bind(port).sync().addListener(new GenericFutureListener<Future<? super Void>>() {
                @Override
                public void operationComplete(Future<? super Void> future) throws Exception {
                    if (future.isSuccess()) {
                        System.out.println(port + "端口绑定成功.");
                    } else {
                        System.out.println(port + "端口绑定成功失败");
                    }
                }
            });
            //如果Channel关闭，这里等待其完成
            future.channel().closeFuture().sync();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            bossGroup.shutdownGracefully().addListener((f) -> {
                System.out.println("bossGroup关闭");
            });
            workerGroup.shutdownGracefully().addListener(f -> {
                System.out.println("workerGroup关闭");
            });
        }
    }

    //测试入站pipeline
    private static void testInboundChannelPipeline(NioSocketChannel ch) {
        ch.pipeline().addLast(new MyServerPipeline.SimpleInHandlerA());
        ch.pipeline().addLast(new MyServerPipeline.SimpleInHandlerB());
        ch.pipeline().addLast(new MyServerPipeline.SimpleInHandlerC());
    }

    //测试出站pipeline
    private static void testOutboundChannelPipeline(NioSocketChannel ch) {
        //入站Handler排在前面，会导致后面的出站Handler无法执行
        //ch.pipeline().addLast(new MyServerPipeline.SimpleInHandler());
        //顺序很重要，出站Handler执行顺序：C-->B-->A
        ch.pipeline().addLast(new MyServerPipeline.SimpleOutHandlerA());
        ch.pipeline().addLast(new MyServerPipeline.SimpleOutHandlerB());
        ch.pipeline().addLast(new MyServerPipeline.SimpleOutHandlerC());
        //最后是入站Handler
        ch.pipeline().addLast(new MyServerPipeline.SimpleInHandler());
    }


    public static void main(String[] args) throws InterruptedException {
        new MyNettyServer().run();
    }
}
