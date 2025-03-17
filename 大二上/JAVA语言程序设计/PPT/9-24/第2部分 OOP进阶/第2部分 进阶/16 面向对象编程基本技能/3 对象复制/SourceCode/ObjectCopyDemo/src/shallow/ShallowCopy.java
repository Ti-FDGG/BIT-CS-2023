package shallow;

class A {
    public int i = 100;
}

public class ShallowCopy {

    public static void main(String[] args) {
        A a = new A();
        //开始克隆
        A other = CloneObject(a);
        System.out.println(a == other);

    }

    static A CloneObject(A obj) {
        A newObj = new A();
        newObj.i = obj.i;
        return newObj;
    }
}

