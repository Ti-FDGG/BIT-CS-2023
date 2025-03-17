package deep;

class A {

    public int i = 100;
    public B b;        //A包容一个B的对象

    public A() {
        b = new B();    //创建被包容对象
    }
}

class B {
    public int j = 200;
}

public class DeepCopy {

    public static void main(String[] args) {
        A a = new A();
        A other = cloneObject(a);
        System.out.println(a == other);
        System.out.println(a.b == other.b);

    }

    static A cloneObject(A obj) {
        A newObj = new A();
        newObj.i = obj.i;
        //创建一个被包容的内部对象
        newObj.b = new B();
        newObj.b.j = obj.b.j;
        return newObj;
    }
}
