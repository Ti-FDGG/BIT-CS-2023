package fileStream;

import java.io.*;

public class CopyFileDemo {

    static String inFileStr = "city.jpg";
    static String outFileStr = "city-copy.jpg";

    public static void main(String[] args) {
 //       fileCopyNoBuffer();
//       fileCopyWithBufferAndArray();
        fileCopyUseStreamTransferTo();
    }

    public static void fileCopyNoBuffer() {
        System.out.println("\n没有使用缓存复制文件 ...");
        long startTime, elapsedTime;
        File fileIn = new File(inFileStr);
        System.out.println("要复制的文件大小为： " + fileIn.length() + " 字节。");
        //创建输入流与输出流
        try (FileInputStream in = new FileInputStream(inFileStr);
             FileOutputStream out = new FileOutputStream(outFileStr)) {
            startTime = System.nanoTime();
            int byteRead;
            //从输入流中一个字节一个字节地读（这是最慢的，效率最低的一种方式）
            while ((byteRead = in.read()) != -1) {
                //写入到输出流中
                out.write(byteRead);
            }
            elapsedTime = System.nanoTime() - startTime;
            System.out.println("耗费时间 " + (elapsedTime / 1000000.0) + " 毫秒。");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void fileCopyWithBufferAndArray() {
        System.out.println("\n使用缓存复制文件 ...");
        long startTime, elapsedTime;
        startTime = System.nanoTime();
        //基于文件输入和输出流，构建缓冲流，并且自动关闭
        try (var in = new BufferedInputStream(new FileInputStream(inFileStr));
             var out = new BufferedOutputStream(new FileOutputStream(outFileStr))) {
            //一次最多可读取4000字节
            byte[] byteBuf = new byte[4000];
            int numBytesRead;
            while ((numBytesRead = in.read(byteBuf)) != -1) {
                out.write(byteBuf, 0, numBytesRead);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        elapsedTime = System.nanoTime() - startTime;
        System.out.println("耗费时间: " + (elapsedTime / 1000000.0) + "  毫秒。");
    }

    //使用transferTo方法在流中复制文件
    private static void fileCopyUseStreamTransferTo() {
        System.out.println("\n使用transferTo方法复制文件 ...");
        long startTime, elapsedTime;
        startTime = System.nanoTime();
        try (var inputStream = new FileInputStream(inFileStr);
             var outputStream = new FileOutputStream(outFileStr)) {
            var result = inputStream.transferTo(outputStream);
            System.out.println("复制字节数：" + result);
        } catch (IOException e) {
            e.printStackTrace();
        }
        elapsedTime = System.nanoTime() - startTime;
        System.out.println("耗费时间: " + (elapsedTime / 1000000.0) + "  毫秒。");
    }
}
