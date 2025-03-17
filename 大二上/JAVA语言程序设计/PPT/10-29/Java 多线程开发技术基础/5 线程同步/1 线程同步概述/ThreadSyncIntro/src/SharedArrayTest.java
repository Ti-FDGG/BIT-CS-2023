import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

public class SharedArrayTest {
    public static void main(String[] arg) throws InterruptedException {
        //创建一个可保存6个整数的SimpleArray对象
        SimpleArray sharedSimpleArray = new SimpleArray(6);
        //创建两个工作线程向SimpleArray写入数据
        var thread1 = new Thread(new ArrayWriter(1, sharedSimpleArray));
        var thread2 = new Thread(new ArrayWriter(11, sharedSimpleArray));
        //启动两个工作线程
        thread1.start();
        thread2.start();
        //等待两个工作线程结束
        thread1.join();
        thread2.join();
        System.out.println("所有线程运行结束，最终结果为：");
        System.out.println(sharedSimpleArray);
    }
}

//一个简单数据类，内部封装了一个数组，它将被多个线程同时访问
class SimpleArray {
    private final int[] array;
    private int writeIndex = 0;
    private final static Random generator = new Random();

    public SimpleArray(int size) {
        //动态创建指定大小的数组
        array = new int[size];
    }

    public void add(int value) {
        int position = writeIndex;
        try {
            //随机休眠，模拟多线程运行环境
            Thread.sleep(generator.nextInt(500));
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        array[position] = value; //将数据写入到数组中
        System.out.printf("%s 将数值 %2d 写入到了数组的第 %d 个元素。\n",
                Thread.currentThread().getName(), value, position);
        ++writeIndex; //生成下一个可写入的位置
        System.out.printf("下一个可写入的位置是: %d\n", writeIndex);
    }

    //重写toString()方法，显示内部封装的数组内容。
    public String toString() {
        return "\nContents of SimpleArray:\n" + Arrays.toString(array);
    }
}

//封装了线程函数，向SimpleArray对象写入3个整数
class ArrayWriter implements Runnable {
    private final SimpleArray sharedSimpleArray;
    private final int startValue;

    public ArrayWriter(int value, SimpleArray array) {
        startValue = value;
        sharedSimpleArray = array;
    }

    public void run() {
        for (int i = startValue; i < startValue + 3; i++) {
            sharedSimpleArray.add(i);
        }
    }
}



