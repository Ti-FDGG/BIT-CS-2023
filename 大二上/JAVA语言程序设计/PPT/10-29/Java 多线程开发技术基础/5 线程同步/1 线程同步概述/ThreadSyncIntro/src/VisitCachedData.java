import java.util.concurrent.TimeUnit;

public class VisitCachedData {
    public static void main(String[] args) {
        var task = new Task();
        new WorkerThread(task).start();
        new Thread(() -> {
            while (!task.isDone()) {
            }
            System.out.println("\n后台任务结束！");
        }).start();
    }
}

class Task {
    //必须加入volatile，否则程序不会结束
    private volatile boolean isDone = false;
    public boolean isDone() {
        return isDone;
    }
    public void setDone(boolean done) {
        isDone = done;
    }
}

class WorkerThread extends Thread {
    Task currentTask = null;

    public WorkerThread(Task task) {
        currentTask = task;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            try {
                TimeUnit.SECONDS.sleep(1);
                System.out.print(".");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        currentTask.setDone(true);
    }
}
