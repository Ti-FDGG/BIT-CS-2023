public class GlobalInfoDemo {
    public static void main(String[] args) {
        //让三个类顺序存取MyInfo对象
        Runnable runnable = () -> {
            new VisitorZhangShan().visit();
            new VisitorLiShi().visit();
            new VisitorWangWu().visit();
        };
        //三个线程并行运行
        for (int i = 0; i < 3; i++) {
            new Thread(runnable).start();
        }
    }
}

class VisitorZhangShan {
    void visit() {
        var myInfo = MyInfoHolder.myInfoInThreadLocal.get();
        myInfo.setInfo(myInfo.getInfo() + "张三到此一游");
        System.out.println(myInfo.hashCode() + ":" + myInfo.getInfo());
    }
}

class VisitorLiShi {
    void visit() {
        var myInfo = MyInfoHolder.myInfoInThreadLocal.get();
        myInfo.setInfo(myInfo.getInfo() + " 李四到此一游");
        System.out.println(myInfo.hashCode() + ":" + myInfo.getInfo());
    }
}

class VisitorWangWu {
    void visit() {
        var myInfo = MyInfoHolder.myInfoInThreadLocal.get();
        myInfo.setInfo(myInfo.getInfo() + " 王五到此一游");
        System.out.println(myInfo.hashCode() + ":" + myInfo.getInfo());
    }
}

//供每个线程独享的数据
class MyInfoHolder {
    public static ThreadLocal<MyInfo> myInfoInThreadLocal =
            ThreadLocal.withInitial(() ->
                    new MyInfo(""));
}

//封装线程工作所需要存取的信息项
class MyInfo {
    private String info;
    public String getInfo() {
        return info;
    }
    public void setInfo(String info) {
        this.info = info;
    }
    public MyInfo(String info) {
        this.info = info;
    }
}
