package deep;

class DeepCopyDemoClass implements Cloneable {

    public int i = 100;
    public InnerClass b;        //A包容一个B的对象

    public DeepCopyDemoClass() {
        b = new InnerClass();	//创建被包容对象
    }
    //重写基类的clone方法
    public Object clone(){
        var newObj = new DeepCopyDemoClass();
        newObj.i = this.i;
        newObj.b = new InnerClass();
        newObj.b.j=this.b.j;
        return newObj;
    }
}

class InnerClass {
    public int j = 200;
}

public class DeepCopy2 {
    public static void main(String[] args) {
        var a = new DeepCopyDemoClass();
        var other = (DeepCopyDemoClass)a.clone();
        System.out.println(a == other);
        System.out.println(a.b == other.b);
    }
}
