package com.jinxuliang;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.Pipe;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws IOException {
//        writeToFileUseBuffer();
//        readFromFileUseBuffer();
        copyFileUseChannel();


    }


    private static void copyFileUseChannel() throws IOException {
        try (var randomAccessFile = new RandomAccessFile("data.txt", "rwd");
             var channel = randomAccessFile.getChannel();
             var targetFile = new RandomAccessFile("data_copied.txt", "rw");
             var targetChannel = targetFile.getChannel()) {
            //以下两种方式，都可以实现文件复制
            //var numberTransfered=targetChannel.transferFrom(channel,0,channel.size());
            var numberTransfered = channel.transferTo(0,
                    channel.size(), targetChannel);
            System.out.println("共复制字节：" + numberTransfered);
            System.out.println(Files.readString(Paths.get("data_copied.txt")));
        }
    }

    private static void readFromFileUseBuffer() throws IOException {
        //以“同步数据更新的读写”模式打开文件
        try (var randomAccessFile = new RandomAccessFile("temp.data", "r");
             var channel = randomAccessFile.getChannel();
        ) {
            //缓冲区要足够大,以便一次读入所有数据
            byte[] content = new byte[(int) randomAccessFile.length()];
            var buffer = ByteBuffer.wrap(content);
            int readCount = channel.read(buffer);
            System.out.println("读入字节数：" + readCount);
            //读入数据的前半段是字符串
            int stringLength = readCount - Integer.BYTES * 2;
            var stringContent = new String(Arrays.copyOf(content, stringLength),
                    StandardCharsets.UTF_8);
            System.out.println(stringContent);//输出字符串
            //指针移到后部,读取两个整数
            buffer.position(readCount - Integer.BYTES * 2);
            //输出两个整数
            System.out.println(buffer.getInt());
            System.out.println(buffer.getInt());
        }
    }

    private static void writeToFileUseBuffer() throws IOException {
        try (var outputStream = new FileOutputStream("temp.data")) {
            //获取文件通道
            var channel = outputStream.getChannel();
            //通道类型:class sun.nio.ch.FileChannelImpl
            System.out.println("通道类型:"+channel.getClass());
            //写入字符串
            writeStringToFile(channel);
            //写入整数
            writeTwoIntegerToFile(channel);
        }
    }

    private static void writeTwoIntegerToFile(FileChannel channel)
            throws IOException {
        //创建一个可以保存两个整数的Buffer
        var intBuffer = ByteBuffer.allocate(Integer.BYTES * 2);
        intBuffer.putInt(100);
        intBuffer.putInt(255);
        //如果这时再次向Buffer中写入数据，由于数据己满,
        //会抛出异常：BufferOverflowException

        //必须将读写指针移到开头，否则，写入的字节为0.
        intBuffer.flip();
        int numWrites = channel.write(intBuffer);
        System.out.println("写入两个整数:100 255");
        //两个整数被追加到了文件尾部
        System.out.println("写入字节数：" + numWrites);
    }

    private static void writeStringToFile(FileChannel channel)
            throws IOException {
        var stringToWrite = "你好，中国！";
        //创建Buffer
        var stringData = stringToWrite.getBytes(StandardCharsets.UTF_8);
        var byteBuffer = ByteBuffer.wrap(stringData);
        //将Buffer中的内容通过通道写入文件
        int numWrites = channel.write(byteBuffer);
        System.out.println("写入字符串:" + stringToWrite);
        System.out.println("写入字节数：" + numWrites);
    }


}
