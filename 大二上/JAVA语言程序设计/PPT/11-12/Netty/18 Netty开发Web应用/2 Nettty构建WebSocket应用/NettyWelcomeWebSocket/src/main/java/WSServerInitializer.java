import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.stream.ChunkedWriteHandler;

public class WSServerInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        var pipeline=ch.pipeline();
        //------HTTP相关支持-------
        //集成Http消息的编码器与解码器
        pipeline.addLast(new HttpServerCodec());
        //支持分块写数据流
        pipeline.addLast(new ChunkedWriteHandler());
        //聚合了FullHttpRequest或FullHttpResponse,经常用到。
        pipeline.addLast(new HttpObjectAggregator(1024*64));

        //-------WebSocket支持---------
        pipeline.addLast(new WebSocketServerProtocolHandler("/ws"));
        //自定义Handler
        pipeline.addLast(new ChatHandler());
    }
}
