package fileStream;

import java.io.*;

//展示文件输入流的基本用法
public class FileInputStreamTest {
    //流的许多方法，都会抛出IOException异常，所以要加以声明
    public static void main(String[] args) throws IOException {
        //创建字节输入流
        var fis = new FileInputStream("./src/fileStream/FileInputStreamTest.java");
        //创建一个长度为1024的数组
        byte[] bbuf = new byte[1024];
        //用于保存实际读取的字节数
        int hasRead = 0;
        //使用循环来重复读取数据
        while ((hasRead = fis.read(bbuf)) > 0) {
            //将字节数组转换成字符串输入
            System.out.print(new String(bbuf, 0, hasRead));
        }
        fis.close();
    }
}
