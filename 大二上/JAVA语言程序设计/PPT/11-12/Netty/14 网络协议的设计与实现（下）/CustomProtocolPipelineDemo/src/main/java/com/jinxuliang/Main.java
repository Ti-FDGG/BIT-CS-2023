package com.jinxuliang;

import com.jinxuliang.codec.PacketCodec;
import com.jinxuliang.protocol.LoginRequestPacket;
import com.jinxuliang.protocol.Packet;
import io.netty.buffer.ByteBufUtil;
import io.netty.buffer.Unpooled;

public class Main {
    public static void main(String[] args) {
        //创建用于测试的登录指令
        LoginRequestPacket loginRequestPacket = new LoginRequestPacket();
        loginRequestPacket.setUserId(100);
        loginRequestPacket.setUserName("testUser");
        loginRequestPacket.setPassword("123456");

        System.out.println("将被序列化的Packet对象");
        System.out.println(loginRequestPacket);
        //进行序列化
        var byteBuf = Unpooled.buffer();
        PacketCodec.encode(loginRequestPacket,byteBuf);
        System.out.println(ByteBufUtil.prettyHexDump(byteBuf));

        //读指针移回开头，以便反序列化
        byteBuf.readerIndex(0);

        System.out.println("\n反序列化结果");
        System.out.println(PacketCodec.decode(byteBuf));
    }
}