import java.io.IOException;
import java.io.RandomAccessFile;

public class UseRandomAccessFileDemo {
    public static void main(String[] args) throws IOException {
        var rf = new RandomAccessFile("Data.txt", "rw");
        System.out.println((char) rf.read());//A
        System.out.println((char) rf.read());//B
        System.out.println((char) rf.read());//C
        rf.write('d'); //写入d
        System.out.println((char) rf.read());//E
        rf.skipBytes(3);//跳过3个字节
        System.out.println((char) rf.read());//I
        rf.seek(3); //回到索引值为3的位置
        System.out.println((char) rf.read());//d
        //获取文件当前读写指针，即当前读写的位置索引
        System.out.println(rf.getFilePointer()); //4
        //向后移动两个字节
        rf.seek(rf.getFilePointer() + 2);
        System.out.println((char) rf.read());//G
    }
}
