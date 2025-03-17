package com.jinxuliang.codec;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;

import java.util.List;

public class IntegerToStringDecoder extends MessageToMessageDecoder<Integer> {
    @Override
    protected void decode(ChannelHandlerContext ctx, Integer msg, List<Object> out) {
        String numStr = String.valueOf(msg);
        System.out.println("IntegerToStringDecoder收到整数：" + msg + " 转换为"
                + numStr.getClass().getSimpleName() + ":" + numStr);
        out.add(numStr);
    }
}
