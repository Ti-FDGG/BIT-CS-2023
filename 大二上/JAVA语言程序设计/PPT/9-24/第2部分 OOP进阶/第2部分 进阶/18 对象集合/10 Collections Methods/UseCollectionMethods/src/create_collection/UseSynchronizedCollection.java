package create_collection;

import java.util.*;

public class UseSynchronizedCollection {
    public static void main(String[] args) {

        //下面程序代码创建了四个同步的集合对象
        var collection =
                Collections.synchronizedCollection(new ArrayList<String>());
        var list =
                Collections.synchronizedList(new ArrayList<String>());
        var set =
                Collections.synchronizedSet(new HashSet<String>());
        var map =
                Collections.synchronizedMap(new HashMap<String, Integer>());


        //以下以同步List为例，展示其用法
        try {
            testSynchronizedList();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    static void testSynchronizedList() throws InterruptedException {
        //创建一个可以保存整数的同步集合
        var list = Collections.synchronizedList(
                new ArrayList<Integer>());
        Random ran = new Random();
        //向集合中追加随机整数
        Runnable addElementToList = new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 5; i++) {
                    int ranValue = ran.nextInt(100);
                    System.out.println(Thread.currentThread().getName()
                            + "添加元素" + ranValue);
                    //add方法可以跨线程随意调用
                    list.add(ranValue);
                }
            }
        };
        //迭代访问List
        Runnable visitList = new Runnable() {
            @Override
            public void run() {
                System.out.print("\n"+Thread.currentThread().getName()
                        + "输出List元素：");
                for (var num : list) {
                    System.out.print(num + ",");
                }
            }
        };
        //创建并启动两个线程，向List集合中添加随机整数
        var addThread1 = new Thread(addElementToList);
        var addThread2 = new Thread(addElementToList);
        addThread1.start();
        addThread2.start();
        //等待元素添加完成
        addThread1.join();
        addThread2.join();
        //当迭代访问同步集合时，仍然需要加锁
        synchronized (list) {
            new Thread(visitList).start();
        }
    }
}
