package com.jinxuliang.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public class IntegerToByteEncoder extends MessageToByteEncoder<Integer> {
    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext,
                          Integer integer,
                          ByteBuf byteBuf) throws Exception {
        byteBuf.writeInt(integer);
        System.out.println("IntegerToByteEncoder: Integer " + integer + " 被写入到ByteBuf中");
    }
}
