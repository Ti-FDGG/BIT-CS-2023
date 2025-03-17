package localdata;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
                wrongCode();
        //        useSynchronize();
        //        correctCode();
    }

    private static void wrongCode() {
        List<Thread> threads = new ArrayList<>();
        var myData = new MyData();
        int threadCount = 5;
        for (int i = 0; i < threadCount; i++) {
            var thread = new MyThread(myData);
            threads.add(thread);
            thread.start();
        }
        threads.forEach(thread -> {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        System.out.println(myData.getValue());
    }

    private static void useSynchronize() {
        List<Thread> threads = new ArrayList<>();
        var myData = new MyData();
        int threadCount = 5;
        for (int i = 0; i < threadCount; i++) {
            var thread = new MyThreadUseSynchronize(myData);
            threads.add(thread);
            thread.start();
        }
        threads.forEach(thread -> {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        System.out.println(myData.getValue());
    }


    private static void correctCode() {
        List<Thread> threads = new ArrayList<>();
        List<MyData> myDataList = new ArrayList<>();
        int threadCount = 5;
        for (int i = 0; i < threadCount; i++) {
            var myData = new MyData();
            myDataList.add(myData);
            var thread = new MyThread(myData);
            threads.add(thread);
            thread.start();
        }
        //等待5个线程运行结束
        threads.forEach(thread -> {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        //访问所有数据对象，进行统计，获取最终结果
        var result = myDataList.stream().mapToInt(MyData::getValue).sum();
        System.out.println(result); //结果一定是：5
    }


}
