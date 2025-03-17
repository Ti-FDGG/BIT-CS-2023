package com.jinxuliang;

import com.jinxuliang.codec.PacketCodec;
import com.jinxuliang.server.LoginRequestHandler;
import com.jinxuliang.client.LoginResponseHandler;
import com.jinxuliang.handler.PacketDecoder;
import com.jinxuliang.handler.PacketEncoder;
import com.jinxuliang.protocol.LoginRequestPacket;
import com.jinxuliang.protocol.LoginResponsePacket;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.embedded.EmbeddedChannel;
import org.junit.jupiter.api.Test;

public class PipelineTest {
    @Test
    public void testServerPipeline() {
        ChannelInitializer<EmbeddedChannel> initializer = new ChannelInitializer<>() {
            @Override
            protected void initChannel(EmbeddedChannel ch) throws Exception {
                ch.pipeline().addLast(new PacketDecoder());
                ch.pipeline().addLast(new LoginRequestHandler());
                ch.pipeline().addFirst(new LoginResponseHandler());
                ch.pipeline().addFirst(new PacketEncoder());
            }
        };
        var channel = new EmbeddedChannel(initializer);
        //创建登录请求对象
        LoginRequestPacket loginRequestPacket = new LoginRequestPacket();
        loginRequestPacket.setUserId(1000);
        loginRequestPacket.setUserName("testUser");
        loginRequestPacket.setPassword("123456");

        //模拟客户端发来的数据，Netty是会将其转换为ByteBuf的，这里就直接实例化了。
        var clientSendData= PacketCodec.encode(loginRequestPacket,null);
        //将数据传入管线
        channel.writeInbound(clientSendData);
        //结束测试
        channel.finish();
        channel.close();
    }

    @Test
    public void testClientPipeline() {
        ChannelInitializer<EmbeddedChannel> initializer = new ChannelInitializer<>() {
            @Override
            protected void initChannel(EmbeddedChannel ch) throws Exception {
                ch.pipeline().addLast(new PacketDecoder());
                ch.pipeline().addLast(new LoginResponseHandler());
                ch.pipeline().addFirst(new PacketEncoder());
            }
        };
        var channel = new EmbeddedChannel(initializer);
        //生成服务端响应对象
        LoginResponsePacket loginResponsePacket = new LoginResponsePacket();
        loginResponsePacket.setIsSuccess(true);
        loginResponsePacket.setMessage("登录成功");
        //编码为ByteBuf
        var clientRecvData= PacketCodec.encode(loginResponsePacket,null);
        //模拟客户端收到了数据
        channel.writeInbound(clientRecvData);
        channel.finish();
        channel.close();
    }
}
