package OneToOne;

import java.io.UnsupportedEncodingException;

/**
 * 封装一对一通讯时用到的一些辅助功能
 *
 * @author JinXuLiang
 */

public class One2OneHelper {

    //按照UTF8编码消息，然后将消息长度放到消息开头（占4个字节），
    //返回处理好的字节数组
    public static byte[] getStringBytes(String message)
            throws IllegalArgumentException {
        if (message == null || message.length() == 0) {
            throw new IllegalArgumentException("要发送的消息不能为空");
        }
        try {
            //按照UTF-8编码分解字符串为字节数组
            byte[] messageData = message.getBytes("utf-8");
            //将消息长度转为字节数组
            byte[] messageLength = intToByte4(messageData.length);
            //构建一个能容纳所有数据的数组
            byte[] data = new byte[4 + messageData.length];
            //复制字符串长度数值
            int index = 0;
            for (index = 0; index < 4; index++) {
                data[index] = messageLength[index];
            }
            //复制消息数据
            for (int i = 0; i < messageData.length; i++) {
                data[index + i] = messageData[i];
            }
            return data;
        } catch (UnsupportedEncodingException e) {
            throw new IllegalArgumentException("要发送的消息不能转换为UTF-8编码");
        }
    }

    /**
     * 基于UTF-8编码从字节数组（是已经移除过前4个字节的原始数据）中还原数据
     */
    public static String getStringFromUTF8ByteArray(byte[] data)
            throws UnsupportedEncodingException {
        if (data == null || data.length == 0) {
            throw new IllegalArgumentException("要解码的数据能为空");
        }
        String message = new String(data, "utf-8");
        return message;
    }

    /**
     * int整数转换为4字节的byte数组
     */
    public static byte[] intToByte4(int i) {
        byte[] targets = new byte[4];
        targets[3] = (byte) (i & 0xFF);
        targets[2] = (byte) (i >> 8 & 0xFF);
        targets[1] = (byte) (i >> 16 & 0xFF);
        targets[0] = (byte) (i >> 24 & 0xFF);
        return targets;
    }

    /**
     * byte数组转换为int整数
     */
    public static int byte4ToInt(byte[] bytes, int off) {
        int b0 = bytes[off] & 0xFF;
        int b1 = bytes[off + 1] & 0xFF;
        int b2 = bytes[off + 2] & 0xFF;
        int b3 = bytes[off + 3] & 0xFF;
        return (b0 << 24) | (b1 << 16) | (b2 << 8) | b3;
    }

}
