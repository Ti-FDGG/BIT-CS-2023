package com.jinxuliang;

import com.jinxuliang.protocol.Command;
import com.jinxuliang.protocol.LoginRequestPacket;
import com.jinxuliang.protocol.Packet;
import com.jinxuliang.protocol.serialize.Serializer;
import io.netty.buffer.ByteBuf;
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
        var byteBuf = encode(loginRequestPacket);
        System.out.println(ByteBufUtil.prettyHexDump(byteBuf));

        //读指针移回开头，以便反序列化
        byteBuf.readerIndex(0);

        System.out.println("\n反序列化结果");
        System.out.println(decode(byteBuf));
    }

    //固定的魔数值
    private static final int MAGIC_NUMBER = 0xABCDABCD;

    public static ByteBuf encode(Packet packet) {
        //创建ByteBuf对象
        ByteBuf byteBuf = Unpooled.buffer();
        //将Packet对象转换为字节数组
        byte[] bytes = Serializer.DEFAULT.serialize(packet);
        //按照自定义通信协议的格式，写入相应数据，注意，顺序是重要的
        byteBuf.writeInt(MAGIC_NUMBER);
        byteBuf.writeByte(packet.getVersion());
        byteBuf.writeByte(Serializer.DEFAULT.getSerializerAlgorithm());
        byteBuf.writeByte(packet.getCommand());
        byteBuf.writeInt(bytes.length);

        byteBuf.writeBytes(bytes);
        return byteBuf;
    }

    public static Packet decode(ByteBuf byteBuf) {
        //依据自定义协议，计算指令头的总长度
        //MAGIC_NUMBER(4)+版本号（1）+序列化算法（1）+指令Id（1）+数据包长度（4）
        int headerLength = 11;
        if(byteBuf.readableBytes()< headerLength){
            //读取的数据不够
            return null;
        }
        //跳过MAGIC_NUMBER
        byteBuf.skipBytes(4);
        //跳过版本号（本例用不上）
        byteBuf.skipBytes(1);
        //获取序列化算法id
        byte serializerAlgorithm = byteBuf.readByte();
        //依据序列化算法id，获取对应的序列化器实例（比如，JSON序列化，则使用JSONSerializer对象）
        var serializer = Serializer.getSerializer(serializerAlgorithm);
        //获取指令Id
        byte command = byteBuf.readByte();
        //依据指令Id,获取指令的具体类型（Class对象）
        var requestType = Command.getRequestType(command);
        //获取数据包长度
        int length = byteBuf.readInt();

        if(byteBuf.readableBytes() < length){
            //读取的数据不够
            return null;
        }
        //读取指令包容的具体数据
        byte[] bytes = new byte[length];
        byteBuf.readBytes(bytes);

        if (requestType != null && serializer != null) {
            //反序列化，得到具体的Packet对象
            return serializer.deserialize(requestType, bytes);
        }
        return null;
    }

}