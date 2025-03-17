package com.jinxuliang.variablelength;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

//期望消息格式：4字节消息长度|真实消息数据
public class PrefixLengthDecoder extends ByteToMessageDecoder {
    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext,
                          ByteBuf byteBuf, List<Object> list) throws Exception {
        if(byteBuf.readableBytes()<4){
            return;
        }
        int length = byteBuf.readInt();
        System.out.println("length:"+length);
        //只有等到收到了完整的数据，才开始运行
        if(byteBuf.readableBytes()<length){
            byteBuf.resetReaderIndex();
            return;
        }
        byte[] data=new byte[length];
        byteBuf.readBytes(data);
        System.out.println(new String(data));
        //记下当前位置，以便从这里继续读
        //这时假设Client会发送多条数据。
        byteBuf.markReaderIndex();
    }
}
