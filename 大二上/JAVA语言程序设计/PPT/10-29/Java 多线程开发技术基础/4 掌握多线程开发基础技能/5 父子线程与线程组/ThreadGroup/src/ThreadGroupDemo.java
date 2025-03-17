public class ThreadGroupDemo {
    public static void main(String[] args) throws InterruptedException {
        parentThreadGroup();
        //threadGroupInfo();
    }

    private static void parentThreadGroup() {
        Runnable runnable = () -> {
            var threadName = Thread.currentThread().getName();
            var currentGroup = Thread.currentThread().getThreadGroup();
            System.out.println("当前线程" + threadName + "所属线程组："
                    + currentGroup.getName());
            for (; ; ) {
                var parentGroup = currentGroup.getParent();
                if (parentGroup != null) {
                    System.out.println("更上一级的父线程组为:" + parentGroup.getName());
                    currentGroup = parentGroup;
                } else {
                    break;
                }
            }
        };
        new Thread(runnable).start();
    }

    private static void threadGroupInfo() {
        Runnable runnable = () -> {
            String groupName = Thread.currentThread().getThreadGroup().getName();
            String threadName = Thread.currentThread().getName();
            System.out.println("\n" + threadName + "属于" + groupName);
        };
        ThreadGroup group = new ThreadGroup("MyThreadGoup");
        Thread thread1 = new Thread(group, runnable);
        Thread thread2 = new Thread(group, runnable);
        thread1.start();
        thread2.start();
        System.out.println("线程组有激活的线程：" + group.activeCount());
        System.out.println("-----------");
        group.list();
        System.out.println("-----------");
    }
}


