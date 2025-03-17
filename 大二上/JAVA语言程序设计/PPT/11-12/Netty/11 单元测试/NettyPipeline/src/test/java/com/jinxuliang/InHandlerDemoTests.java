package com.jinxuliang;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.embedded.EmbeddedChannel;
import org.junit.jupiter.api.Test;

public class InHandlerDemoTests {
    @Test
    public void testInHandlerLifecycle() {
        final InHandlerDemo handlerDemo = new InHandlerDemo();
        ChannelInitializer initializer = new ChannelInitializer<EmbeddedChannel>() {
            @Override
            protected void initChannel(EmbeddedChannel ch) throws Exception {
                ch.pipeline().addLast(handlerDemo);
            }
        };
        var channel = new EmbeddedChannel(initializer);
        ByteBuf buf = Unpooled.buffer();
        buf.writeInt(100);
        channel.writeInbound(buf);
        channel.flush();
        channel.close();
    }

}
