package fileStream;

import java.io.*;

//文件输入流与文件输出流配合使用，实现文件复制
public class FileOutputStreamTest {
    public static void main(String[] args) throws IOException {
        FileInputStream fis = null;
        FileOutputStream fos = null;
        try {
            //创建字节输入流
            fis = new FileInputStream("./src/fileStream/FileOutputStreamTest.java");
            //创建字节输出流
            fos = new FileOutputStream("newFile.txt");
            byte[] bbuf = new byte[32];
            int hasRead = 0;
            //循环从输入流中取出数据
            while ((hasRead = fis.read(bbuf)) > 0) {
                //每读取一次，即写入文件输出流，读了多少，就写多少。
                fos.write(bbuf, 0, hasRead);
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } finally {
            //使用finally块来关闭文件输入流
            if (fis != null) {
                fis.close();
            }
            //使用finally块来关闭文件输出流
            if (fos != null) {
                fos.close();
            }
        }
    }
}
