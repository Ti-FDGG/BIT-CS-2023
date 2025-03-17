package com.jinxuliang.handler;

import com.jinxuliang.codec.PacketCodec;
import com.jinxuliang.protocol.LoginResponsePacket;
import com.jinxuliang.protocol.Packet;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

//负责将一个Packet对象序列化到ByteBuf中，然后再发送出去
public class PacketEncoder extends MessageToByteEncoder<Packet> {
    @Override
    protected void encode(ChannelHandlerContext ctx, Packet msg, ByteBuf out) {
        System.out.println("PacketEncoder收到的Packet:" + msg);
        PacketCodec.encode(msg, out);
        ctx.writeAndFlush(out);
        System.out.println("PacketEncoder最终发送出去的ByteBuf："
                + ByteBufUtil.prettyHexDump(out));
    }
}
