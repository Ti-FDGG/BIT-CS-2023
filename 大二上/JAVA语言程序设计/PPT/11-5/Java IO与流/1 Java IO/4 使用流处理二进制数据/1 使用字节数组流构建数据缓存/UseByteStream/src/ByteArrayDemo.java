import java.io.*;

public class ByteArrayDemo {
    public static void main(String[] args) throws IOException {

//        writeToByteArrayOutputStream();
//        readFromByteArrayInputStream();
//        byteArrayToString();
        byteStreamToFileStream();

    }

    //实现两个输出流之间的相互协作
    private static void byteStreamToFileStream() throws IOException {
        try (var bos = new ByteArrayOutputStream(26);
             var outFile = new FileOutputStream("alphabet.txt")) {
            //输出26个字母
            for (int i = 'a'; i <= 'z'; i++) {
                bos.write(i);
            }
            //写入到文件中
            bos.writeTo(outFile);
        };
        System.out.println("数据输出完毕！");
    }

    //写入到数据缓存区中
    private static void writeToByteArrayOutputStream() {
        //初始化缓存区大小为26个字节
        var bos = new ByteArrayOutputStream(26);
        for (int i = 'a'; i <= 'z'; i++) {
            bos.write(i);
        }
        System.out.println("当前缓冲区大小：" + bos.size());//26
        //虽然缓存区写满了，但是仍然可以继续写入，缓存区大小是可以扩充的
        for (int i = 'A'; i <= 'Z'; i++) {
            bos.write(i);
        }
        System.out.println("当前缓冲区大小：" + bos.size());//52
        //将流转换为字节数组
        byte[] data = bos.toByteArray();
        //输出字节数组中的内容
        for (byte elem : data) {
            System.out.print((char) elem);
            System.out.print(" ");
        }
    }

    //以ByteArrayInputStream为中间媒介，将字节数组转换为字符串
    private static void byteArrayToString() throws IOException {
        byte[] b = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j'};
        var bis = new ByteArrayInputStream(b);
        //一次读取全部字节
        String str = new String(bis.readAllBytes());
        //输出：abcdefghij
        System.out.println(str);
        bis.close();
    }

    //从数据缓存区中读
    private static void readFromByteArrayInputStream() {
        byte[] b = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j'};
        //使用字节数组直接构建流
        var bis = new ByteArrayInputStream(b);
        int x;
        //循环读取全部数据
        while ((x = bis.read()) != -1) {
            //将整数转换为char
            System.out.print((char) x + ",");
        }
    }


}
