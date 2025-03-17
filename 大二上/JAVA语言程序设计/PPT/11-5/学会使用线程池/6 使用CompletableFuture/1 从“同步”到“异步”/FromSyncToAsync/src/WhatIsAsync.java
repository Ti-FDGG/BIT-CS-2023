import java.util.concurrent.CompletableFuture;

public class WhatIsAsync {
    public static void main(String[] args) {
        //syncCode();

        asyncCode();
    }

    //异步执行的代码
    private static void asyncCode() {
        CompletableFuture.runAsync(() -> {
            task("工作一");
        });
        task("工作二");
    }

    //同步执行的代码
    private static void syncCode() {
        task("工作一");
        task("工作二");
    }

    //用于模拟一个计算任务
    static void task(String taskId) {
        System.out.println(taskId);
        var threadName = Thread.currentThread().getName();
        for (int i = 1; i <= 10; i++) {
            System.out.println(threadName + "线程报告:" + taskId + "己完成" + (i * 10) + "%");
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
