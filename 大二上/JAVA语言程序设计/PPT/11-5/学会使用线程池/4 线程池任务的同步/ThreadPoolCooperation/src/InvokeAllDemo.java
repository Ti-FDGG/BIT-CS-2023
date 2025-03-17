import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

class Result {
    private String name;
    private int value;

    //region "getter and setter"
    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }


    public int getValue() {
        return value;
    }


    public void setValue(int value) {
        this.value = value;
    }
    //endregion
}

class InvokeTask implements Callable<Result> {
    private String name;
    public InvokeTask(String name) {
        this.name = name;
    }
    @Override
    public Result call() throws Exception {
        System.out.printf("%s: 开始执行\n", this.name);
        try {
            Long duration = (long) (Math.random() * 10);
            System.out.printf("%s: 等待 %d 秒出结果。\n", this.name, duration);
            TimeUnit.SECONDS.sleep(duration);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        int value = 0;
        for (int i = 0; i < 5; i++) {
            value += (int) (Math.random() * 100);
        }
        Result result = new Result();
        result.setName(this.name);
        result.setValue(value);
        System.out.printf("%s: 结束工作\n", this.name);
        return result;
    }
}

public class InvokeAllDemo {
    public static void main(String[] args) {
        var executor = Executors.newCachedThreadPool();
        List<InvokeTask> taskList = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            InvokeTask task = new InvokeTask("Task-" + i);
            taskList.add(task);
        }
        List<Future<Result>> resultList = null;
        try {
            resultList = executor.invokeAll(taskList);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        executor.shutdown();
        System.out.printf("\n主线程打印结果\n");
        for (int i = 0; i < resultList.size(); i++) {
            Future<Result> future = resultList.get(i);
            try {
                Result result = future.get();
                System.out.printf("%s: %s\n", result.getName(), result.getValue());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
        System.out.println("\n所有的任务都结束了!");
    }
}
