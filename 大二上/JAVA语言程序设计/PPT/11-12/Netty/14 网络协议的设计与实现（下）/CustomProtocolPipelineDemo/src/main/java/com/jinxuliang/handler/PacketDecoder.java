package com.jinxuliang.handler;

import com.jinxuliang.codec.PacketCodec;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

//解码Netty传过来的ByteCode，将其解码为特定的Pack类型，
//比如LoginRequestPacket和LoginResponsePacket
public class PacketDecoder extends ByteToMessageDecoder {

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in,
                          List<Object> out) throws Exception {
        System.out.println("PacketDecoder接收到的远方发来的byteBuf");
        System.out.println(ByteBufUtil.prettyHexDump(in));
        var packet = PacketCodec.decode(in);
        System.out.println("PacketDecoder反序列化得到的Packet:" + packet);
        out.add(packet);
    }
}
