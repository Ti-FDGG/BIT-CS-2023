import model.ClonablePerson;


public class SerialCloneTest {
    public static void main(String[] args)
            throws CloneNotSupportedException {
        var zhangShan = new ClonablePerson("张三", 20);
        //实现对象克隆
        ClonablePerson zhangShan2 = (ClonablePerson) zhangShan.clone();
        //输出克隆对象的内容
        System.out.println(zhangShan);
        System.out.println(zhangShan2);
        //这两个对象是不是独立的？
        System.out.println(zhangShan == zhangShan2);
    }
}



