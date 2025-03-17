import com.jinxuliang.codec.ByteToIntegerDecoder;
import com.jinxuliang.codec.IntegerToByteEncoder;
import com.jinxuliang.codec.IntegerToStringDecoder;
import com.jinxuliang.codec.MyInboundHandler;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.embedded.EmbeddedChannel;
import org.junit.jupiter.api.Test;

public class EncoderDecoderTests {

    @Test
    public void testEncoderDecoder() {
        //构建数据处理流水线
        ChannelInitializer initializer = new ChannelInitializer<EmbeddedChannel>() {
            @Override
            protected void initChannel(EmbeddedChannel ch) throws Exception {
                ch.pipeline().addLast(new IntegerToByteEncoder());
                ch.pipeline().addLast(new ByteToIntegerDecoder());
                ch.pipeline().addLast(new IntegerToStringDecoder());
                ch.pipeline().addLast(new MyInboundHandler());
            }
        };
        var channel = new EmbeddedChannel(initializer);
        ByteBuf buf= Unpooled.buffer();
        buf.writeInt(1);
        //模拟客户端将整数1发给服务端，这将启动整条流水线
        channel.writeInbound(buf);
        //读取最终的结果
        var result = channel.readOutbound();
        //显示将要发送到网络上的最终的ByteBuf的内容
        System.out.println(ByteBufUtil.prettyHexDump((ByteBuf) result));
        //关闭退出
        channel.finish();
        channel.close();
    }

}
