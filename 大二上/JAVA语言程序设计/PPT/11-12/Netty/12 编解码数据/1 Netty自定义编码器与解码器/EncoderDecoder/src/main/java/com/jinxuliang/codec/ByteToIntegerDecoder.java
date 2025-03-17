package com.jinxuliang.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

public class ByteToIntegerDecoder extends ByteToMessageDecoder {
    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext,
                          ByteBuf byteBuf, List<Object> list) throws Exception {
        if (byteBuf.readableBytes() >= 4) {
            int num = byteBuf.readInt();
            list.add(num);
            System.out.println("ByteToIntegerDecoder接收到整数："+num);
        }
    }
}
