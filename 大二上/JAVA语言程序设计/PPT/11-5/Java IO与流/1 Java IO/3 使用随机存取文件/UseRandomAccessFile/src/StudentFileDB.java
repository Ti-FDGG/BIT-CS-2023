
import java.io.*;
import java.util.*;

public class StudentFileDB {
    public static void main(String[] args) {
        //测试用示例数据
        Student[] students = {
                new Student("张三", 90),
                new Student("李四", 95),
                new Student("王五", 88),
                new Student("赵六", 84)};

        File file = new File("student.dat");
        // 建立RandomAccessFile实例并以读写模式开启文件
        // 利用try with resource特性，在用完后自动关闭文件
        try (var randomAccessFile = new RandomAccessFile(file, "rw")) {

            for (Student value : students) {
                // 使用对应的write方法写入数据
                randomAccessFile.writeChars(value.getName());
                randomAccessFile.writeInt(value.getScore());
            }

            Scanner scanner = new Scanner(System.in);
            System.out.print("读取第几笔数据？");
            int num = scanner.nextInt();

            // 使用seek()方法操作存取位置
            randomAccessFile.seek((long) (num - 1) * Student.size());
            Student student = new Student();

            // 使用对应的read方法读出数据
            student.setName(readName(randomAccessFile));
            student.setScore(randomAccessFile.readInt());

            System.out.println("姓名：" + student.getName());
            System.out.println("分数：" + student.getScore());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //读取学生姓名的辅助方法
    private static String readName(
            RandomAccessFile randomAccessfile)
            throws IOException {
        char[] name = new char[15];
        for (int i = 0; i < name.length; i++)
            name[i] = randomAccessfile.readChar();
        // 将空字符取代为空格符并返回
        return new String(name).replace('\0', ' ');
    }
}


class Student {
    private String name;
    private int score;

    public Student() {
        setName("noname");
    }

    public Student(String name, int score) {
        setName(name);
        this.score = score;
    }

    public void setName(String name) {
        StringBuilder builder = null;
        if (name != null)
            builder = new StringBuilder(name);
        else
            builder = new StringBuilder(15);

        builder.setLength(15); // 最长 15 字符
        this.name = builder.toString();
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    // 每笔数据固定写入34字节
    public static int size() {
        return 34;
    }
}