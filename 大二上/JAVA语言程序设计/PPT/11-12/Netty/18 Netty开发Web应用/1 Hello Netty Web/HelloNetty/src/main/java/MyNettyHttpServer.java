import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.*;
import io.netty.handler.timeout.IdleStateHandler;
import io.netty.util.AttributeKey;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;

import java.util.Date;

public class MyNettyHttpServer {
    private int port=9000;

    public void run() throws InterruptedException {
        EventLoopGroup boosGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        AttributeKey<String> severInfo=AttributeKey.newInstance("ServerInfo");
        try {
            ServerBootstrap boot = new ServerBootstrap();
            boot.group(boosGroup,workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            //启用接收数据的解压功能
                            socketChannel.pipeline().addLast(new HttpContentDecompressor());
                            //这个中间件，包容了两个子Handler: HttpRequestDecoder 和 HttpResponseEncoder
                            //主要完成的，就是编解码功能
                            socketChannel.pipeline().addLast(new HttpServerCodec());
                            //自定义中间件，用于生成HTTP响应
                            socketChannel.pipeline().addLast(new MyHttpHandler());
                            //启用发送数据的压缩功能
                            socketChannel.pipeline().addFirst(new HttpContentCompressor());
                        }
                    });

            ChannelFuture future = boot.bind(port).sync().addListener(new GenericFutureListener<Future<? super Void>>() {
                @Override
                public void operationComplete(Future<? super Void> future) throws Exception {
                    if(future.isSuccess()){
                        System.out.println(port+"端口绑定成功.");
                    }else {
                        System.out.println(port+"端口绑定成功失败");
                    }
                }
            });
            //如果Channel关闭，这里等待其完成
            future.channel().closeFuture().sync();



        } finally {
            boosGroup.shutdownGracefully().addListener((f)->{
                System.out.println("bossGroup关闭");
            });
            workerGroup.shutdownGracefully().addListener(f->{
                System.out.println("workerGroup关闭");
            });
        }
    }

    public static void main(String[] args) throws InterruptedException {
        new MyNettyHttpServer().run();
    }
}
